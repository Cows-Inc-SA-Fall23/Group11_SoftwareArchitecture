package madasi.sensorManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication
@EnableScheduling
@SpringBootApplication(scanBasePackages = "madasi.sensorManager")
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class, SecurityAutoConfiguration.class})
public class sensorManagerApplication {
	public static void main(String[] args) {
		System.setProperty("server.servlet.path", "/");
		System.setProperty("server.servlet.context-path", "/sensorManager");
		System.setProperty("spring.mvc.favicon.path", "classpath:/static/favicon.ico");
		SpringApplication.run(sensorManagerApplication.class, args);
	}

}
