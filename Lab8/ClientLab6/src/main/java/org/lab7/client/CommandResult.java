package org.lab7.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CommandResult {
    private String message;
    private boolean isSuccess;
    private boolean isChanged;

    @JsonCreator
    public CommandResult(@JsonProperty("message")String message,@JsonProperty("isSuccess") boolean isSuccess, @JsonProperty("isChanged") boolean isChanged) {
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
