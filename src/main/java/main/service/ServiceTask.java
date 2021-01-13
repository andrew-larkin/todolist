package main.service;

import main.model.Task;
import main.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceTask {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        List<Task> tasks = new ArrayList<>();
        for (Task task : taskIterable) {
            tasks.add(task);
        }
        return tasks;
    }

    public int addTask(Task task) {
        Task newTask = taskRepository.save(task);
        return newTask.getId();
    }

   public ResponseEntity getTask(int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalTask.get(), HttpStatus.OK);
    }

    public ResponseEntity delete(int id) {
        if(!taskRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Optional<Task> taskToDelete = taskRepository.findById(id);
        taskRepository.delete(taskToDelete.get());
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity put(int id, Task task) {
        if(!taskRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Optional<Task> taskToUpdate = taskRepository.findById(id);
        Task taskOld = taskToUpdate.get();
        if (task.getId() == taskOld.getId()) {
            taskOld.setName(task.getName());
            taskOld.setDate(task.getDate());
            taskOld.setDescription(task.getDescription());
            taskRepository.save(taskOld);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}