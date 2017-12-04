package py.fpuna.com.agendapediatricaapp.apis;

import android.os.StrictMode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import py.fpuna.com.agendapediatricaapp.dto.HijosDTO;
import py.fpuna.com.agendapediatricaapp.dto.JsonHelper;
import py.fpuna.com.agendapediatricaapp.dto.UsuarioDTO;

/**
 * Created by jaime on 18/11/17.
 */

public class Manager {

    String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public Respuesta validarUsuario(String correo) throws Exception {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Respuesta respuesta = new Respuesta();
        respuesta.setEstado("OK");

        System.out.println("URL " + ConstantesRest.API_VALDILAR_USUARIO );

        URL url = new URL(ConstantesRest.API_VALDILAR_USUARIO );

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


        urlConnection.setRequestMethod( "POST" );


        urlConnection.setRequestProperty("Content-Type", "application/json");

        String urlParameters = "{\"correo\": \""+correo+"\"}";

        urlConnection.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        urlConnection.setConnectTimeout(5000); //5 segundos

        int code = urlConnection.getResponseCode();

        BufferedInputStream in;

        System.out.println("La respuesta  es: " + code);

        if (code >= 400) {

            in = new BufferedInputStream(urlConnection.getErrorStream());
            Scanner s = new Scanner(in).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";
            respuesta.setEstado("ERROR");

        } else {

            in = new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";

            System.out.println("El estado de la respuesta: " + result.toString());


            Gson gson = new Gson();


            Type listType = new TypeToken<UsuarioDTO>() {}.getType();
            UsuarioDTO user = new Gson().fromJson(result.toString(), listType);
            respuesta.setDatos(user);


        }

        return respuesta;

    }

    public Respuesta mostrarHijos(String idusuario) throws Exception {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Respuesta respuesta = new Respuesta();
        respuesta.setEstado("OK");

        System.out.println("URL " + ConstantesRest.API_GET_MOSTRAR_HIJO );

        URL url = new URL(ConstantesRest.API_GET_MOSTRAR_HIJO );

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


        urlConnection.setRequestMethod( "POST" );


        urlConnection.setRequestProperty("Content-Type", "application/json");

        String urlParameters = "{\"usuario\": \""+idusuario+"\"}";

        urlConnection.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        urlConnection.setConnectTimeout(5000); //5 segundos

        int code = urlConnection.getResponseCode();

        BufferedInputStream in;

        System.out.println("La respuesta  es: " + code);

        if (code >= 400) {

            in = new BufferedInputStream(urlConnection.getErrorStream());
            Scanner s = new Scanner(in).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";
            respuesta.setEstado("ERROR");

        } else {

            in = new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";

            System.out.println("El estado de la respuesta: " + result.toString());
         //   respuesta = JsonHelper.fromJson(result, Respuesta.class);

            Gson gson = new GsonBuilder().setDateFormat(DATE_FORMAT_PATTERN).create();
           /* HashMap<String, Object> JSONROOT = gson.fromJson(result.toString(), HashMap.class);

            ArrayList x = (ArrayList) JSONROOT.get("hijosCollection");
            System.out.println("ARRAY: " + x.toString());

            Type listType = new TypeToken<List<HijosDTO>>() {}.getType();

            List<HijosDTO> list =gson.fromJson(x.toString(), listType);*/


          //  respuesta.setDatos(list);

            Type listType = new TypeToken<UsuarioDTO>() {}.getType();
            UsuarioDTO user = new Gson().fromJson(result.toString(), listType);
            respuesta.setDatos(user);


        }

        return respuesta;

    }

    public Respuesta getVacunas(String idhijo) throws Exception {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Respuesta respuesta = new Respuesta();
        respuesta.setEstado("OK");

        System.out.println("URL " + ConstantesRest.API_GET_VACUNAS + " " + idhijo);

        URL url = new URL(ConstantesRest.API_GET_VACUNAS );

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


        urlConnection.setRequestMethod( "POST" );


        urlConnection.setRequestProperty("Content-Type", "application/json");

        String urlParameters = "{\"hijo\": \""+idhijo+"\"}";

        urlConnection.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        urlConnection.setConnectTimeout(5000); //5 segundos

        int code = urlConnection.getResponseCode();

        BufferedInputStream in;

        System.out.println("La respuesta  es: " + code);

        if (code >= 400) {

            in = new BufferedInputStream(urlConnection.getErrorStream());
            Scanner s = new Scanner(in).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";
            respuesta.setEstado("ERROR");

        } else {

            in = new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";

            System.out.println("El estado de la respuesta: " + result.toString());
            //   respuesta = JsonHelper.fromJson(result, Respuesta.class);

            Gson gson = new GsonBuilder().setDateFormat(DATE_FORMAT_PATTERN).create();
           /* HashMap<String, Object> JSONROOT = gson.fromJson(result.toString(), HashMap.class);

            ArrayList x = (ArrayList) JSONROOT.get("hijosCollection");
            System.out.println("ARRAY: " + x.toString());

            Type listType = new TypeToken<List<HijosDTO>>() {}.getType();

            List<HijosDTO> list =gson.fromJson(x.toString(), listType);*/


            //  respuesta.setDatos(list);

            Type listType = new TypeToken<HijosDTO>() {}.getType();
            HijosDTO user = gson.fromJson(result.toString(), listType);
            respuesta.setDatos(user);


        }

        return respuesta;

    }
}
