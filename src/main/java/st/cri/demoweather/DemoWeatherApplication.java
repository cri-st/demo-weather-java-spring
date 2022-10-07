package st.cri.demoweather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoWeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoWeatherApplication.class, args);
	}

}
