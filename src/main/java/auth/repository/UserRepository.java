package auth.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import auth.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);

  User findByUsername(String username);

  @Transactional
  void deleteByUsername(String username);

}
