package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private AppUserRepository repository;

    @Test
    void saveUser() {
        repository.save(new AppUser("test_user", "test_password"));
        assertThat(repository.findByUsername("user1").isPresent()).isTrue();
    }

    @Test
    void deleteUser() {
        repository.save(new AppUser("test_user", "test_password"));
        repository.deleteAll();
        assertThat(repository.findAll().size()).isEqualTo(0);
    }

}

