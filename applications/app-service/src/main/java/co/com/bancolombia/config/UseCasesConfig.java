package co.com.bancolombia.config;

import co.com.bancolombia.model.hash.gateways.HashValidation;
import co.com.bancolombia.model.stats.gateways.StatsRepository;
import co.com.bancolombia.usecase.stats.StatsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "co.com.bancolombia.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {

        @Bean
        public StatsUseCase statsUseCase(HashValidation hashValidation, StatsRepository statsRepository) {
                return new StatsUseCase(hashValidation, statsRepository);
        }
}
