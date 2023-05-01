package org.lab6.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CommandResult {
    private String message;
    private boolean isSuccess;
    @JsonCreator
    public CommandResult(@JsonProperty("message")String message,@JsonProperty("isSuccess") boolean isSuccess) {
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
