package com.betrybe.taskmanager.dto;

public record TaskDto(String id, String title, String description,
                      String ownerName, Boolean isComplete) { }
