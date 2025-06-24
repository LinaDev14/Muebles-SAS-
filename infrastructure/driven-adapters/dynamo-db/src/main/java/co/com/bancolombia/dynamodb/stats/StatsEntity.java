package co.com.bancolombia.dynamodb.stats;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;


@DynamoDbBean
public class StatsEntity {

    private String timestamp;
    private Integer totalContactoClientes;
    private Integer motivoReclamo;
    private Integer motivoGarantia;
    private Integer motivoDuda;
    private Integer motivoCompra;
    private Integer motivoFelicitaciones;
    private Integer motivoCambio;


    @DynamoDbPartitionKey
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getTotalContactoClientes() {
        return totalContactoClientes;
    }

    public void setTotalContactoClientes(Integer totalContactoClientes) {
        this.totalContactoClientes = totalContactoClientes;
    }

    public Integer getMotivoReclamo() {
        return motivoReclamo;
    }

    public void setMotivoReclamo(Integer motivoReclamo) {
        this.motivoReclamo = motivoReclamo;
    }

    public Integer getMotivoGarantia() {
        return motivoGarantia;
    }

    public void setMotivoGarantia(Integer motivoGarantia) {
        this.motivoGarantia = motivoGarantia;
    }

    public Integer getMotivoDuda() {
        return motivoDuda;
    }

    public void setMotivoDuda(Integer motivoDuda) {
        this.motivoDuda = motivoDuda;
    }

    public Integer getMotivoCompra() {
        return motivoCompra;
    }

    public void setMotivoCompra(Integer motivoCompra) {
        this.motivoCompra = motivoCompra;
    }

    public Integer getMotivoFelicitaciones() {
        return motivoFelicitaciones;
    }

    public void setMotivoFelicitaciones(Integer motivoFelicitaciones) {
        this.motivoFelicitaciones = motivoFelicitaciones;
    }

    public Integer getMotivoCambio() {
        return motivoCambio;
    }

    public void setMotivoCambio(Integer motivoCambio) {
        this.motivoCambio = motivoCambio;
    }
}
