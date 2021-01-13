package main.controller;

import main.repository.TaskRepository;
import main.service.ServiceTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import main.model.Task;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class TaskController {

    private final ServiceTask serviceTask;

    public TaskController(ServiceTask serviceTask) {
        this.serviceTask = serviceTask;
    }

    @RequestMapping(value = "/tasks/", method = RequestMethod.GET)
    public List<Task> list() {
        return serviceTask.getAllTasks();
    }

    @RequestMapping(value = "/tasks/", method = RequestMethod.POST)
    public int add(Task task) {
        return serviceTask.addTask(task);
    }

    @GetMapping(value = "tasks/{id}")
    public ResponseEntity get(@PathVariable int id) {
       return serviceTask.getTask(id);
    }

    @DeleteMapping(value = "/tasks/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        return serviceTask.delete(id);
    }

    @PutMapping(value = "tasks/{id}")
    public ResponseEntity put(@PathVariable int id, Task task) {
        return serviceTask.put(id, task);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}