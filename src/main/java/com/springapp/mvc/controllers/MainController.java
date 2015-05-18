package com.springapp.mvc.controllers;

import com.springapp.mvc.project_dto.ResponseBase;
import com.springapp.mvc.project_dto.SendCommandDto;
import com.springapp.mvc.services.DeferredResultService;
import com.springapp.mvc.services.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

@Controller
@RequestMapping("/")
public class MainController extends ExceptionService {
    @Autowired
    private DeferredResultService deferredResultService;

    @RequestMapping(value="/commands/{deviceId}/{timeout}", method = RequestMethod.GET)
    @ResponseBody
    public DeferredResult<ResponseBase> printCommand(@PathVariable final String deviceId, @PathVariable long timeout) throws Exception {
        return deferredResultService.printCommand(deviceId, timeout);
    }

    @RequestMapping(value="/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseBase doPost(@RequestBody final SendCommandDto sendCommandDto) throws Exception {
        return deferredResultService.addCommand(sendCommandDto);
    }
}