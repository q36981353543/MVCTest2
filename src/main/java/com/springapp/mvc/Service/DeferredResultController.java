package com.springapp.mvc.Service;

import com.springapp.mvc.DTO.GetCommandResponseDto;
import com.springapp.mvc.DTO.SendCommandDto;
import com.springapp.mvc.Data.Data;
import com.springapp.mvc.Data.Command;
import org.springframework.web.context.request.async.DeferredResult;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class DeferredResultController {
    //queue with async contexts
    private static Map<Integer,DeferredResult<GetCommandResponseDto>> deferredResultsMap = new LinkedHashMap<>();

    public static DeferredResult<GetCommandResponseDto> printCommand(final int deviceId, long timeout) throws Exception {
        final long MS_TO_SEC = 1000;
        final DeferredResult<GetCommandResponseDto> deferredResult = new DeferredResult<>(timeout*MS_TO_SEC);
        if (Data.isFound(deviceId)){
            //show answer without delay
            deferredResult.setResult(new GetCommandResponseDto(Data.remove(deviceId)));
        }else{
            //start async context
            deferredResultsMap.put(deviceId, deferredResult);
            deferredResult.onTimeout(new Runnable() {
                @Override
                public void run() {
                    //command doesn't appear
                    deferredResult.setResult(null);
                }
            });
            deferredResult.onCompletion(new Runnable() {
                @Override
                public void run() {
                    //command appeared
                    deferredResultsMap.remove(deviceId);
                }
            });
        }
        return deferredResult;
    }

    public static void addCommand(SendCommandDto sendCommandDto) throws IOException {
        Command command = new Command(sendCommandDto);
        Data.add(command);
        for (Map.Entry<Integer, DeferredResult<GetCommandResponseDto>> entry : deferredResultsMap.entrySet()){
            if (entry.getKey().equals(command.getDeviceId())){
                //show entered command in context, which wait it
                entry.getValue().setResult(new GetCommandResponseDto(command));
            }
        }
    }
}
