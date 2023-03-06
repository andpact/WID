package andpact.project.wid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WidApplication {

    public static void main(String[] args) {
        SpringApplication.run(WidApplication.class, args);
    }

}
