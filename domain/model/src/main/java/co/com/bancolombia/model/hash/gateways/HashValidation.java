package co.com.bancolombia.model.hash.gateways;

import reactor.core.publisher.Mono;

public interface HashValidation {

        Mono<Boolean> validate(String hash,  String keys);
}
