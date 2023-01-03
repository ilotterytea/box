package kz.ilotterytea.box;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(BoxProperties.class)
public class BoxApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoxApplication.class, args);
	}

}
