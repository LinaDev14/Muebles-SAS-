package co.com.bancolombia.hashvalidation;

import co.com.bancolombia.model.hash.gateways.HashValidation;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class HashValidator  implements HashValidation {

        /**
         * Convierte un arreglo de bytes en una cadena hexadecimal en minúsculas.
         */
        private String bytesToHex(byte[] bytes) {
                StringBuilder result = new StringBuilder();
                for (byte b : bytes) {
                        result.append(String.format("%02x", b));
                }
                return result.toString();
        }

        /**
         * Valida si el hash MD5 generado a partir de la cadena `keys` coincide con el hash recibido.
         *
         * @param inputHash hash MD5 recibido (desde el request)
         * @param keys      cadena construida a partir de los valores del modelo, separados por coma
         * @return Mono<Boolean> con el resultado de la validación
         */
        @Override
        public Mono<Boolean> validate(String inputHash, String keys) {
                try {
                        MessageDigest md = MessageDigest.getInstance("MD5");
                        byte[] digest = md.digest(keys.getBytes(StandardCharsets.UTF_8));
                        String generatedHash = bytesToHex(digest);

                        return Mono.just(generatedHash.equalsIgnoreCase(inputHash));
                } catch (NoSuchAlgorithmException e) {
                        return Mono.error(new RuntimeException("MD5 algorithm is not available", e));
                }
        }
}
