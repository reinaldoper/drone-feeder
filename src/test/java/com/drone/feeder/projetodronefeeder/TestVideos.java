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
import com.drone.feeder.projetodronefeeder.model.Drone;
import com.drone.feeder.projetodronefeeder.model.Video;
import com.drone.feeder.projetodronefeeder.repository.VideoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestVideos {

  @Autowired
  private MockMvc mockMvc;

  @SpyBean
  VideoRepository videoRepo;

  @Captor
  private ArgumentCaptor<Video> videoCaptor;

  @BeforeEach
  public void setup() {
    videoRepo.deleteAll();
  }

  @Test
  @Order(1)
  @DisplayName("1 -  Deve adicionar um Video na base de dados.")
  void deveAdicionarVideoNaBaseDeDados() throws Exception {
    final var video = new Video("video1.mp4");
    mockMvc
        .perform(post("/videos").contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(video)))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.Message").value("Inserido com sucesso!"));

    verify(videoRepo, atLeast(1)).save(videoCaptor.capture());

    assertThat(videoCaptor.getValue()).isNotNull();
    assertThat(videoCaptor.getValue().getId()).isNotNull();
  }

  @Test
  @Order(2)
  @DisplayName("2 - Deve retornar todos os videos existentes da base de dados.")
  void deveRetornarTodosVideosExistentesNaBase() throws Exception {
    final var video = new Video("video1.mp4");
    final var video1 = new Video("video11.mp4");
    videoRepo.save(video1);
    videoRepo.save(video);

    mockMvc.perform(get("/videos").contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(jsonPath("$[0].nomeArquivo").value(video1.getNomeArquivo()))
        .andExpect(jsonPath("$[1].nomeArquivo").value(video.getNomeArquivo()));
  }

  @Test
  @Order(3)
  @DisplayName("3 - Deve retornar lista vazia quando não existir videos na base de dados.")
  void deveRetornarListaVaziaQuandoNaoExistirVideosNaBase() throws Exception {
    mockMvc.perform(get("/videos").contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(content().string(containsString("[]")));
  }

  @Test
  @Order(4)
  @DisplayName("4 - Deve remover video, por um id existente informado.")
  void deveRemoverVideoQuandoExistirNaBase() throws Exception {
    final var video = new Video("video1.mp4");
    videoRepo.save(video);

    mockMvc.perform(delete("/videos/" + video.getId())).andExpect(status().isOk());
  }

  @Test
  @Order(5)
  @DisplayName("5 - Deve retornar erro quando não existe o id informado.")
  void deveRetornarErroQuandoVideoNaoExistirNaBase() throws Exception {
    mockMvc.perform(delete("/videos/" + new Random().nextInt())).andExpect(status().isNotFound())
        .andExpect(jsonPath("$.error").value("Video não existe"));
  }

  @Test
  @Order(6)
  @DisplayName("6 - Deve retornar erro quando id informado não for valido.")
  void deveRetornarErroQuandoIdNaoENumeroNaBase() throws Exception {
    mockMvc.perform(delete("/videos/" + new Random().toString())).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.error").value("Digite um número inteiro válido."));
  }

}
