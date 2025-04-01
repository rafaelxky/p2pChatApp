package io.sourceWare.program.client.view.html;

import io.sourceWare.program.client.view.html.show.JavaFXShow;
import io.sourceWare.program.client.view.html.show.Show;
import io.sourceWare.program.client.view.html.show.SwingShow;

public class ShowHtml {
    public static void main(String[] args) {
        Show show = new JavaFXShow();
        show.show();
    }
}
