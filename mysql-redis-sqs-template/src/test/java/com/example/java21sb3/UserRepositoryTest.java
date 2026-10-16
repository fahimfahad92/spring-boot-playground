package com.example.java21sb3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.java21sb3.db.UserRepository;
import com.example.java21sb3.user.User;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryTest extends ContainerInitializer {

  @Autowired UserRepository userRepository;

  @Test
  void mysqlTestContainerRunning() {
    assertTrue(mySQLContainer.isRunning());
  }

  @Test
  void shouldCreateAndFindUsers() {
    String first = "Fahim" + UUID.randomUUID();
    String second = "Fahim" + UUID.randomUUID();

    userRepository.saveAll(List.of(new User(first), new User(second)));

    // The database is shared by all test classes, so check our own rows, not the total count.
    assertNotNull(userRepository.findByName(first));
    assertEquals(second, userRepository.findByName(second).getName());
  }
}
