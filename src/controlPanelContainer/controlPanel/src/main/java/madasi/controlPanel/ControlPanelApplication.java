package madasi.controlPanel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(scanBasePackages = "madasi.controlPanel")
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class, SecurityAutoConfiguration.class})
public class ControlPanelApplication {
	public static void main(String[] args) {
		System.setProperty("server.servlet.path", "/");
		System.setProperty("server.servlet.context-path", "/controlPanel");
		System.setProperty("spring.mvc.favicon.path", "classpath:/static/favicon.ico");
		SpringApplication.run(ControlPanelApplication.class, args);
	}

}
