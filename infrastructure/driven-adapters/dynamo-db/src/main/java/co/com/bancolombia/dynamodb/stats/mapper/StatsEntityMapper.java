package co.com.bancolombia.dynamodb.stats.mapper;

import co.com.bancolombia.dynamodb.stats.StatsEntity;
import co.com.bancolombia.model.stats.Stats;

public class StatsEntityMapper {

        public static StatsEntity toEntity(Stats stats) {
                StatsEntity entity = new StatsEntity();
                entity.setTimestamp(stats.getTimestamp());
                entity.setTotalContactoClientes(stats.getTotalContactoClientes());
                entity.setMotivoReclamo(stats.getMotivoReclamo());
                entity.setMotivoGarantia(stats.getMotivoGarantia());
                entity.setMotivoDuda(stats.getMotivoDuda());
                entity.setMotivoCompra(stats.getMotivoCompra());
                entity.setMotivoFelicitaciones(stats.getMotivoFelicitaciones());
                entity.setMotivoCambio(stats.getMotivoCambio());
                return entity;
        }

        public static Stats toDomain(StatsEntity entity) {
                return Stats.builder()
                        .timestamp(entity.getTimestamp())
                        .totalContactoClientes(entity.getTotalContactoClientes())
                        .motivoReclamo(entity.getMotivoReclamo())
                        .motivoGarantia(entity.getMotivoGarantia())
                        .motivoDuda(entity.getMotivoDuda())
                        .motivoCompra(entity.getMotivoCompra())
                        .motivoFelicitaciones(entity.getMotivoFelicitaciones())
                        .motivoCambio(entity.getMotivoCambio())
                        .build();
        }
}
