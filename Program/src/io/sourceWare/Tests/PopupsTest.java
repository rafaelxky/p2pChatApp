package io.sourceWare.Tests;

import io.sourceWare.program.client.view.Popus;
import java.awt.*;
import java.awt.event.KeyEvent;

public class PopupsTest {
    public static void main(String[] args) {

        System.out.println("Popup test result: " + popupTests());
    }

    public static boolean popupTests(){
        System.out.println("Starting popup tests ⏳");
        System.out.println("If you see a popup on screen for more than 3 seconds the test might have failed ℹ️");
        System.out.println("This test mimics key presses (enter) which may cause issues in other programs \uD83D\uDD36");

        return unknownConnectionPopupTest();
    }

    public static boolean unknownConnectionPopupTest() {
        try {
            // Start a thread to handle the popup automatically
            new Thread(() -> {
                try {
                    Thread.sleep(1500); // Give the popup time to appear
                    Robot robot = new Robot();
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);
                } catch (AWTException | InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            // Trigger the popup
            Popus.unknownConnection();

            // If we reach this point, assume the popup displayed and closed successfully
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
