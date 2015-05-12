package com.springapp.mvc.controllers;

import com.springapp.mvc.project_dto.GetCommandResponseDto;
import com.springapp.mvc.project_dto.SendCommandDto;
import com.springapp.mvc.services.DeferredResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private DeferredResultService deferredResultService;

    @RequestMapping(value="/commands/{deviceId}/{timeout}", method = RequestMethod.GET)
    @ResponseBody
    public DeferredResult<GetCommandResponseDto> printCommand(@PathVariable final int deviceId, @PathVariable long timeout) throws Exception {
        return deferredResultService.printCommand(deviceId, timeout);
    }

    @RequestMapping(value="/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void doPost(@RequestBody final SendCommandDto sendCommandDto) throws IOException {
        deferredResultService.addCommand(sendCommandDto);
    }
}