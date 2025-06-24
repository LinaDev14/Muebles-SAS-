package co.com.bancolombia.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitConfig {

        public static final String STATS_QUEUE = "event.stats.validated";

        @Bean
        public Queue statsValidatedQueue() {
                return new Queue(STATS_QUEUE, true); // durable
        }
}
