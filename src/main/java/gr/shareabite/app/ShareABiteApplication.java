package gr.shareabite.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//to enable automatic withhold of auditing(timestamps of insert or update)
@EnableJpaAuditing
public class ShareABiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShareABiteApplication.class, args);
	}

}
