package py.fpuna.com.agendapediatricaapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import py.fpuna.com.agendapediatricaapp.apis.ConstantesRest;

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
        OkHttpHandler okHttpHandler= new OkHttpHandler();
        okHttpHandler.execute(ConstantesRest.API_VALDILAR_USUARIO+email);

    }

    // tareas asyncronas
    public class OkHttpHandler extends AsyncTask<String, String, String> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String...params) {

            Request.Builder builder = new Request.Builder();
            builder.url(params[0]);
            Request request = builder.build();

            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        // respuesta del api, aqui se debe obtener la respuesta del servicio
        //y si exite va al home activity
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            System.out.println(s);
            /**
             * Resultado del api
             * Se debe enviar como parametro al activity
             * el ID del usuario, para buscar sus hijos
             */
            Intent intent = new Intent(LoginActivity.this, HijosActivity.class);
          //  intent.putExtra("idUsuario", usuarioDTO.getId());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
