package com.springapp.mvc.project_data;

import com.springapp.mvc.project_dto.SendCommandDto;

public class Command {
    private int commandId;
    private int deviceId;
    private String command;

    private static final int MIN_INDEX_VALUE = 0;
    private static final int MAX_INDEX_VALUE = 255;
    private static int index = MIN_INDEX_VALUE;

    public Command(SendCommandDto sendCommandDto) {
        if (index >= MAX_INDEX_VALUE){
            index = MIN_INDEX_VALUE;
        }
        this.commandId = index++;
        this.deviceId = sendCommandDto.getDeviceId();
        this.command = sendCommandDto.getCommand();
    }

    public int getCommandId() {
        return commandId;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
