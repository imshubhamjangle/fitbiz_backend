package auth.service;

import javax.servlet.http.HttpServletRequest;
import auth.dto.UserSessionDTO;
import auth.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import auth.exception.CustomException;
import auth.model.User;
import auth.repository.UserRepository;
import auth.security.JwtTokenProvider;
import java.util.Collections;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  public UserSessionDTO signin(String username, String password) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      String token = jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());

      UserSessionDTO userSessionDTO = new UserSessionDTO();
      userSessionDTO.setUsername(username);
      userSessionDTO.setToken(token);

      return userSessionDTO;
    } catch (AuthenticationException e) {
      throw new CustomException("Invalid username/password supplied", HttpStatus.FAILED_DEPENDENCY);
    }
  }

  public UserSessionDTO signup(User user) {
    if (userRepository.existsByUsername(user.getUsername())) {
      throw new CustomException("Username is already in use", HttpStatus.LOCKED);
    }
    if (userRepository.existsByEmail(user.getEmail())) {
      throw new CustomException("Email is already in use", HttpStatus.EXPECTATION_FAILED);
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRoles(Collections.singletonList(Role.ROLE_CLIENT));   //Set role as Client by default
    userRepository.save(user);

    //returning object UserSessionDTO
    String token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
    UserSessionDTO userSessionDTO = new UserSessionDTO();
    userSessionDTO.setUsername(user.getUsername());
    userSessionDTO.setToken(token);
    return userSessionDTO;
  }

  public void delete(String username) {
    userRepository.deleteByUsername(username);
  }

  public User search(String username) {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
    return user;
  }

  public User whoami(HttpServletRequest req) {
    return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
  }

  public String refresh(String username) {
    return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
  }

}
