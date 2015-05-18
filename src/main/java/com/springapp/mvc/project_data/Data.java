package com.springapp.mvc.project_data;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class Data {
    private Map<String, Queue<Command>> commands = new LinkedHashMap<>();
    private final int MAX_QUEUE_LENGTH = 256;   //0..255

    public void add(Command command){
        if(commands.containsKey(command.getDeviceId())) {
            commands.get(command.getDeviceId()).add(command);
            if (commands.get(command.getDeviceId()).size() > MAX_QUEUE_LENGTH){
                commands.get(command.getDeviceId()).remove();
            }
        }else{
            Queue<Command> queue = new ConcurrentLinkedQueue<>();
            queue.add(command);
            commands.put(command.getDeviceId(),queue);
        }
    }

    public Command remove(String deviceId){
        if(commands.containsKey(deviceId) && !commands.get(deviceId).isEmpty()) {
            return commands.get(deviceId).remove();
        }else{
            return null;
        }
    }
}
