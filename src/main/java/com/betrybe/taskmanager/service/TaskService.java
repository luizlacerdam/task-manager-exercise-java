package com.betrybe.taskmanager.service;

import com.betrybe.taskmanager.database.FakeTaskDatabase;
import com.betrybe.taskmanager.database.TaskDatabaseInterface;
import com.betrybe.taskmanager.dto.TaskDto;
import com.betrybe.taskmanager.model.TaskModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

/**
 * Classe service.
 */
@Service
public class TaskService {

  TaskDatabaseInterface fakeTaskDatabase;

  /**
   * Constructor.
   */
  @Autowired
  public TaskService (TaskDatabaseInterface fakeTaskDatabase) {
    this.fakeTaskDatabase = fakeTaskDatabase;
  }

  public TaskDto getTaskById(Long id) {
//  Optional<TaskModel> taskOptional = fakeTaskDatabase.getTaskById(id.toString());
    TaskModel taskModel = fakeTaskDatabase.getTaskById(id.toString());
    TaskDto taskDto = new TaskDto(
        taskModel.getId(),
        taskModel.getTitle(),
        taskModel.getDescription(),
        taskModel.getOwnerName(),
        taskModel.getIsCompleted()
    );
    return taskDto;
  }

  public List<TaskDto> geAllTasks() {
    List<TaskDto> allTaskDto = new ArrayList<>();

    for (TaskModel task : fakeTaskDatabase.getAllTasks()) {
      allTaskDto.add(
          new TaskDto(
              task.getId(),
              task.getTitle(),
              task.getDescription(),
              task.getOwnerName(),
              task.getIsCompleted()
          )
      );
    }
    return allTaskDto;
  }
}
