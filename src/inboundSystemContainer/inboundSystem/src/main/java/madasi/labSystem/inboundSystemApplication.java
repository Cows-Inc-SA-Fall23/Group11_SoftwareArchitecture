package madasi.inboundSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(scanBasePackages = "madasi.inboundSystem")
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class, SecurityAutoConfiguration.class})
public class inboundSystemApplication {
	public static void main(String[] args) {
		System.setProperty("server.servlet.path", "/");
		System.setProperty("server.servlet.context-path", "/inboundSystem");
		System.setProperty("spring.mvc.favicon.path", "classpath:/static/favicon.ico");
		SpringApplication.run(inboundSystemApplication.class, args);
	}

}
