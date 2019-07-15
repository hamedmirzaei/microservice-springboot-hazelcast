package microservice.springboot.hazelcast;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@Slf4j
public class HazelcastInstance1Application implements CommandLineRunner {

    @Autowired
    CacheManager cacheManager;

    public static void main(String[] args) {
        SpringApplication.run(HazelcastInstance1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Using cache manager: " + cacheManager.getClass().getName());
    }
}
