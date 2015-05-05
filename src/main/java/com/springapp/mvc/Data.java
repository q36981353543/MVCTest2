package com.springapp.mvc;

import java.util.ArrayList;
import java.util.Collections;

public class Data {
    private ArrayList<Command> commands = new ArrayList<>();

    public Data() {}

    public Data(Command[] commands) {
        Collections.addAll(this.commands, commands);
    }

    public void add(Command cmd){
        commands.add(cmd);
    }

    public boolean isFound(int id){
        for (Command command : commands) {
            if (command.getId() == id) {
                return true;
            }
        }return false;
    }

    public Command getCommand(int id) {
        for (Command command : commands) {
            if (command.getId() == id) {
                return command;
            }
        }return null;
    }

}
