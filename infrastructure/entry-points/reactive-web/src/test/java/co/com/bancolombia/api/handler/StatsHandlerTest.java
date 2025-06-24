package co.com.bancolombia.api.handler;

import co.com.bancolombia.api.Handler;
import co.com.bancolombia.model.stats.Stats;
import co.com.bancolombia.usecase.stats.StatsUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StatsHandlerTest {

        @InjectMocks
        private Handler handler;

        @Mock
        private StatsUseCase useCase;

        @Test
        void shouldReturnStatsSuccessfully() {
                Stats stats = Stats.builder()
                        .timestamp("2025-06-23T18:00:00")
                        .totalContactoClientes(250)
                        .build();

                ServerRequest request = mock(ServerRequest.class);

                //Body to mono
                when(request.bodyToMono(Stats.class)).thenReturn(Mono.just(stats));
                when(request.headers()).thenReturn(mock(ServerRequest.Headers.class));
                when(request.headers().firstHeader("hash")).thenReturn("hashEjemplo");

                when(useCase.save(any(Stats.class), anyString()))
                        .thenReturn(Mono.just(stats));

                Mono<ServerResponse> response = handler.listenPOSTUseCase(request);

                StepVerifier.create(response)
                        .expectNextMatches(serverResponse -> serverResponse.statusCode().is2xxSuccessful())
                        .verifyComplete();

                verify(useCase).save(stats, "hashEjemplo");
}
}