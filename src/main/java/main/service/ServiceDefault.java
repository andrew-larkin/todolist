package main.service;

import main.model.Task;
import main.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;

@Service
public class ServiceDefault {

    @Autowired
    TaskRepository taskRepository;


    public String indexing(Model model) {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : taskIterable) {
            tasks.add(task);
        }
        model.addAttribute("tasks", tasks);
        model.addAttribute("tasksCount", tasks.size());
        return "index";
    }
}
