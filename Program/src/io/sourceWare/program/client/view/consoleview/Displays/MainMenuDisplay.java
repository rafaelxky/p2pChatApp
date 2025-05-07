package io.sourceWare.program.client.view.consoleview.Displays;

import io.sourceWare.program.client.view.consoleview.Messages;

public class MainMenuDisplay extends AbstractDisplay {

    @Override
    public void show() {
        System.out.println(Messages.Welcome.getMessage());
        System.out.println(Messages.MainMenu.getMessage());
    }

    @Override
    public void input() {

    }
}
