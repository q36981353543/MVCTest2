package com.springapp.mvc.project_dto;

public class SendCommandDto {
    private String deviceId;
    private String command;

    public SendCommandDto() {}

    public SendCommandDto(String deviceId, String command) {
        this.deviceId = deviceId;
        this.command = command;
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
