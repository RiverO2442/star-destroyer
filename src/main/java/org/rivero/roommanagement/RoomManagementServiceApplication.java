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
		MoneyConsumeEvent BHX_1 = new MoneyConsumeEvent("BHX 1", 100000, "Hao", List.of("Hao", "Long"), "Mua Do An" );
		MoneyConsumeEventRepository moneyConsumeEventRepository = app.getBean(MoneyConsumeEventRepository.class);
		UserRepository userRepository = app.getBean(UserRepository.class);
//		moneyConsumeEventRepository.save(BHX_1);
		List<MoneyConsumeEvent> listEvent =  moneyConsumeEventRepository.findAll();
		List<User> listUser =  userRepository.findAll();
		for(MoneyConsumeEvent event : listEvent){
			event.accounting(listUser);
			moneyConsumeEventRepository.save(event);
		}
		for(User user : listUser){
			userRepository.save(user);
		}
		userRepository.findAll().forEach(System.out::println);
//		moneyConsumeEventRepository.findAll().forEach(System.out::println);
//		User userHao = new User("Hao", "HaoDz", 1);
//		UserRepository userRepository = app.getBean(UserRepository.class);
//		User userLong = userRepository.findByName("Long");
//		userRepository.deleteById(userLong.getId());
//		userRepository.findAll().forEach(System.out::println);
//		MoneyConsumeEvent event = userHao.createMoneyConsumeEvent(List.of(userHao.getId(), userLong.getId()));
//		event.accounting(List.of(userHao, userLong));
//		System.out.println(userHaoDz);
//		System.out.println(userLong);

	}
}

