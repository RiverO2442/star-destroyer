package org.rivero.roommanagement;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.rivero.roommanagement.entities.MoneyConsumeEvent;
import org.rivero.roommanagement.entities.User;
import org.rivero.roommanagement.repositories.DBConnectionManager;
import org.rivero.roommanagement.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class RoomManagementServiceApplication {
    public static void main(String[] args) throws SQLException {
		ApplicationContext app =  SpringApplication.run(RoomManagementServiceApplication.class, args);
    }
}

