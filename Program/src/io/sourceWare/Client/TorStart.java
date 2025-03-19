package io.sourceWare.Client;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

// class
public class TorStart {

    // main
    public static void main(String[] args) throws IOException, InterruptedException {
        startTor();
        TorHiddenServiceSetup();
    }

    static File torExecutable;

    // start tor
    public static void startTor() throws InterruptedException, IOException {
        torExecutable = new File("tor/tor/tor.exe");

        if (!torExecutable.exists()) {
            System.out.println("❌ Tor not found in " + torExecutable.getAbsolutePath());
            return;
        }

        if (isTorRunning()) {
            System.out.println("✅ Tor is already running!");
            return;
        }

        Process torProcess = new ProcessBuilder(torExecutable.getAbsolutePath()).start();
        System.out.println("🚀 Tor is starting...");

        // ✅ Wait until Tor is actually running
        if (waitForTorStartup(30)) {  // Wait up to 30 seconds
            System.out.println("✅ Tor is now running on 127.0.0.1:9050");
        } else {
            System.out.println("❌ Tor failed to start.");
        }
    }

    // setup hidden services
    public static void TorHiddenServiceSetup() throws IOException, InterruptedException {
        String hiddenServiceDir = "tor/tor/hidden_service", torccPath = "tor/tor/torrc";
        Files.createDirectories(Paths.get(hiddenServiceDir));

        File hsDir = new File(hiddenServiceDir);
        if (!hsDir.exists()) {
            hsDir.mkdirs();
            System.out.println("📂 Hidden Service directory created");
        }

        File torccFile = new File(torccPath);
        if (!torccFile.exists()) {  // ✅ Only create if missing
            String torccContent = "HiddenServiceDir " + hiddenServiceDir + "\n" +
                    "HiddenServicePort 5000 127.0.0.1:5000";
            Files.write(Paths.get(torccPath), torccContent.getBytes());
            System.out.println("✅ torrc file created!");
        } else {
            System.out.println("ℹ️ torrc already exists, skipping creation.");
        }

        ProcessBuilder pb = new ProcessBuilder("tor/tor/tor.exe", "-f", torccPath);
        pb.start();
        System.out.println("🚀 Tor started with Hidden Service!");

        Thread.sleep(5000);

        File onionFile = new File(hiddenServiceDir + "/hostname");

        int retries = 30;  // Wait up to 30 seconds
        while (!onionFile.exists() && retries > 0) {
            Thread.sleep(1000);
            System.out.print(".");
            retries--;
        }
        System.out.println("");

        if (onionFile.exists()) {
            String onionAddress = new String(Files.readAllBytes(onionFile.toPath())).trim();
            System.out.println("🧅 Your onion address is: " + onionAddress);
        } else {
            System.out.println("❌ Tor didn’t generate the .onion address. Try restarting Tor.");
        }
    }

    // check if tor is running
    public static boolean isTorRunning() {
        try (Socket socket = new Socket("127.0.0.1", 9050)) {
            return true;  // ✅ Tor is running because we connected
        } catch (IOException e) {
            return false; // ❌ No connection → Tor is NOT running
        }
    }

    // ✅ Wait for Tor to start
    public static boolean waitForTorStartup(int timeoutSeconds) throws InterruptedException {
        int elapsed = 0;
        while (elapsed < timeoutSeconds) {
            if (isTorRunning()) {
                return true;
            }
            Thread.sleep(1000);  // Wait 1 sec before retrying
            elapsed++;
        }
        return false;  // ❌ Timed out
    }
}
