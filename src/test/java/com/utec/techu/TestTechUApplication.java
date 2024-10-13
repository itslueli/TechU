package com.utec.techu;

import org.springframework.boot.SpringApplication;

public class TestTechUApplication {

	public static void main(String[] args) {
		SpringApplication.from(TechUApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
