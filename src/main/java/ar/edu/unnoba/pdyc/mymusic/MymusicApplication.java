package ar.edu.unnoba.pdyc.mymusic;

import java.util.concurrent.Executor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableAsync
public class MymusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(MymusicApplication.class, args);

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // Bean para indicar la capacidad de las peticiones asincronicas
    @Bean("taskExecutor")
    public Executor getAsyncExecutor() {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(200);
        executor.setMaxPoolSize(2000);
        executor.setQueueCapacity(200);
        executor.setThreadNamePrefix("executor-");
        executor.initialize();
        return executor;
    }
}
