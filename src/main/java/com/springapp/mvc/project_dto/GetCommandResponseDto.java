package com.springapp.mvc.project_dto;

import com.springapp.mvc.project_data.Command;

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
}
