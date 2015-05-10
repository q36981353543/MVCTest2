package com.springapp.mvc.Data;

import com.springapp.mvc.DTO.SendCommandDto;

public class Command {
    private int commandId;
    private int deviceId;
    private String command;
    private static int index = 0;

    public Command(int deviceId, String command) {
        if (index >= 255){
            index = 0;
        }
        this.commandId = index++;
        this.deviceId = deviceId;
        this.command = command;
    }

    public Command(SendCommandDto sendCommandDto) {
        if (index >= 255){
            index = 0;
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

    public static int getIndex() {
        return index;
    }

    public static void setIndex(int index) {
        Command.index = index;
    }

    @Override
    public String toString() {
        return "Command{" +
                "commandId=" + commandId +
                ", deviceId=" + deviceId +
                ", command='" + command + '\'' +
                '}';
    }
}
