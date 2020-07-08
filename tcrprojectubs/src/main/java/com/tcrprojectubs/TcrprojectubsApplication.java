package com.tcrprojectubs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcrprojectubs.domain.User;
import com.tcrprojectubs.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.lang.String;

@SpringBootApplication
public class TcrprojectubsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TcrprojectubsApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserService userService){
		return args -> {

			// read JSON and load json
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<User>> typeReference = new TypeReference<List<User>>(){};

			File file = new File("~/Documentos/tcrprojectubs/src/main/resources/json");
			File[] arquivos = file.listFiles();

			/*
			for (File arquivo : arquivos) {
				System.out.println(arquivo);
			}
*/
			//InputStream inputStream = TypeReference.class.getResourceAsStream("/json/data_1.json");
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/data1.json");
			for (File arquivo : arquivos) {
				try {
					List<User> users = mapper.readValue(inputStream, typeReference);
					userService.save(users);
					System.out.println("Users Saved!");
				} catch (IOException e) {
					System.out.println("Unable to save users: " + e.getMessage());
				}
			}
		};
	}
}