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
import com.drone.feeder.projetodronefeeder.controller.AdicionaTarefa;
import com.drone.feeder.projetodronefeeder.controller.UpdateTarefa;
import com.drone.feeder.projetodronefeeder.model.Drone;
import com.drone.feeder.projetodronefeeder.model.Entrega;
import com.drone.feeder.projetodronefeeder.model.Video;
import com.drone.feeder.projetodronefeeder.repository.DroneRepository;
import com.drone.feeder.projetodronefeeder.repository.EntregaRepository;
import com.drone.feeder.projetodronefeeder.repository.VideoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EntregaTest {

  @Autowired
  private MockMvc mockMvc;

  @SpyBean
  EntregaRepository entregaRepo;

  @SpyBean
  DroneRepository droneRepo;

  @SpyBean
  VideoRepository videoRepo;

  @Captor
  private ArgumentCaptor<Entrega> entregaCaptor;

  @BeforeEach
  public void setup() {

    entregaRepo.deleteAll();

  }

  @Test
  @Order(1)
  @DisplayName("1 - Deve retornar lista vazia de entregas.")
  void deveRetornarListaVaziaEntregaNaBaseDeDados() throws Exception {
    mockMvc.perform(get("/entregas").contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(content().string(containsString("[]")));

  }

  @Test
  @Order(2)
  @DisplayName("2 - Deve retornar erro quando não existe o id informado.")
  void deveRetornarErroQuandoVideoNaoExistirNaBase() throws Exception {
    mockMvc.perform(delete("/entregas/" + new Random().nextInt())).andExpect(status().isNotFound())
        .andExpect(jsonPath("$.error").value("Entrega não existe"));
  }

  @Test
  @Order(3)
  @DisplayName("3 - Deve retornar erro quando id informado não for valido(delete).")
  void deveRetornarErroQuandoIdNaoENumeroNaBase() throws Exception {
    mockMvc.perform(delete("/entregas/" + new Random().toString()))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.error").value("Digite um número inteiro válido."));
  }



  @Test
  @Order(5)
  @DisplayName("5 - Deve retornar erro quando não existe o id informado(delete).")
  void deveRetornarErroQuandoEntregaNaoExistirNaBase() throws Exception {
    mockMvc.perform(delete("/entregas/" + new Random().nextInt())).andExpect(status().isNotFound())
        .andExpect(jsonPath("$.error").value("Entrega não existe"));
  }

  @Test
  @Order(6)
  @DisplayName("4 - Deve retornar entrega, por um id existente informado.")
  void deveRetornarEntregaIdQuandoExistirNaBase() throws Exception {
    final var entrega = new Entrega();
    entregaRepo.save(entrega);

    mockMvc.perform(get("/entregas/" + entrega.getId())).andExpect(status().isOk());
  }

  @Test
  @Order(7)
  @DisplayName("5 - Deve retornar erro quando não existe o id informado(get).")
  void deveRetornarErroQuandoEntregaNaoExistirNaBase1() throws Exception {
    mockMvc.perform(get("/entregas/" + new Random().nextInt())).andExpect(status().isNotFound())
        .andExpect(jsonPath("$.error").value("Entrega não existe"));
  }

  @Test
  @Order(8)
  @DisplayName("8 - Deve retornar erro quando id informado não for valido(get).")
  void deveRetornarErroQuandoIdNaoENumeroNaBase1() throws Exception {
    mockMvc.perform(get("/entregas/" + new Random().toString())).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.error").value("Digite um número inteiro válido."));
  }

  @Test
  @Order(9)
  @DisplayName("9 - Deve retornar erro quando não existe o id informado do drone(get).")
  void deveRetornarErroQuandoEntregaDroneIdNaoExistirNaBase1() throws Exception {
    mockMvc.perform(get("/entregas/drones" + new Random().nextInt()))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.error").value("Digite um número inteiro válido."));
  }

  @Test
  @Order(10)
  @DisplayName("10 - Deve retornar erro quando não existe o id informado do drone não for válido(get).")
  void deveRetornarErroQuandoEntregaDroneIdNaoValidoNaBase1() throws Exception {
    mockMvc.perform(get("/entregas/drones" + new Random().toString()))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.error").value("Digite um número inteiro válido."));
  }

  @Test
  @Order(11)
  @DisplayName("11 -  Deve adicionar uma Entrega na base de dados.")
  void deveAdicionarEntregaNaBaseDeDados() throws Exception {
    final var drone = new Drone(37.7749, -123.4549, "image1");
    droneRepo.save(drone);
    final var video = new Video("video1.mp4");
    videoRepo.save(video);
    final var entrega = new AdicionaTarefa("pendente", drone.getId(), video.getId());
    mockMvc
        .perform(post("/entregas").contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(entrega)))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.Message").value("Inserido com sucesso!"));
  }

  @Test
  @Order(12)
  @DisplayName("12 -  Deve retornar erro ao adicionar 'status' errado.")
  void deveRetornarErroStatusNaBaseDeDados() throws Exception {
    final var drone = new Drone(37.7749, -123.4549, "image2");
    droneRepo.save(drone);
    final var video = new Video("video1.mp4");
    videoRepo.save(video);
    final var entrega = new AdicionaTarefa("em andamento", drone.getId(), video.getId());
    mockMvc
        .perform(post("/entregas").contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(entrega)))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.error").value("O status deve ser /pendente/ ou /entregue/"));
  }

  @Test
  @Order(13)
  @DisplayName("13 - Deve retornar entregas de um drone expecifico.")
  void deveRetornarEntregaDroneExpecificoExistirNaBase1() throws Exception {
    final var drone = new Drone(37.7749, -123.4549, "image3");
    droneRepo.save(drone);
    final var video = new Video("video1.mp4");
    videoRepo.save(video);
    final var entrega = new Entrega();
    entregaRepo.save(entrega);
    mockMvc.perform(get("/entregas/drones/" + drone.getId())).andExpect(status().isOk());
  }

  @Test
  @Order(14)
  @DisplayName("14 - Deve retornar erro quando drone expecifico não existe.")
  void deveRetornarErroDroneExpecificoExistirNaBase1() throws Exception {
    final var drone = new Drone(37.7749, -123.4549, "image4");
    droneRepo.save(drone);
    final var video = new Video("video1.mp4");
    videoRepo.save(video);
    final var entrega = new Entrega();
    entregaRepo.save(entrega);
    mockMvc.perform(get("/entregas/drones/" + new Random().nextInt()))
        .andExpect(status().isNotFound()).andExpect(jsonPath("$.error").value("Drone não existe"));
  }

}
