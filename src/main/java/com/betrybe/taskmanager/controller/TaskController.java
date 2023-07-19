package com.betrybe.taskmanager.controller;

import com.betrybe.taskmanager.dto.TaskCreationDto;
import com.betrybe.taskmanager.dto.TaskDto;
import com.betrybe.taskmanager.service.TaskService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @PutMapping("/{id}")
  public ResponseEntity<Void> completeTaskWithId(@PathVariable String id) {
    service.setTaskWithIdAsComplete(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<String> createTask(@RequestBody TaskCreationDto newTask) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.saveTask(newTask));
  }

  @GetMapping("/{id}")
  public ResponseEntity<TaskDto> getTaskById(@PathVariable String id) {
    TaskDto taskDto = service.getTaskById(id);
    return ResponseEntity.ok(taskDto);
  }

  @GetMapping
  public ResponseEntity<List<TaskDto>> getAllTasks() {
    List<TaskDto> allTasks = service.geAllTasks();
    return ResponseEntity.ok(allTasks);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> removeTaskById(@PathVariable String id) {
    service.deleteTaskById(id);
    return ResponseEntity.noContent().build();
  }

}
