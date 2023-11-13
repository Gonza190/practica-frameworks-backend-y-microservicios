package es.uah.peliculasfront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class PeliculasFrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeliculasFrontApplication.class, args);
	}

}
