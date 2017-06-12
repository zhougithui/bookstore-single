package org.bear.bookstore.retry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableRetry
public class Application {

    @Bean
    public RetryService service() {
        return new RetryService();
    }

}
