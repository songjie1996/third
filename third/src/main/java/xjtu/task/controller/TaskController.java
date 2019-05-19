package xjtu.task.controller;


import org.springframework.web.bind.annotation.*;
import xjtu.task.domain.Task;

import java.util.*;

@RestController
@RequestMapping(value = "/api/task")
public class TaskController {

    static Map<Integer, Task> tasks = Collections.synchronizedMap(new HashMap<Integer, Task>());

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public List<Task> getTaskList(){
        List<Task> r = new ArrayList<>(tasks.values());
        return r;
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String postTaskList(@ModelAttribute Task task){
        tasks.put(task.getId(),task);
        return "success";
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Task getTaskList(@PathVariable Integer id) {
        return tasks.get(id);
    }


    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteTaskList(@PathVariable Integer id) {
        tasks.remove(id);
        return "success";
    }

}
