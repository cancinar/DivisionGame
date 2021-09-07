package com.cinar.divisiongame.playerone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.cinar.divisiongame.playerone",
		"com.cinar.divisiongame.common",
})
public class PlayeroneApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayeroneApplication.class, args);
	}

}
