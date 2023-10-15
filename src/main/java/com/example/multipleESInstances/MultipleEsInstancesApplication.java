package com.example.multipleESInstances;

import com.example.multipleESInstances.es7.PersonES7Repository;
import com.example.multipleESInstances.es8.PersonES8Repository;
import com.example.multipleESInstances.model.Person;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class MultipleEsInstancesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultipleEsInstancesApplication.class, args);
	}


	@Bean
	CommandLineRunner es7stuff(PersonES7Repository repo7, PersonES8Repository repo8)  {
		return args -> {
			Random r = new Random();
			System.out.println("On ES7");
			Person p7 = new Person(null, "PJD7");
			repo7.save(p7);

			System.out.println("Searching PJD7: "+ repo7.findPersonByFullname("PJD7"));
			System.out.println("Searching PJD8: "+ repo7.findPersonByFullname("PJD8"));

			System.out.println("On ES8");
			Person p8 = new Person(null, "PJD8");
			repo8.save(p8);
			System.out.println("Searching PJD7: "+ repo8.findPersonByFullname("PJD7"));
			System.out.println("Searching PJD8: "+ repo8.findPersonByFullname("PJD8"));

		};
	}
}
