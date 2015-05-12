package com.springapp.mvc.services;

import com.springapp.mvc.project_dto.GetCommandResponseDto;
import com.springapp.mvc.project_dto.SendCommandDto;
import com.springapp.mvc.project_data.Data;
import com.springapp.mvc.project_data.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DeferredResultService {
    //queue with async contexts
    private Map<Integer,DeferredResult<GetCommandResponseDto>> deferredResultsMap = new LinkedHashMap<>();
    @Autowired
    private Data data;

    public DeferredResult<GetCommandResponseDto> printCommand(final int deviceId, long timeout) throws Exception {
        final long MS_TO_SEC = 1000;
        final DeferredResult<GetCommandResponseDto> deferredResult = new DeferredResult<>(timeout*MS_TO_SEC);
        Command command = data.remove(deviceId);
        if(command != null) {
            //show answer without delay
            deferredResult.setResult(new GetCommandResponseDto(command));
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

    public void addCommand(SendCommandDto sendCommandDto) throws IOException {
        Command command = new Command(sendCommandDto);
        data.add(command);
        for (Map.Entry<Integer, DeferredResult<GetCommandResponseDto>> entry : deferredResultsMap.entrySet()){
            if (entry.getKey().equals(command.getDeviceId())){
                //show entered command in context, which wait it
                entry.getValue().setResult(new GetCommandResponseDto(command));
            }
        }
    }
}
