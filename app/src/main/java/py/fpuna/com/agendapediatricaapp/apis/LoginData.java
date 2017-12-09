package py.fpuna.com.agendapediatricaapp.apis;

import java.util.ArrayList;
import java.util.List;

import py.fpuna.com.agendapediatricaapp.dto.UsuarioDTO;
import py.fpuna.com.agendapediatricaapp.dto.VacunaDTO;

/**
 * Created by jaime on 04/12/17.
 */

public class LoginData {

    public static UsuarioDTO usuario;
    public static List<VacunaDTO> listVacunas = new ArrayList<>();


    public static List<VacunaDTO> getListVacunas() {
        return listVacunas;
    }

    public static void setListVacunas(List<VacunaDTO> listVacunas) {
        LoginData.listVacunas = listVacunas;
    }

    public static UsuarioDTO getUsuario() {
        return usuario;
    }

    public static void setUsuario(UsuarioDTO usuario) {
        LoginData.usuario = usuario;
    }
}
