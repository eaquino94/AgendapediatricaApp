package py.fpuna.com.agendapediatricaapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.Date;

import py.fpuna.com.agendapediatricaapp.apis.ConstantesRest;
import py.fpuna.com.agendapediatricaapp.apis.LoginData;
import py.fpuna.com.agendapediatricaapp.apis.Manager;
import py.fpuna.com.agendapediatricaapp.apis.Respuesta;
import py.fpuna.com.agendapediatricaapp.dto.HijosDTO;
import py.fpuna.com.agendapediatricaapp.dto.UsuarioDTO;
import py.fpuna.com.agendapediatricaapp.dto.VacunaDTO;

public class LoginActivity extends AppCompatActivity {

    SignInButton logingoogle;
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //boton del login
        logingoogle = (SignInButton) findViewById(R.id.logingoogle);


        //click en el boton
        logingoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGoogle();
            }
        });

    }

    //consulta las cuentas del correo
    private void signInWithGoogle() {
        if(mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        final Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println("REQUEST CODE: " + requestCode);


        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            System.out.println("SUCCESS: " + result.isSuccess());
            //la respuesta es FALSE por eso no entra aqui
           /* if (result.isSuccess()) {
                final GoogleApiClient client = mGoogleApiClient;
                GoogleSignInAccount profile = result.getSignInAccount();

                // get profile information
                String name = "";
                String email = "";
                String uriPicture = "";
                if (profile.getDisplayName() != null) {
                    name = profile.getDisplayName();
                }
                if (profile.getEmail() != null) {
                    email = profile.getEmail();
                }
                if (profile.getPhotoUrl() != null) {
                    uriPicture = profile.getPhotoUrl().toString();
                }
                // save profile information to preferences
                SharedPreferences prefs = getSharedPreferences("com.misuperapp.app", Context.MODE_PRIVATE);
                prefs.edit().putString("com.misuperapp.app.nombre", name).apply();
                prefs.edit().putString("com.misuperapp.app.email", email).apply();
                prefs.edit().putString("com.misuperapp.app.uriPicture", uriPicture).apply();
                // redirect to map screen
                startActivity(new Intent(LoginActivity.this, HijosActivity.class));
            } else {
                // Otros result de actividades de inicio de session como facebook o twitter
            }*/

           //valida el usuario, en duro, deberia obtener del resultado del GoogleSignInResult
           validarusuario("cparedes.cabanas@gmail.com");

        }

    }


    public void validarusuario(String email){

        //permisos
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //ejecuta la url
       // OkHttpHandler okHttpHandler= new OkHttpHandler();
       // okHttpHandler.execute(email);

        MyTask myTask = new MyTask(getApplicationContext(), email, "validarusuario");
        myTask.execute();

    }


    private class MyTask extends AsyncTask<Respuesta, Respuesta, Respuesta>
    {
        Context context;
        String email;
        String metodo;

        public MyTask(Context context, String email, String metodo) {
            this.context = context;
            this.email = email;
            this.metodo = metodo;

        }


        @Override
        protected void onPreExecute()
        {
            //updateDisplay("Starting Task");
            super.onPreExecute();
        }


        @Override
        protected Respuesta doInBackground(Respuesta... params)
        {

            Respuesta respuesta = new Respuesta();
            try {

                Manager manager = new Manager();

                if(metodo.equals("validarusuario")){

                    respuesta = manager.validarUsuario(email);
                }else{
                    respuesta = manager.getVacunas(email);
                }



            } catch (Exception e) {
                e.printStackTrace();

            }

            return respuesta;

        }


        @Override
        protected void onPostExecute(Respuesta respuesta)
        {

            if(respuesta.getEstado().equals("OK")){

                if(metodo.equals("validarusuario")){
                    UsuarioDTO usuarioDTO = (UsuarioDTO) respuesta.getDatos();
                    if(usuarioDTO.getValido()){
                        for (HijosDTO hijos: usuarioDTO.getHijosCollection()) {
                            MyTask myTask = new MyTask(getApplicationContext(), String.valueOf(hijos.getId()), "getVacunas");
                            myTask.execute();

                        }

                        LoginData.setUsuario(usuarioDTO);

                        Intent intent = new Intent(LoginActivity.this, HijosActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();

                    }else{

                    }

                }else if(metodo.equals("getVacunas")){
                    HijosDTO hijos = (HijosDTO) respuesta.getDatos();
                    for (VacunaDTO vacuna: hijos.getVacunasCollection() ) {
                        vacuna.setIdHijo(Integer.parseInt(email));
                        LoginData.getListVacunas().add(vacuna);
                    }

                   /* Intent intent = new Intent(LoginActivity.this, HijosActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();*/

                }else if(metodo.equals("mostrarHijos")){


                   /* Intent intent = new Intent(LoginActivity.this, HijosActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();*/

                }

            }
        }

    }

}
