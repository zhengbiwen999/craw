import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableRetry
@EnableDiscoveryClient
public class LogDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogDemoApplication.class,args);
    }

}
