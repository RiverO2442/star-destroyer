package org.rivero.roommanager.repositories;

import org.rivero.roommanager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
