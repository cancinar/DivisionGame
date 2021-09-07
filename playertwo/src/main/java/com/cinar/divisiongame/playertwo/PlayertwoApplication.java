package com.cinar.divisiongame.playertwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.cinar.divisiongame.playertwo",
		"com.cinar.divisiongame.common",
})
public class PlayertwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayertwoApplication.class, args);
	}

}
