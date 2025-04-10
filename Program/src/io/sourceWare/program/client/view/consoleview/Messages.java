package io.sourceWare.program.client.view.consoleview;

public enum Messages {
    Welcome("Welcome to \"insert name\" chat app"),
    MainMenuQuit("5 - Quit", 5),
    MainMenuTorConnect("1 - Tor connection", 1),
    MainMenuDirectConnection(" 2 - Direct connection", 2),
    MainMenuGetLocalData(" 3 - Get local data", 3),
    DislayLocalIP("IP: "),
    DisplayLocalPort("Port: "),
    MainMenu(
            Messages.MainMenuTorConnect.message + System.lineSeparator() +
            Messages.MainMenuDirectConnection.message + System.lineSeparator() +
            Messages.MainMenuGetLocalData.message);



    private final String message;
    private final Integer opt;

    Messages(String message, Integer opt) {
        this.message = message;
        this.opt = opt;
    }

    Messages(String message) {
        this.message = message;
        opt = null;
    }

    public String getMessage() {
        return message;
    }
}
