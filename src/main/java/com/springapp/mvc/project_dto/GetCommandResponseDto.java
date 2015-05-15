package com.springapp.mvc.project_dto;

import com.springapp.mvc.project_data.Command;
import com.springapp.mvc.project_data.ResultCode;
import org.codehaus.jackson.annotate.JsonProperty;

public class GetCommandResponseDto extends ResponseBase{
    private int commandId;
    private String command;

    public GetCommandResponseDto(Command command) {
        super(ResultCode.OK);
        this.commandId = command.getCommandId();
        this.command = command.getCommand();
    }

    @JsonProperty("resultCode")
    public ResultCode getResultCode() {
        return super.getResultCode();
    }

    @JsonProperty("commandId")
    public int getCommandId() {
        return commandId;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    @JsonProperty("command")
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
