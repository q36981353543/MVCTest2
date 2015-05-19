package com.springapp.mvc.project_data;

import com.springapp.mvc.project_dto.SendCommandDto;

import java.util.LinkedHashMap;
import java.util.Map;

public class Command {
    private int commandId;
    private String deviceId;
    private String command;

    private static Map<String, Integer> indexMap = new LinkedHashMap<>();
    private static final int MIN_INDEX_VALUE = 0;
    private static final int MAX_INDEX_VALUE = 255;

    public Command(SendCommandDto sendCommandDto) {
        this.deviceId = sendCommandDto.getDeviceId();
        this.command = sendCommandDto.getCommand();
        if (indexMap.containsKey(this.deviceId) && indexMap.get(this.deviceId) < MAX_INDEX_VALUE){
            this.commandId = indexMap.get(this.deviceId) +1;
        }else{
            this.commandId = MIN_INDEX_VALUE;
        }indexMap.put(this.deviceId, this.commandId);
    }

    public int getCommandId() {
        return commandId;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
