package ar.com.jf.antilavado.repository.contant;

/**
 * Created by fvaldes on 13/01/2016.
 */
public enum StatusTypes {

    GENERAL_FILE(1L),
    GENERAL_FILE_EN_MOVIMIENTO(2L),
    GENERAL_FILE_ARCHIVO(3L),
    ALERT(4L),
    ALERT_SIN_TRATAMIENTO(5L),
    ALERT_ASIGNADA(6L),
    ALERT_CERRADA(7L),
    ALERT_ES_SOSPECHOSA(8L);

    StatusTypes(Long code) {
        this.code = code;
    }

    private Long code;

    public Long getCode() {
        return code;
    }
}


