package in.nareshsharma.horo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableConfigurationProperties
@EnableJpaRepositories
@SpringBootApplication
public class DemoHoroscopeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoHoroscopeApplication.class, args);
	}
}
