package com.betrybe.taskmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import com.betrybe.taskmanager.database.FakeTaskDatabase;
import com.betrybe.taskmanager.model.TaskModel;
import java.lang.reflect.Constructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApiTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private FakeTaskDatabase fakeTaskDatabase;

  private String testId;

  private String getUrl() {
    return "http://localhost:" + port + "/tasks";
  }

  private Object createTaskDto(String title, String description, String ownerName)
      throws Exception {
    Class<?> TaskCreationDto = Class.forName("com.betrybe.taskmanager.dto.TaskCreationDto");
    Constructor<?> TaskCreationDtoCtor =
        TaskCreationDto.getDeclaredConstructor(String.class, String.class, String.class);
    Object task = TaskCreationDtoCtor.newInstance(title, description, ownerName);
    return task;
  }

  private String taskJson(String id) {
    return String.format(
        "{\"id\":\"%s\",\"title\":\"Mock de tarefa\",\"description\":\"Mock a ser usado nos testes\",\"ownerName\":\"Trybe\",\"isComplete\":false}",
        id);
  }

  private String taskJson(String id, String isComplete) {
    return String.format(
        "{\"id\":\"%s\",\"title\":\"Mock de tarefa\",\"description\":\"Mock a ser usado nos testes\",\"ownerName\":\"Trybe\",\"isComplete\":%s}",
        id, isComplete);
  }


  private String taskJson(String id, String title, String description) {
    return String.format(
        "{\"id\":\"%s\",\"title\":\"%s\",\"description\":\"%s\",\"ownerName\":\"Trybe\",\"isComplete\":false}",
        id, title, description);
  }

  @BeforeEach
  public void setup() {
    // Inject fake data into the FakeDatabase
    TaskModel task =
        fakeTaskDatabase.createTask("Mock de tarefa", "Mock a ser usado nos testes", "Trybe");
    testId = task.getId();

  }

  @Test
  @DisplayName("2 - Crie o endpoint GET /tasks para retornar todos as tarefas")
  void testGetAllTasks() throws Exception {
    ResponseEntity<String> respostaParaListaDeTarefas =
        restTemplate.getForEntity(getUrl(), String.class);
    assertEquals(HttpStatus.OK, respostaParaListaDeTarefas.getStatusCode());
    assertEquals("[" + taskJson(testId) + "]", respostaParaListaDeTarefas.getBody());
  }

  @Test
  @DisplayName("3 - Crie o endpoint GET /tasks/{id} para retornar a tarefa com determinado id")
  void testGetTaskById() throws Exception {
    ResponseEntity<String> respostaParaEntidadeQueExiste =
        restTemplate.getForEntity(getUrl() + "/" + testId, String.class);
    assertEquals(HttpStatus.OK, respostaParaEntidadeQueExiste.getStatusCode());
    assertEquals(taskJson(testId), respostaParaEntidadeQueExiste.getBody());

    ResponseEntity<String> respostaParaEntidadeQueNaoExiste =
        restTemplate.getForEntity(getUrl() + "/" + "id-invalido", String.class);
    assertNotEquals(HttpStatus.OK, respostaParaEntidadeQueNaoExiste.getStatusCode());
  }


  @Test
  @DisplayName("5 - Crie o endpoint POST /tasks para criar novas tarefas")
  void testPostTask() throws Exception {
    Object task = createTaskDto("Teste", "Requisição teste do requisito 5", "Trybe");
    ResponseEntity<String> respostaParaCriacaoDeNovaTarefa =
        restTemplate.postForEntity(getUrl(), task, String.class);

    assertEquals(HttpStatus.CREATED, respostaParaCriacaoDeNovaTarefa.getStatusCode());
    String taskId = respostaParaCriacaoDeNovaTarefa.getBody();

    ResponseEntity<String> respostaParaGetDaTarefaCriada =
        restTemplate.getForEntity(getUrl() + "/" + taskId, String.class);
    assertEquals(HttpStatus.OK, respostaParaGetDaTarefaCriada.getStatusCode());
    assertEquals(taskJson(taskId, "Teste", "Requisição teste do requisito 5"),
        respostaParaGetDaTarefaCriada.getBody());
  }

  @Test
  @DisplayName("6 - Crie o endpoint PUT /tasks/{id} para alterar o status da tarefa para 'Concluída'")
  void testPutTask() throws Exception {
    restTemplate.put(getUrl() + "/" + testId, null);
    ResponseEntity<String> respostaParaGetDaTarefaAlterada =
        restTemplate.getForEntity(getUrl() + "/" + testId, String.class);
    assertEquals(HttpStatus.OK, respostaParaGetDaTarefaAlterada.getStatusCode());
    assertEquals(taskJson(testId, "true"), respostaParaGetDaTarefaAlterada.getBody());
  }

  @Test
  @DisplayName("7 - Crie o endpoint DELETE /tasks/{id} para a remoção de tarefas")
  void testDeleteTask() throws Exception {
    ResponseEntity<String> respostaParaRemocaoDaTarefa =
        restTemplate.exchange(RequestEntity.delete(getUrl() + "/" + testId).build(), String.class);
    assertEquals(HttpStatus.NO_CONTENT, respostaParaRemocaoDaTarefa.getStatusCode());

    ResponseEntity<String> respostaParaGetDaTarefaRemovida =
        restTemplate.getForEntity(getUrl() + "/" + testId, String.class);
    assertNotEquals(HttpStatus.OK, respostaParaGetDaTarefaRemovida.getStatusCode());
  }
}
