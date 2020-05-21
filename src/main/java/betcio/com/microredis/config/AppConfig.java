package betcio.com.microredis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class AppConfig {

    final ConfigurableApplicationContext context;

    @Autowired
    public AppConfig(ConfigurableApplicationContext context) {
        this.context = context;
    }

    @Scheduled(initialDelay = 600000, fixedRate = 5000)
    public void shutDown () {
        System.out.println("Closing service...");
        System.exit(SpringApplication.exit(context));
    }

}
