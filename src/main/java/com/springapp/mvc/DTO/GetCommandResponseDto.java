package com.springapp.mvc.DTO;

import com.springapp.mvc.Data.Command;

public class GetCommandResponseDto {
    private int commandId;
    private String command;

    public GetCommandResponseDto(Command command) {
        this.commandId = command.getCommandId();
        this.command = command.getCommand();
    }

    public int getCommandId() {
        return commandId;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "GetCommandResponseDto{" +
                "commandId=" + commandId +
                ", command='" + command + '\'' +
                '}';
    }
}
