package com.springapp.mvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Controller
@RequestMapping("/")
public class CommandController {
    //some test data
    private Data data = new Data(
            new Command[]{
                    new Command(1, "command1"),
                    new Command(2, "command2")
            });
    //queue with async contexts
    private Queue<DeferredResult<String>> queue = new ConcurrentLinkedQueue<>();

	@RequestMapping(value="/commands/{commandId}/{timeout}", method = RequestMethod.GET)
	public DeferredResult<String> printCommand(@PathVariable int commandId, @PathVariable long timeout, ModelMap model) throws Exception {
        String resultStr = "command not found";             //message, that shows in command.jsp page
        if (data.isFound(commandId)){
            resultStr = data.getCommand(commandId).toString();
            final long NO_DEFERRED = 1;     //timeout in ms (0 not supported)
            timeout = NO_DEFERRED;
        }
        DeferredResult<String> deferredResult = new DeferredResult<>(timeout, "command");        //start async context
        queue.add(deferredResult);
        //WebAsyncUtils.getAsyncManager((WebRequest) model).startDeferredResultProcessing(deferredResult,queue);    //may be smth, like this?
        model.addAttribute("message", resultStr);
        return deferredResult;
	}

    @RequestMapping(value="/add", method=RequestMethod.GET)
    public String addCommand(){
        return "add";
    };

    @RequestMapping(value="/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Command post(@RequestBody Command command) throws IOException {
        //add entered command if there is no such
        if (!data.isFound(command.getId())) {
            data.add(command);
            for (DeferredResult<String> q: queue){
                q.setResult("command");                 //ToDo: show right result
            }
        }
        return command;
    }

}