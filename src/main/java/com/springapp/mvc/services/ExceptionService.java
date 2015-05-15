package com.springapp.mvc.services;

import com.springapp.mvc.project_data.ResultCode;
import com.springapp.mvc.project_dto.ResponseBase;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class ExceptionService {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseBase handleException() {
        return new ResponseBase(ResultCode.ERROR);
    }
}
