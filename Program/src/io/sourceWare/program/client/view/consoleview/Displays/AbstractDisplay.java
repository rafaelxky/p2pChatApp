package io.sourceWare.program.client.view.consoleview.Displays;

import java.util.HashMap;

public abstract class AbstractDisplay {
    // hash map with int and the next display
    HashMap<Integer, AbstractDisplay> displays = new HashMap<>();

    public AbstractDisplay() {
        show();
        input();
    }

    // start
    public abstract void show();
    public abstract void input();
}
