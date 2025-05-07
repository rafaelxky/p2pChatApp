package io.sourceWare.program.client.model.connections.matrix;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class MatrixClient {
    private final String homeserver;
    private final String username;
    private final String password;
    private String accessToken;
    private String userId;

    public MatrixClient(String homeserver, String username, String password) {
        this.homeserver = homeserver;
        this.username = username;
        this.password = password;
    }

    public boolean connect() {
        try {
            URL url = new URL(homeserver + "/_matrix/client/r0/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String payload = String.format("""
                    {
                      "type": "m.login.password",
                      "user": "%s",
                      "password": "%s"
                    }
                    """, username, password);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.getBytes(StandardCharsets.UTF_8));
            }

            if (conn.getResponseCode() == 200) {
                String response = readResponse(conn.getInputStream());
                accessToken = parseJson(response, "access_token");
                userId = parseJson(response, "user_id");
                System.out.println("✅ Connected as " + userId);
                return true;
            } else {
                System.out.println("❌ Login failed: " + conn.getResponseCode());
                return false;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    public void send(String roomId, String message) throws IOException, IOException {
        String txnId = UUID.randomUUID().toString();  // Unique txn ID per message
        String encodedRoomId = URLEncoder.encode(roomId, "UTF-8");
        URL url = new URL(homeserver + "/_matrix/client/r0/rooms/" + encodedRoomId + "/send/m.room.message/" + txnId + "?access_token=" + accessToken);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String body = String.format("""
        {
          "msgtype": "m.text",
          "body": "%s"
        }
        """, message);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(body.getBytes(StandardCharsets.UTF_8));
        }

        System.out.println("📤 Sent message to " + roomId);
    }

    public void receive(String roomId) throws IOException {
        URL url = new URL(homeserver + "/_matrix/client/r0/sync?access_token=" + accessToken + "&timeout=30000");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() == 200) {
            String response = readResponse(conn.getInputStream());
            // 💡 Here you would normally parse the JSON to extract messages for the given room
            System.out.println("📥 Received sync response: " + response.substring(0, Math.min(500, response.length())) + "...");
        } else {
            System.out.println("❌ Sync failed: " + conn.getResponseCode());
        }
    }

    private String readResponse(InputStream stream) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(stream))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }

    private String parseJson(String json, String key) {
        int start = json.indexOf("\"" + key + "\":\"") + key.length() + 4;
        int end = json.indexOf("\"", start);
        return json.substring(start, end);
    }
}
