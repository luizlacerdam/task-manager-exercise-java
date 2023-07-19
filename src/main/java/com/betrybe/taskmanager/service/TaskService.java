package com.betrybe.taskmanager.service;

import com.betrybe.taskmanager.database.FakeTaskDatabase;
import com.betrybe.taskmanager.database.TaskDatabaseInterface;
import com.betrybe.taskmanager.dto.TaskDto;
import com.betrybe.taskmanager.model.TaskModel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe service.
 */
@Service
public class TaskService {

  TaskDatabaseInterface fakeTaskDatabase;

  @Autowired
  public TaskService (TaskDatabaseInterface fakeTaskDatabase) {
    this.fakeTaskDatabase = fakeTaskDatabase;
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
