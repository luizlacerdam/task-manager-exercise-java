package com.betrybe.taskmanager;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.lang.reflect.Field;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaskDtoTest {
  @Test
  @DisplayName("1 - Crie o DTO (Data Transfer Objects) de resposta para tarefas")
  void testTaskDto() {
    try {
      Class<?> sut = Class.forName("com.betrybe.taskmanager.dto.TaskDto");
      assertTrue(sut.isRecord(), "O DTO deve ser definido como um 'Record'");

      Field[] dtoFields = sut.getDeclaredFields();
      assertTrue(Arrays.stream(dtoFields).anyMatch(field -> field.getName().equals("id")),
          "O DTO deve possuir o campo 'id'");
      assertTrue(Arrays.stream(dtoFields).anyMatch(field -> field.getName().equals("title")),
          "O DTO deve possuir o campo 'title'");
      assertTrue(Arrays.stream(dtoFields).anyMatch(field -> field.getName().equals("description")),
          "O DTO deve possuir o campo 'description'");
      assertTrue(Arrays.stream(dtoFields).anyMatch(field -> field.getName().equals("ownerName")),
          "O DTO deve possuir o campo 'ownerName'");
      assertTrue(Arrays.stream(dtoFields).anyMatch(field -> field.getName().equals("isComplete")),
          "O DTO deve possuir o campo 'isComplete'");

    } catch (ClassNotFoundException e) {
      fail("Esperava encontrar a classe " + e.getMessage());
    }
  }


  @Test
  @DisplayName("4 - Crie o DTO (Data Transfer Objects) de requisição para tarefas")
  void testTaskCreationDto() {
    try {
      Class<?> sut = Class.forName("com.betrybe.taskmanager.dto.TaskCreationDto");
      assertTrue(sut.isRecord(), "O DTO deve ser definido como um 'Record'");

      Field[] dtoFields = sut.getDeclaredFields();
      assertTrue(Arrays.stream(dtoFields).anyMatch(field -> field.getName().equals("title")),
          "O DTO deve possuir o campo 'title'");
      assertTrue(Arrays.stream(dtoFields).anyMatch(field -> field.getName().equals("description")),
          "O DTO deve possuir o campo 'description'");
      assertTrue(Arrays.stream(dtoFields).anyMatch(field -> field.getName().equals("ownerName")),
          "O DTO deve possuir o campo 'ownerName'");

    } catch (ClassNotFoundException e) {
      fail("Esperava encontrar a classe " + e.getMessage());
    }
  }
}
