package com.springapp.mvc.Service;

import com.springapp.mvc.DTO.GetCommandResponseDto;
import com.springapp.mvc.DTO.SendCommandDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class MainController {
    @RequestMapping(value="/commands/{deviceId}/{timeout}", method = RequestMethod.GET)
    @ResponseBody
    public DeferredResult<GetCommandResponseDto> printCommand(@PathVariable final int deviceId, @PathVariable long timeout) throws Exception {
        return DeferredResultController.printCommand(deviceId, timeout);
    }

    @RequestMapping(value="/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void doPost(@RequestBody final SendCommandDto sendCommandDto) throws IOException {
        DeferredResultController.addCommand(sendCommandDto);
    }
}