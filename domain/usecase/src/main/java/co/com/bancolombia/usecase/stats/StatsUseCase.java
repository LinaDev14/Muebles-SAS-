package co.com.bancolombia.usecase.stats;

import co.com.bancolombia.model.exception.CustomHashValidationException;
import co.com.bancolombia.model.hash.gateways.HashValidation;
import co.com.bancolombia.model.stats.Stats;
import co.com.bancolombia.model.stats.gateways.StatsRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class StatsUseCase {

        private  final HashValidation hashValidation;
        private final StatsRepository statsRepository;

        public Mono<Stats> save(Stats stats, String hash) {

                String keys = String.join(",",
                        stats.getTotalContactoClientes().toString(),
                        stats.getMotivoReclamo().toString(),
                        stats.getMotivoGarantia().toString(),
                        stats.getMotivoDuda().toString(),
                        stats.getMotivoCompra().toString(),
                        stats.getMotivoFelicitaciones().toString(),
                        stats.getMotivoCambio().toString()
                );

                System.out.println("🧪 keys = " + keys);
                System.out.println("🧪 expected hash = " + hash);

                return hashValidation.validate(hash, keys)
                        .doOnNext(valid -> System.out.println("✅ isValid = " + valid))
                        .flatMap(isValid -> {
                                if (isValid) {
                                        return statsRepository.save(stats).thenReturn(stats);
                                } else {
                                        return Mono.error(new CustomHashValidationException("Hash inválido"));
                                }
                        });
        }

}
