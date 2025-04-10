package io.sourceWare.program.client.view.html.show;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.event.EventTarget;

import java.io.IOException;
import java.net.URL;

public class JavaFXShow extends Application implements Show {

    @Override
    public void start(Stage primaryStage) {
        // Create a WebView to display HTML content
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Load an HTML file from the classpath (or use a URL)
        // This can be your index.html or any valid HTML file
        String htmlFilePath = "file:///path/to/your/index.html";  // Modify the path
        webEngine.load(htmlFilePath);

        // Alternatively, load HTML content as a string directly
        String htmlContent = "<html><body><h1>Hello, JavaFX!</h1><p>This is a paragraph.</p></body></html>";
        webEngine.loadContent(htmlContent);

        // Set up the scene with the WebView
        Scene scene = new Scene(webView, 800, 600);
        primaryStage.setTitle("JavaFX HTML Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void show() {
        launch();
    }
}
