package co.com.bancolombia.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class StatsRequest {

        private Integer totalContactoClientes;
        private Integer motivoReclamo;
        private Integer motivoGarantia;
        private Integer motivoDuda;
        private Integer motivoCompra;
        private Integer motivoFelicitaciones;
        private Integer motivoCambio;
        private String hash;
}
