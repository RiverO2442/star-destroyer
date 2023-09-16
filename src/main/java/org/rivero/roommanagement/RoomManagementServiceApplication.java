package org.rivero.roommanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class RoomManagementServiceApplication {
	public static void main(String[] args) {
		ApplicationContext app =  SpringApplication.run(RoomManagementServiceApplication.class, args);
		Room myRoom = app.getBean(Room.class);
		System.out.println(myRoom.id);
	}
}

@Component
class Room{
	String id;
	Room(){
		this.id = "1";
	}
}