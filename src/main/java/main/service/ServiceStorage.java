package main.service;

import main.model.Task;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ServiceStorage {

    private static int currentId = 1;
    private static HashMap<Integer, Task> tasks = new HashMap<>();

    public static List<Task> getAllTasks () {
        List<Task> taskList = new ArrayList<>();
        taskList.addAll(tasks.values());
        return taskList;
    }

    public static int addTask (Task task) {
        int id = currentId++; //генерируем id новой задачи
        task.setId(id); //устанавливаем этот id
        tasks.put(id, task); //добавляем новую задачу список всех задач
        return id;
    }

    public static Task getTask (int taskId) {
        if (tasks.containsKey(taskId)) {
            return tasks.get(taskId);
        }
        return null;
    }

    public static Task deleteTask (int taskId) {
        if (tasks.containsKey(taskId)) {
            tasks.remove(taskId);
        } else {
            System.out.println("Данная задача не найдена!");
        }
        return null;
    }

    public static Task putTask (int taskId) {
        Task task = tasks.get(taskId); //находим задачу, которую требуется изменить
        tasks.put(taskId, task);
        return null;
    }
}