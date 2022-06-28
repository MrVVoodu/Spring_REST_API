package toh_rest_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "toh_rest_api" })
public class ToHRestApiApplication {

	public static void main(String... args) {
		SpringApplication.run(ToHRestApiApplication.class, args);
	}

}
