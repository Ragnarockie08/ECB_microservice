import org.bochenek.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;


@SpringBootApplication(scanBasePackages = "org.bochenek")
@EnableFeignClients(basePackages = "org.bochenek")
@EnableCaching
@EnableScheduling
public class Application {

        public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
        }

}
