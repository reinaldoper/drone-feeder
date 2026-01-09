package com.drone.feeder.projetodronefeeder.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Manipulador global de exceções da aplicação.
 * Captura e trata exceções específicas e genéricas lançadas pelos controllers,
 * retornando respostas padronizadas em formato JSON.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  // =======================
  // Exceções de Usuário
  // =======================

  /**
   * Trata exceções de usuário não encontrado.
   *
   * @param ex exceção UserNotFound lançada quando o usuário não é localizado.
   * @return resposta HTTP 404 com detalhes do erro.
   */
  @ExceptionHandler(UserNotFound.class)
  public ResponseEntity<Map<String, Object>> handleUserNotFound(UserNotFound ex) {
    return buildResponse(HttpStatus.NOT_FOUND, "Usuário não encontrado",
        ex.getMessage());
  }

  /**
   * Trata exceções de usuário já existente.
   *
   * @param e exceção UserAlreadyExistsException lançada quando o e-mail já está cadastrado.
   * @return resposta HTTP 409 com detalhes do erro.
   */
  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<Map<String, Object>> handleUserAlreadyExists(UserAlreadyExistsException e) {
    return buildResponse(HttpStatus.CONFLICT, "Usuário já cadastrado", e.getMessage());
  }

  /**
   * Trata exceções de dados inválidos no cadastro ou atualização de usuário.
   *
   * @param e exceção InvalidUserDataException lançada quando os dados são inválidos.
   * @return resposta HTTP 400 com detalhes do erro.
   */
  @ExceptionHandler(InvalidUserDataException.class)
  public ResponseEntity<Map<String, Object>> handleInvalidUserData(InvalidUserDataException e) {
    return buildResponse(HttpStatus.BAD_REQUEST, "Dados inválidos", e.getMessage());
  }

  // =======================
  // Exceções de Drone
  // =======================

  /**
   * Trata exceções de drone não encontrado.
   *
   * @param ex exceção DroneNotFound lançada quando o drone não é localizado.
   * @return resposta HTTP 404 com detalhes do erro.
   */
  @ExceptionHandler(DroneNotFound.class)
  public ResponseEntity<Map<String, Object>> handleDroneNotFound(DroneNotFound ex) {
    return buildResponse(HttpStatus.NOT_FOUND, "Drone não encontrado",
        ex.getMessage());
  }

  /**
   * Trata exceções de drone nulo ou inválido.
   *
   * @param ex exceção DroneNull lançada quando os dados do drone são inválidos.
   * @return resposta HTTP 417 com detalhes do erro.
   */
  @ExceptionHandler(DroneNull.class)
  public ResponseEntity<Map<String, Object>> handleDroneNull(DroneNull ex) {
    return buildResponse(HttpStatus.EXPECTATION_FAILED, "Drone inválido",
        ex.getMessage());
  }

  // =======================
  // Exceções de Entrega
  // =======================

  /**
   * Trata exceções de entrega não encontrada.
   *
   * @param ex exceção EntregaNotFound lançada quando a entrega não é localizada.
   * @return resposta HTTP 404 com detalhes do erro.
   */
  @ExceptionHandler(EntregaNotFound.class)
  public ResponseEntity<Map<String, Object>> handleEntregaNotFound(EntregaNotFound ex) {
    return buildResponse(HttpStatus.NOT_FOUND, "Entrega não encontrada",
        ex.getMessage());
  }

  /**
   * Trata exceções de entrega nula ou inválida.
   *
   * @param ex exceção EntregaNull lançada quando os dados da entrega são inválidos.
   * @return resposta HTTP 417 com detalhes do erro.
   */
  @ExceptionHandler(EntregaNull.class)
  public ResponseEntity<Map<String, Object>> handleEntregaNull(EntregaNull ex) {
    return buildResponse(HttpStatus.EXPECTATION_FAILED, "Entrega inválida",
        ex.getMessage());
  }

  /**
   * Trata exceções de relacionamento inválido com entrega.
   *
   * @param e exceção RelacionadoEntrega lançada quando há erro de vínculo com a entrega.
   * @return resposta HTTP 400 com detalhes do erro.
   */
  @ExceptionHandler(RelacionadoEntrega.class)
  public ResponseEntity<Map<String, Object>> handleRelacionamentoEntrega(RelacionadoEntrega e) {
    return buildResponse(HttpStatus.BAD_REQUEST, "Erro de relacionamento com entrega",
        e.getMessage());
  }

  /**
   * Trata exceções ao alterar o status de uma entrega.
   *
   * @param ex exceção StatusError lançada quando ocorre erro ao atualizar o status.
   * @return resposta HTTP 400 com detalhes do erro.
   */
  @ExceptionHandler(StatusError.class)
  public ResponseEntity<Map<String, Object>> handleStatusError(StatusError ex) {
    return buildResponse(HttpStatus.BAD_REQUEST, "Erro ao alterar status",
        ex.getMessage());
  }

  // =======================
  // Exceções de Vídeo
  // =======================

  /**
   * Trata exceções de vídeo não encontrado.
   *
   * @param ex exceção VideoNotFound lançada quando o vídeo não é localizado.
   * @return resposta HTTP 404 com detalhes do erro.
   */
  @ExceptionHandler(VideoNotFound.class)
  public ResponseEntity<Map<String, Object>> handleVideoNotFound(VideoNotFound ex) {
    return buildResponse(HttpStatus.NOT_FOUND, "Vídeo não encontrado",
        ex.getMessage());
  }

  /**
   * Trata exceções de vídeo nulo ou inválido.
   *
   * @param ex exceção VideoNull lançada quando os dados do vídeo são inválidos.
   * @return resposta HTTP 417 com detalhes do erro.
   */
  @ExceptionHandler(VideoNull.class)
  public ResponseEntity<Map<String, Object>> handleVideoNull(VideoNull ex) {
    return buildResponse(HttpStatus.EXPECTATION_FAILED, "Vídeo inválido",
        ex.getMessage());
  }

  // =======================
  // Validação e Genéricos
  // =======================

  /**
   * Trata exceções de validação de campos com anotações como @NotBlank e @Email.
   *
   * @param e exceção MethodArgumentNotValidException lançada quando a validação falha.
   * @return resposta HTTP 400 com a primeira mensagem de erro encontrada.
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException e) {
    String message = e.getBindingResult().getFieldErrors().stream()
        .map(error -> error.getField() + ": "
            + error.getDefaultMessage())
        .findFirst()
        .orElse("Erro de validação");

    return buildResponse(HttpStatus.BAD_REQUEST, "Dados inválidos", message);
  }

  /**
   * Trata exceções internas da aplicação.
   *
   * @param ex exceção InternalException lançada para erros inesperados.
   * @return resposta HTTP 500 com mensagem genérica.
   */
  @ExceptionHandler(InternalException.class)
  public ResponseEntity<Map<String, Object>> handleInternal(InternalException ex) {
    return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno",
        "Ocorreu um erro inesperado.");
  }

  /**
   * Trata exceções genéricas não mapeadas.
   *
   * @param ex exceção genérica lançada durante a execução.
   * @return resposta HTTP 500 com detalhes do erro.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
    return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro inesperado",
        ex.getMessage());
  }

  /**
   * Constrói uma resposta padronizada com base nos parâmetros fornecidos.
   *
   * @param status  código de status HTTP a ser retornado.
   * @param error   descrição do tipo de erro ocorrido.
   * @param message mensagem detalhada da exceção.
   * @return objeto ResponseEntity com corpo JSON contendo os detalhes do erro.
   */
  private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status,
                                                            String error, String message) {
    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", status.value());
    body.put("error", error);
    body.put("message", message);
    return ResponseEntity.status(status).body(body);
  }
}
