package dist2.rest;

import dist2.rest.bankclient.BankClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class RestApplication {
	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}
	@GetMapping("/")
	public List<BankClient> hello() {
		return List.of(
				new BankClient("Bart"),
				new BankClient("Benny", 500, 10.20),
				new BankClient("Rita")
		);
	}
}
