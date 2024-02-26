package com.icarobteles.webservices.modules.users.domain.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icarobteles.webservices.modules.users.domain.entities.User;

public interface UsersRepository extends JpaRepository<User, UUID> {
  Optional<User> findByEmail(String email);
}
