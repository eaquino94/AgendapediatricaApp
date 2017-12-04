package py.fpuna.com.agendapediatricaapp.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by marceloe on 09/10/17.
 */

public class UsuarioDTO implements Serializable {

    private Integer id;

    private String nombre;

    private String correo;

    private Boolean valido;

    private List<HijosDTO> hijosCollection;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Boolean getValido() {
        return valido;
    }

    public void setValido(Boolean valido) {
        this.valido = valido;
    }

    public List<HijosDTO> getHijosCollection() {
        return hijosCollection;
    }

    public void setHijosCollection(List<HijosDTO> hijosCollection) {
        this.hijosCollection = hijosCollection;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", valido=" + valido +
                ", hijosCollection=" + hijosCollection +
                '}';
    }
}