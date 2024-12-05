package Kelompok2_RPL.AplikasiKlinik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class AplikasiKlinikApplication {

	public static void main(String[] args) {
		SpringApplication.run(AplikasiKlinikApplication.class, args);
	}

}
