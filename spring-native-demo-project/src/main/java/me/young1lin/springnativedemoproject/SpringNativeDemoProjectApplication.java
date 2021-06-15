package me.young1lin.springnativedemoproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Native demo project
 *
 * @author young1lin
 * @since 1.0
 */
@SpringBootApplication
@RestController
public class SpringNativeDemoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringNativeDemoProjectApplication.class, args);
	}

	@GetMapping("/helloworld")
	public String hello() {
		return "Hello World!";
	}

	@GetMapping("/")
	public String index() {
		return "Hello World!!!!";
	}

}
