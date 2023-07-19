package com.betrybe.taskmanager.controller;

import com.betrybe.taskmanager.dto.TaskDto;
import com.betrybe.taskmanager.service.TaskService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class controller.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

  TaskService service;

  /**
   * Constructor.
   */
  @Autowired
  public TaskController (TaskService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<TaskDto>> getAllTasks() {
    List<TaskDto> allTasks = service.geAllTasks();
    return ResponseEntity.ok(allTasks);
  }

}
