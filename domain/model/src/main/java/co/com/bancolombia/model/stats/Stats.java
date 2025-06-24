package co.com.bancolombia.model.stats;
import lombok.*;

@Getter
@Builder(toBuilder = true)
public class Stats {

        private final String timestamp;
        private final Integer totalContactoClientes;
        private final Integer motivoReclamo;
        private final Integer motivoGarantia;
        private final Integer motivoDuda;
        private final Integer motivoCompra;
        private final Integer motivoFelicitaciones;
        private final Integer motivoCambio;
        private String hash;



}
