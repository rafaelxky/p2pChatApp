package io.sourceWare.program.client.model.file_handler.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonHandler {
    private static String filePath;

    // Method to read JSON from file and return a list of ContactPojo objects
    public static List<ContactPojo> readContactJson(String filePath) {
        List<ContactPojo> contactList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            reader.close();

            // Parse the JSON content into a JSONObject
            JSONObject jsonObject = new JSONObject(jsonContent.toString());

            // Get the contacts array from the JSON object
            JSONArray contactsArray = jsonObject.getJSONArray("contacts");

            // Iterate over the contacts array and map each entry to a ContactPojo
            for (int i = 0; i < contactsArray.length(); i++) {
                JSONObject contactJson = contactsArray.getJSONObject(i);
                ContactPojo contact = new ContactPojo();
                contact.setId(contactJson.getString("id"));
                contact.setAlias(contactJson.getString("alias"));

                // Add the contact to the list
                contactList.add(contact);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return contactList;
    }

    public static List<ContactPojo> readContactJson() {
        return readContactJson(filePath);
    }

    public static void writeContactJson(ContactPojo contact, String url){
        // Create a JSONObject
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", contact.getId());
        jsonObject.put("alias", contact.getAlias());

        // Write the JSON object to a file
        try (FileWriter file = new FileWriter(url)) {
            // todo: continue, make so that it appends to file, might need to use mthods from gile handler
            List<ContactPojo> list = readContactJson();
            list.add(contact);
            list.forEach(elem -> System.out.println(elem));

            file.write(jsonObject.toString(4)); // Indentation of 4 spaces for pretty printing
            System.out.println("Successfully wrote JSON to file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeContactJson(ContactPojo contact){
        writeContactJson(contact,filePath);
    }

    public static void setFilePath(String filePath) {
        JsonHandler.filePath = filePath;
    }

    public static String getFilePath() {
        return filePath;
    }
}
