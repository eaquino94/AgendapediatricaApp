package py.fpuna.com.agendapediatricaapp.apis;

/**
 * Created by jaime on 17/11/17.
 */

public class ConstantesRest {

    public static final String urlBase = "http://10.0.10.11:8080/AgendaPediatrica/webresources/";


    //poner aqui las urls de los servicios
    public static String API_VALDILAR_USUARIO = urlBase + "agendapediatrica.usuarios/ValidarUsuario";
    public static String API_GET_MOSTRAR_HIJO= urlBase + "agendapediatrica.usuarios/Mostrarhijo";
    public static String API_GET_VACUNAS = urlBase + "agendapediatrica.hijos/VacunasHijo";


}
