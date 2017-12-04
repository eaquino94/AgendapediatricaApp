package py.fpuna.com.agendapediatricaapp.dto;

import java.sql.Date;

/**
 * Created by jaime on 18/11/17.
 */

public class VacunaDTO {

    private Character aplicada;
    private Date fechaAplicacion;
    private Integer idHijo;
    private Integer id;
    private String descripcionVacunas;
    private String enfermedadVacunas;


    public VacunaDTO() {
    }

    public VacunaDTO(Character aplicada, Date fechaAplicacion, String descripcionVacunas) {
        this.aplicada = aplicada;
        this.fechaAplicacion = fechaAplicacion;
        this.descripcionVacunas = descripcionVacunas;
    }

    public Character getAplicada() {
        return aplicada;
    }

    public void setAplicada(Character aplicada) {
        this.aplicada = aplicada;
    }

    public Date getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(Date fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public Integer getIdHijo() {
        return idHijo;
    }

    public void setIdHijo(Integer idHijo) {
        this.idHijo = idHijo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcionVacunas() {
        return descripcionVacunas;
    }

    public void setDescripcionVacunas(String descripcionVacunas) {
        this.descripcionVacunas = descripcionVacunas;
    }

    public String getEnfermedadVacunas() {
        return enfermedadVacunas;
    }

    public void setEnfermedadVacunas(String enfermedadVacunas) {
        this.enfermedadVacunas = enfermedadVacunas;
    }

    @Override
    public String toString() {
        return "VacunaDTO{" +
                "aplicada=" + aplicada +
                ", fechaAplicacion=" + fechaAplicacion +
                ", idHijo=" + idHijo +
                ", id=" + id +
                ", descripcionVacunas='" + descripcionVacunas + '\'' +
                ", enfermedadVacunas='" + enfermedadVacunas + '\'' +
                '}';
    }
}
