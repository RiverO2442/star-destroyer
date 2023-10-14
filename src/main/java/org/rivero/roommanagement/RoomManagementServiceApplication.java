package org.rivero.roommanagement;

import org.rivero.roommanagement.entities.MoneyConsumeEvent;
import org.rivero.roommanagement.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@SpringBootApplication
public class RoomManagementServiceApplication {
	public static void main(String[] args) {
		ApplicationContext app =  SpringApplication.run(RoomManagementServiceApplication.class, args);
	}
}

