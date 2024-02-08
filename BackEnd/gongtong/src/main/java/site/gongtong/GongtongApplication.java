package site.gongtong;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import site.gongtong.map.service.MapService;

//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
//@EnableJpaAuditing
//public class GongtongApplication{
//
//	public static void main(String[] args) {
//		SpringApplication.run(GongtongApplication.class, args);
//	}
//
//
//}
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableJpaAuditing
public class GongtongApplication{

	private final MapService mapService;

	public GongtongApplication(MapService mapService) {
		this.mapService = mapService;
	}

	public static void main(String[] args) {
		SpringApplication.run(GongtongApplication.class, args);
	}

	@PostConstruct
	public void getCafeDataAndSave() {
		mapService.getCafeDataAndSave();
	}

}
