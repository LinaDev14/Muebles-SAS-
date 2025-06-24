package co.com.bancolombia.api.mapper;

import co.com.bancolombia.api.dto.StatsRequest;
import co.com.bancolombia.model.stats.Stats;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.function.Function;

@Component
public class StatsMappper {

        public Function<StatsRequest, Stats> mapToDomain() {
                return dto -> Stats.builder()
                        .timestamp(Instant.now().toString())
                        .totalContactoClientes(dto.getTotalContactoClientes())
                        .motivoReclamo(dto.getMotivoReclamo())
                        .motivoGarantia(dto.getMotivoGarantia())
                        .motivoDuda(dto.getMotivoDuda())
                        .motivoCompra(dto.getMotivoCompra())
                        .motivoFelicitaciones(dto.getMotivoFelicitaciones())
                        .motivoCambio(dto.getMotivoCambio())
                        .hash(dto.getHash())
                        .build();
        }

        public Function<Stats, StatsRequest> mapToDto() {
                return stats -> StatsRequest.builder()
                        .totalContactoClientes(stats.getTotalContactoClientes())
                        .motivoReclamo(stats.getMotivoReclamo())
                        .motivoGarantia(stats.getMotivoGarantia())
                        .motivoDuda(stats.getMotivoDuda())
                        .motivoCompra(stats.getMotivoCompra())
                        .motivoFelicitaciones(stats.getMotivoFelicitaciones())
                        .motivoCambio(stats.getMotivoCambio())
                        .hash(stats.getHash())
                        .build();
        }
}
