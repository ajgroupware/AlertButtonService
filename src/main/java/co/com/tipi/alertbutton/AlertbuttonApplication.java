package co.com.tipi.alertbutton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AlertbuttonApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlertbuttonApplication.class, args);
	}
}
