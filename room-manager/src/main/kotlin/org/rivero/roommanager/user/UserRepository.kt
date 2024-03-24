package org.rivero.roommanager.user

import org.rivero.roommanager.entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User?, String?>
