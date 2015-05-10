package com.springapp.mvc.Data;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Data {
    private static ArrayList<Queue<Command>> commands = new ArrayList<>();

    public static void add(Command command){
        if(isFound(command.getDeviceId())) {
            for (Queue<Command> q : commands) {
                if (!q.isEmpty() && q.element().getDeviceId() == command.getDeviceId()) {
                    q.add(command);
                }
            }
        }else{
            Queue<Command> queue = new ConcurrentLinkedQueue<>();
            queue.add(command);
            commands.add(queue);
        }
    }

    public static Command remove(int deviceId){
        for (Queue<Command> queue : commands) {
            if (!queue.isEmpty() && queue.element().getDeviceId() == deviceId) {
                return queue.remove();
            }
        }return null;
    }

    public static boolean isFound(int deviceId){
        for (Queue<Command> q : commands) {
            if (!q.isEmpty() && q.element().getDeviceId() == deviceId) {
                return true;
            }
        }return false;
    }
}
