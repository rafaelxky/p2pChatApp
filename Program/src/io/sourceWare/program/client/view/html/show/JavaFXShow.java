package io.sourceWare.program.client.view.html.show;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class JavaFXShow extends Application implements Show {

    @Override
    public void start(Stage stage) {
        // Create a WebView to display HTML content
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Try to load the HTML file
        URL url = getClass().getResource("/io/sourceWare/program/client/view/html/index.html");

        if (url != null) {
            // If the file is found, load it into the WebView
            webEngine.load(url.toExternalForm());
            System.out.println("Serving file from: " + url);
        } else {
            // If the file is not found, display an error message
            System.err.println("Error: File not found in classpath: /io/sourceWare/program/client/view/html/index.html");
            webEngine.loadContent("<html><h1 style='color:red;'>Error loading page</h1></html>");
        }

        // Create a scene with the WebView and set the window size
        Scene scene = new Scene(webView, 800, 600);
        stage.setTitle("p2pChatApp");
        stage.setScene(scene);
        stage.show();
    }

    // Implement the show method from the Show interface
    @Override
    public void show() {
        launch();
    }

    // Main method to launch the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }
}
