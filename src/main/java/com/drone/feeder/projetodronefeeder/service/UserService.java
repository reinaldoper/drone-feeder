package com.drone.feeder.projetodronefeeder.service;

import com.drone.feeder.projetodronefeeder.config.JwtUtil;
import com.drone.feeder.projetodronefeeder.dto.SaveRequest;
import com.drone.feeder.projetodronefeeder.dto.UpdateRequest;
import com.drone.feeder.projetodronefeeder.dto.UserResponse;
import com.drone.feeder.projetodronefeeder.exceptions.InvalidUserDataException;
import com.drone.feeder.projetodronefeeder.exceptions.UserAlreadyExistsException;
import com.drone.feeder.projetodronefeeder.exceptions.UserNotFound;
import com.drone.feeder.projetodronefeeder.model.User;
import com.drone.feeder.projetodronefeeder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe responsável pelas operações de usuário.
 */
@Service
@Transactional
public class UserService {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JwtUtil jwtUtil;

  /**
   * Salva um novo usuário no banco de dados.
   *
   * @param request dados do usuário
   */
  public void saveUser(SaveRequest request) {
    String name = request.getName();
    String email = request.getEmail();
    String password = request.getPassword();

    if (name == null || name.isEmpty()
        || email == null || email.isEmpty()
        || password == null || password.isEmpty()) {
      throw new InvalidUserDataException("Os campos não podem ser vazios.");
    }

    boolean emailExists = userRepository.existsByEmail(email);
    if (emailExists) {
      throw new UserAlreadyExistsException("E-mail já está em uso.");
    }

    String hashedPassword = passwordEncoder.encode(password);
    User user = new User(name, hashedPassword, email);
    userRepository.save(user);
  }

  /**
   * Busca um usuário pelo ID.
   *
   * @param id identificador do usuário
   * @return usuário encontrado
   */
  public UserResponse getUserById(Integer id) {
    User userId = userRepository.findById(id).orElse(null);
    if (userId == null) {
      throw new UserNotFound("Usuário não encontrado.");
    }
    return new UserResponse(userId.getId(), userId.getName(), userId.getEmail());
  }

  /**
   * Busca um usuário pelo e-mail.
   *
   * @param email e-mail do usuário
   * @return usuário encontrado
   */
  public User getUserByEmail(String email) {
    User emailValid = userRepository.findByEmail(email).orElse(null);
    if (emailValid == null) {
      throw new UserNotFound("Usuario não encontrado.");
    }
    return emailValid;
  }

  /**
   * Deleta um usuário pelo ID.
   *
   * @param id identificador do usuário
   * @return mensagem de sucesso
   */
  public String deleteUser(Integer id) {
    User userId = userRepository.findById(id).orElse(null);
    if (userId == null) {
      throw new UserNotFound("Usuário não encontrado.");
    }
    userRepository.delete(userId);
    return "Usuário deletado com sucesso.";
  }

  /**
   * Atualiza os dados de um usuário.
   *
   * @param request dados atualizados
   * @param id identificador do usuário
   * @return usuário atualizado
   */
  public UserResponse updateUser(UpdateRequest request, Integer id) {
    User userUpdate = userRepository.findById(id).orElse(null);

    if (userUpdate == null) {
      throw  new UserNotFound("Usuario não encontrado");
    }

    if (request.getName() != null && !request.getName().isEmpty()) {
      userUpdate.setName(request.getName());
    }

    if (request.getEmail() != null && !request.getEmail().isEmpty()) {
      userUpdate.setEmail(request.getEmail());
    }

    if (request.getPassword() != null && !request.getPassword().isEmpty()) {
      String hashedPassword = passwordEncoder.encode(request.getPassword());
      userUpdate.setPassword(hashedPassword);
    }

    User user = userRepository.save(userUpdate);
    return new UserResponse(user.getId(), user.getName(), user.getEmail());
  }

  /**
   * Realiza login e retorna o token JWT.
   *
   * @param email e-mail do usuário
   * @param rawPassword senha em texto plano
   * @return token JWT
   */
  public String login(String email, String rawPassword) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UserNotFound("Usuário não encontrado."));

    if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
      throw new UserNotFound("Senha incorreta.");
    }

    return jwtUtil.generateToken(user.getEmail());
  }
}
