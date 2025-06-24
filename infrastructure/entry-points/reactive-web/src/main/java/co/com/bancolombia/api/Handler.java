package co.com.bancolombia.api;

import co.com.bancolombia.api.dto.StatsRequest;
import co.com.bancolombia.api.mapper.StatsMappper;
import co.com.bancolombia.model.stats.Stats;
import co.com.bancolombia.usecase.stats.StatsUseCase;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
public class Handler {

    // Handler stats
    private final StatsUseCase statsUseCase;
    private final StatsMappper statsMapper;

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest request) {
        return request.bodyToMono(StatsRequest.class)
                .map(statsMapper.mapToDomain()) // 👈 CORRECTO
                .flatMap(stats -> statsUseCase.save(stats, stats.getHash()))
                .flatMap(saved -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(saved))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
    }



    public Mono<ServerResponse> listenGETUseCase(ServerRequest serverRequest) {
        // useCase.logic();
        return ServerResponse.ok().bodyValue("");
    }

    public Mono<ServerResponse> listenGETOtherUseCase(ServerRequest serverRequest) {
        // useCase2.logic();
        return ServerResponse.ok().bodyValue("");
    }

}
