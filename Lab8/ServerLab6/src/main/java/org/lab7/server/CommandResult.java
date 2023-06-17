package org.lab7.server;

public class CommandResult {
    private String message;
    private boolean isSuccess;
    private boolean isChanged;

    public CommandResult(String message, boolean isSuccess) {
        this.message = message;
        this.isSuccess = isSuccess;
        this.isChanged = false;
    }

    public CommandResult(String message, boolean isSuccess, boolean isChanged) {
        this.message = message;
        this.isSuccess = isSuccess;
        this.isChanged = isChanged;
    }

    public String getMessage() {
        return message;
    }
    public boolean getIsChanged() {
        return isChanged;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }
}
