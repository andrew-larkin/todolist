package main.repository;

import main.model.Task;
import main.service.ServiceStorage;
import java.util.List;

public class Storage {

    private final ServiceStorage serviceStorage;

    public Storage(ServiceStorage serviceStorage) {
        this.serviceStorage = serviceStorage;
    }

    public List<Task> getAllTasks () {
        return serviceStorage.getAllTasks();
    }

    public int addTask (Task task) {
        return serviceStorage.addTask(task);
    }

    public Task getTask (int taskId) {
        return serviceStorage.getTask(taskId);
    }
    public Task deleteTask (int taskId) {
       return serviceStorage.deleteTask(taskId);
    }

    public Task putTask (int taskId) {
        return serviceStorage.putTask(taskId);
    }
}