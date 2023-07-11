package com.betrybe.taskmanager.model;

import java.util.UUID;

/**
 * Defines TaskModel model. This is the internal representation of a Task with getters and setters
 * to provide access to the data.
 */
public class TaskModel {
  String id;
  String title;
  String description;
  String ownerName;
  Boolean isCompleted;

  /**
   * This is the Task class constructor.
   *
   * @param title Task title
   * @param description Task description
   * @param ownerName Task owner name
   */
  public TaskModel(String title, String description, String ownerName) {
    this.id = UUID.randomUUID().toString();
    this.title = title;
    this.description = description;
    this.ownerName = ownerName;
    this.isCompleted = false;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getOwnerName() {
    return ownerName;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }

  public Boolean getIsCompleted() {
    return isCompleted;
  }

  public void setIsCompleted(Boolean isCompleted) {
    this.isCompleted = isCompleted;
  }

}
