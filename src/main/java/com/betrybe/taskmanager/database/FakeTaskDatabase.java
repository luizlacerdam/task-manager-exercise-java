package com.betrybe.taskmanager.database;

import com.betrybe.taskmanager.model.TaskModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;


/**
 * Implements TaskDatabase interface but it is not a real database since it resets to initial state
 * when application resets (no data persistence).
 */
@Component
public class FakeTaskDatabase implements TaskDatabaseInterface {

  private Map<String, TaskModel> tasks = new HashMap<>();

  @Override
  public List<TaskModel> getAllTasks() {
    return new ArrayList<>(tasks.values());
  }

  @Override
  public TaskModel getTaskById(String id) {
    return tasks.get(id);
  }

  @Override
  public TaskModel createTask(String title, String description, String ownerName) {
    TaskModel newTask = new TaskModel(title, description, ownerName);
    tasks.put(newTask.getId(), newTask);
    return newTask;
  }

  @Override
  public TaskModel setTaskAsCompleted(String id) {
    tasks.get(id).setIsCompleted(null);
    return tasks.get(id);
  }

  @Override
  public TaskModel removeTaskById(String id) {
    TaskModel task = tasks.remove(id);
    System.out.println(task.getId());
    return task;
  }
}
