package py.fpuna.com.agendapediatricaapp.apis;

import android.os.StrictMode;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 * Created by jaime on 18/11/17.
 */

public class Manager {

    public Boolean validarUsuario(String correo) throws Exception {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        boolean respuesta = false;

        System.out.println("URL " + ConstantesRest.API_VALDILAR_USUARIO + correo);

        URL url = new URL(ConstantesRest.API_VALDILAR_USUARIO + correo);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod("GET");
        urlConnection.setConnectTimeout(10000); //5 segundos

        int code = urlConnection.getResponseCode();

        BufferedInputStream in;

        System.out.println("La respuesta  es: " + code);

        if (code >= 400) {

            in = new BufferedInputStream(urlConnection.getErrorStream());
            Scanner s = new Scanner(in).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";
           respuesta = false;

        } else {

            in = new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";

            System.out.println("El estado de la respuesta: " + result.toString());

            /*respuesta = JsonHelper.fromJson(result, Respuesta.class);



            System.out.println("El object es: " + respuesta.getDatos().toString());

            Gson gson = new Gson();

            String jsonInString = gson.toJson(respuesta.getDatos());

            System.out.println("El dato desserializado es: " + jsonInString);

            Type listType = new TypeToken<List<Cancha>>() {}.getType();
            List<Cancha> listCanchas = new Gson().fromJson(jsonInString, listType);
            respuesta.setDatos(listCanchas);*/




        }

        return respuesta;

    }
}
