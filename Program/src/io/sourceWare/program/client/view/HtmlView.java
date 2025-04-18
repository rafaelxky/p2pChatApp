package io.sourceWare.program.client.view;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class HtmlView {
    public static void main(String[] args) {
        show();
    }

    public static void show() {
        JFrame frame = new JFrame("p2pChatApp");
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        editorPane.setContentType("text/html");

        try {
            URL url = HtmlView.class.getResource("/io/sourceWare/program/client/view/html/index.html");

            if (url == null) {
                throw new IOException("File not found in classpath: /io/sourceWare/program/client/view/html/index.html");
            }

            editorPane.setPage(url);
            System.out.println("Serving file from: " + url);
        } catch (IOException e) {
            editorPane.setText("<html><h1 style='color:red;'>Error loading page</h1></html>");
            System.err.println("Error loading HTML: " + e.getMessage());
            e.printStackTrace();
        }

        frame.add(new JScrollPane(editorPane));
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
