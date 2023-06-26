package com.drone.feeder.projetodronefeeder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import com.drone.feeder.projetodronefeeder.model.Drone;
import com.drone.feeder.projetodronefeeder.repository.DroneRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProjetoDroneFeederApplicationTests {

  @Autowired
  private MockMvc mockMvc;

  @SpyBean
  DroneRepository droneRepo;

  @Captor
  private ArgumentCaptor<Drone> serieCaptor;

  @BeforeEach
  public void setup() {
    droneRepo.deleteAll();
  }

  @Test
  @Order(1)
  @DisplayName("1 -  Deve adicionar um Drone na base de dados.")
  void deveAdicionarDroneNaBaseDeDados() throws Exception {
    final var drone = new Drone(37.7749, -123.4549);
    mockMvc
        .perform(post("/drones").contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(drone)))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.Message").value("Inserido com sucesso!"));

    verify(droneRepo, atLeast(1)).save(serieCaptor.capture());

    assertThat(serieCaptor.getValue()).isNotNull();
    assertThat(serieCaptor.getValue().getId()).isNotNull();
  }

  @Test
  @Order(2)
  @DisplayName("2 - Deve retornar todos os drones existentes da base de dados.")
  void deveRetornarTodosDronesExistentesNaBase() throws Exception {
    final var drone1 = new Drone(37.7749, -123.4549);
    final var drone2 = new Drone(3.7649, -12.4589);
    droneRepo.save(drone1);
    droneRepo.save(drone2);

    mockMvc.perform(get("/drones").contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(jsonPath("$[0].latitude").value(drone1.getLatitude()))
        .andExpect(jsonPath("$[1].latitude").value(drone2.getLatitude()));
  }

  @Test
  @Order(3)
  @DisplayName("3 - Deve retornar lista vazia quando não existir drones na base de dados.")
  void deveRetornarListaVaziaQuandoNaoExistirDronesNaBase() throws Exception {
    mockMvc.perform(get("/drones").contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(content().string(containsString("[]")));
  }

  @Test
  @Order(4)
  @DisplayName("4 - Deve remover drone, por um id existente informado.")
  void deveRemoverDroneQuandoExistirNaBase() throws Exception {
    final var drone = droneRepo.save(new Drone(3.7649, -12.4589));

    mockMvc.perform(delete("/drones/" + drone.getId())).andExpect(status().isOk());
  }

  @Test
  @Order(5)
  @DisplayName("5 - Deve retornar erro quando não existe o id informado.")
  void deveRetornarErroQuandoDroneNaoExistirNaBase() throws Exception {
    mockMvc.perform(delete("/drones/" + new Random().nextInt())).andExpect(status().isNotFound())
        .andExpect(jsonPath("$.error").value("Drone não existe"));
  }

  @Test
  @Order(6)
  @DisplayName("6 - Deve retornar erro quando id informado não for valido.")
  void deveRetornarErroQuandoIdNaoENumeroNaBase() throws Exception {
    mockMvc.perform(delete("/drones/" + new Random().toString())).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.error").value("Digite um número inteiro válido."));
  }

  @Test
  @Order(7)
  @DisplayName("7 - Deve retornar drone expecifico existente da base de dados.")
  void deveRetornarDroneExistenteNaBase() throws Exception {
    final var drone1 = new Drone(37.7749, -123.4549);

    droneRepo.save(drone1);


    mockMvc.perform(get("/drones/" + drone1.getId()).contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());


  }

}
