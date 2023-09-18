package org.rivero.roommanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@SpringBootApplication
public class RoomManagementServiceApplication {
	public static void main(String[] args) {
		ApplicationContext app =  SpringApplication.run(RoomManagementServiceApplication.class, args);
		Room myRoom = app.getBean(Room.class);
		User userHao = new User("Hao", "HaoDz", 1);
		User userLong = new User("Long", "LongDz", 1);
		MoneyConsumeEvent event = userHao.createMoneyConsumeEvent(List.of(userHao.getId(), userLong.getId()));
		event.accounting(List.of(userHao, userLong));
		System.out.println(userHao);
		System.out.println(userLong);
	}
}

@Component
class Room{
	String id;
	Room(){
		this.id = "1";
	}
}

