package com.springapp.mvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class CommandController {
    private Data data = new Data(
            new Command[]{
                    new Command(1, "command1"),
                    new Command(2, "command2")
            });                                                                                 //some test data
    private Map<Integer,DeferredResult<String>> deferredResultsMap = new LinkedHashMap<>();     //queue with async contexts

    @RequestMapping(value="/commands/{commandId}/{timeout}", method = RequestMethod.GET)
    @ResponseBody
    public DeferredResult<String> printCommand(@PathVariable final int commandId, @PathVariable long timeout, final ModelMap model) throws Exception {
        final long MS_TO_SEC = 1000;
        final DeferredResult<String> deferredResult = new DeferredResult<>(timeout*MS_TO_SEC, commandId);
        if (data.isFound(commandId)){
            deferredResult.setResult(data.getCommand(commandId).toString());                    //show answer without delay
        }else{
            deferredResultsMap.put(commandId, deferredResult);                                  //start async context
            deferredResult.onTimeout(new Runnable() {
                @Override
                public void run() {
                    deferredResult.setResult("Command no found");                   //command doesn't appear
                }
            });
            deferredResult.onCompletion(new Runnable() {
                @Override
                public void run() {
                    deferredResultsMap.remove(commandId);                           //command appeared
                }
            });
        }
        return deferredResult;                          //answer
    }

    @RequestMapping(value="/add", method=RequestMethod.GET)
    public String addCommand(){
        return "add";           //json input page
    }

    @RequestMapping(value="/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void post(@RequestBody final Command command) throws IOException {
        if (!data.isFound(command.getId())) {
            data.add(command);                  //add entered command if there is no such
            for (Map.Entry<Integer, DeferredResult<String>> entry : deferredResultsMap.entrySet()){
                if (entry.getKey().equals(command.getId())){
                    entry.getValue().setResult(command.toString());     //show entered command in context, which wait it
                }
            }
        }
    }

}