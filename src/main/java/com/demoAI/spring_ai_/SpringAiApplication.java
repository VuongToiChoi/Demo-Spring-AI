package com.demoAI.spring_ai_;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAiApplication {
	public static void main(String[] args) {
		// Set the timezone to Asia/Ho_Chi_Minh
		// In runtime process I have a problem with PostgresSQL
		// The time zone in my Window OS is set Asia/Saigon
		// PostgresSQL is included Asia/Ho_Chi_Minh so that i need to set the time zone at run time
		System.setProperty("user.timezone", "Asia/Ho_Chi_Minh");
		SpringApplication.run(SpringAiApplication.class, args);
	}

}
