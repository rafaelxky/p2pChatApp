package io.sourceWare.program.client.view;
import javax.swing.JOptionPane;

public class Popus {
    public static void unknownConnection(){
        popup("Warning, you have received a packet from an unknown address!", "Warning!");
    }

    public static void popup(String message, String title){
        JOptionPane.showMessageDialog(null,message ,title,JOptionPane.INFORMATION_MESSAGE);
    }
}