package py.fpuna.com.agendapediatricaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import py.fpuna.com.agendapediatricaapp.adapter.HijosAdapter;
import py.fpuna.com.agendapediatricaapp.apis.LoginData;
import py.fpuna.com.agendapediatricaapp.dto.HijosDTO;
import py.fpuna.com.agendapediatricaapp.dto.VacunaDTO;

public class HijosActivity extends AppCompatActivity {

    RecyclerView  rv;
    Integer paramIDusuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rv = (RecyclerView)findViewById(R.id.rvHijos);



        //parametros enviados en el otro activity
        Bundle param = getIntent().getExtras();
        if(param != null){
            // con este ID se debe buscar los hijos
            paramIDusuario = param.getInt("idUsuario");

        }

        //lista de hijos
        // agregar elementos de la lista
        List<HijosDTO> lista = new ArrayList<>();
        lista.add(new HijosDTO(1, "Laura", "Gomez"));
        lista.add(new HijosDTO(1, "Juan", "Gomez"));


        // estos dos metodos muestran la lista en el activity, llamar despues de tener la lista de hijos
        generarLineaLayoutVertical();
        inicializarAdaptadorRV(crearAdaptador(LoginData.getUsuario().getHijosCollection()));
    }

    public void generarLineaLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
    }


    public HijosAdapter crearAdaptador(List<HijosDTO> hijos) {
        HijosAdapter adaptador = new HijosAdapter(getApplicationContext(), hijos);
        return adaptador;
    }

    public void inicializarAdaptadorRV(HijosAdapter adapatador) {
        rv.setAdapter(adapatador);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        List<VacunaDTO> list = new ArrayList<>();
        LoginData.setListVacunas(list);
        Intent intent = new Intent(HijosActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
