package com.drone.feeder.projetodronefeeder.controller;

import com.drone.feeder.projetodronefeeder.dto.LoginRequest;
import com.drone.feeder.projetodronefeeder.dto.SaveRequest;
import com.drone.feeder.projetodronefeeder.dto.UpdateRequest;
import com.drone.feeder.projetodronefeeder.exceptions.InternalException;
import com.drone.feeder.projetodronefeeder.exceptions.UserNotFound;
import com.drone.feeder.projetodronefeeder.model.User;
import com.drone.feeder.projetodronefeeder.service.UserService;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável pelas operações de autenticação e gerenciamento de usuários.
 */
@RestController
@RequestMapping("/auth")
public class UserController {

  @Autowired
  private UserService userService;

  /**
   * Cria um novo usuário.
   *
   * @param saveRequest dados do usuário
   * @return mensagem de sucesso
   */
  @CrossOrigin
  @PostMapping
  public ResponseEntity<HashMap<String, String>> saveUser(@RequestBody SaveRequest saveRequest) {
    try {
      userService.saveUser(saveRequest);
      HashMap<String, String> response = new HashMap<>();
      response.put("mensagem", "Usuário salvo com sucesso!");
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } catch (Exception e) {
      throw new UserNotFound(e.getMessage());
    }
  }

  /**
   * Busca um usuário pelo ID.
   *
   * @param id identificador do usuário
   * @return usuário encontrado
   */
  @CrossOrigin
  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Integer id) {
    try {
      User user = userService.getUserById(id);
      return ResponseEntity.status(HttpStatus.OK).body(user);
    } catch (NumberFormatException e) {
      throw new InternalException();
    }
  }

  /**
   * Realiza login e retorna o token JWT.
   *
   * @param loginRequest dados de login
   * @return token JWT
   */
  @CrossOrigin
  @PostMapping("/login")
  public ResponseEntity<HashMap<String, String>> login(@RequestBody LoginRequest loginRequest) {
    String email = loginRequest.getEmail();
    String password = loginRequest.getPassword();
    String token = userService.login(email, password);
    HashMap<String, String> msg = new HashMap<>();
    msg.put("token", token);
    return ResponseEntity.status(HttpStatus.OK).body(msg);
  }

  /**
   * Atualiza os dados de um usuário.
   *
   * @param updateRequest dados atualizados
   * @param id            identificador do usuário
   * @return usuário atualizado
   */
  @CrossOrigin
  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@RequestBody UpdateRequest updateRequest,
                                         @PathVariable Integer id) {
    try {
      User user = userService.updateUser(updateRequest, id);
      return ResponseEntity.status(HttpStatus.OK).body(user);
    } catch (NumberFormatException e) {
      throw new InternalException();
    }
  }

  /**
   * Deleta um usuário pelo ID.
   *
   * @param id identificador do usuário
   * @return mensagem de sucesso
   */
  @CrossOrigin
  @DeleteMapping("/{id}")
  public ResponseEntity<HashMap<String, String>> deleteUser(@PathVariable Integer id) {
    try {
      String userDeletado = userService.deleteUser(id);
      HashMap<String, String> msg = new HashMap<>();
      msg.put("mensagem", userDeletado);
      return ResponseEntity.status(HttpStatus.OK).body(msg);
    } catch (NumberFormatException e) {
      throw new InternalException();
    }
  }
}
