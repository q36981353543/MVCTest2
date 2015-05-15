package com.springapp.mvc.project_dto;

import com.springapp.mvc.project_data.ResultCode;
import org.codehaus.jackson.annotate.JsonProperty;

public class ResponseBase {
    private ResultCode resultCode;

    public ResponseBase(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    @JsonProperty("resultCode")
    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
