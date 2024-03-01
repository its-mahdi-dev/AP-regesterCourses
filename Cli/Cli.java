package Cli;

import Application.Application;

public abstract class Cli {
    Application application;

    public Cli(Application application) {
        this.application = application;
    }

    public Application getApplication() {
        return application;
    }

    public abstract void processCommand(String command);
}
