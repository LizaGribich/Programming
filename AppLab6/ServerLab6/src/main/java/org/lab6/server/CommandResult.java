package org.lab6.server;

public class CommandResult {
    private String message;
    private boolean isSuccess;

    public CommandResult(String message, boolean isSuccess) {
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }
}
