package no.niklas.oeving2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Oeving2Application {

	public static void main(String[] args) {
	    DummyDB.makeDummyData();
		SpringApplication.run(Oeving2Application.class, args);
	}

}
