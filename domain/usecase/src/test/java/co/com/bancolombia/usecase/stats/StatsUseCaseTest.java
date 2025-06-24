package co.com.bancolombia.usecase.stats;

import co.com.bancolombia.model.stats.Stats;
import co.com.bancolombia.model.stats.gateways.StatsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StatsUseCaseTest {

        @Mock
        private StatsRepository repository;

        @InjectMocks
        private StatsUseCase useCase;

        @Test
        void shouldSaveStats() {
                Stats stats = Stats.builder()
                        .timestamp("2025-06-23T18:00:00")
                        .totalContactoClientes(250)
                        .motivoReclamo(25)
                        .motivoGarantia(10)
                        .motivoDuda(100)
                        .motivoCompra(100)
                        .motivoFelicitaciones(7)
                        .motivoCambio(8)
                        .hash("5484062a4be1ce5645eb414663e14f59")
                        .build();

                when(repository.save(stats)).thenReturn(Mono.just(stats));

                StepVerifier.create(useCase.save(stats, "valorDePrueba"))
                        .expectNext(stats)
                        .verifyComplete();

                verify(repository).save(stats);
        }

        @Test
        void shouldHandleRepositoryError() {
                Stats stats = Stats.builder()
                        .timestamp("2025-06-23T18:00:00")
                        .totalContactoClientes(250)
                        .build();

                when(repository.save(any(Stats.class))
                        .thenReturn(Mono.error(new RuntimeException("DB down"))));

                StepVerifier.create(useCase.save(stats, "valorDePrueba"))
                        .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                                throwable.getMessage().equals("DB down"))
                        .verify();
        }

}
