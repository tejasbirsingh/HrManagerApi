package com.nagarro.assignment5;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Assignment5Application {

    public static void main(String[] args) {
	SpringApplication application = new SpringApplication(Assignment5Application.class);
	// this will make the api run at port 9090
	application.setDefaultProperties(Collections.singletonMap("server.port", "9090"));
	application.run(args);
    }

}
