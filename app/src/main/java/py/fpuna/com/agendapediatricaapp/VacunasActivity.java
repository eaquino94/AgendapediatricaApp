package py.fpuna.com.agendapediatricaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import py.fpuna.com.agendapediatricaapp.adapter.VacunasAdapter;
import py.fpuna.com.agendapediatricaapp.dto.VacunaDTO;

public class VacunasActivity extends AppCompatActivity {

    RecyclerView rv;
    TextView nombreHijo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacunas);


        rv = (RecyclerView)findViewById(R.id.rvVacunas);
        nombreHijo = (TextView)findViewById(R.id.vacunasHijo);

        String paramHijo;
        //parametros enviados en el otro activity
        Bundle param = getIntent().getExtras();
        if(param != null){
            paramHijo = param.getString("hijo");
            nombreHijo.setText(paramHijo);
        }

        //lista de vacunas
        // agregar elementos de la lista
        List<VacunaDTO> lista = new ArrayList<>();
        lista.add(new  VacunaDTO('S', new Date(System.currentTimeMillis()), "BCG"));
        lista.add(new  VacunaDTO('S', new Date(System.currentTimeMillis()), "BCG"));

        /**
         * estos dos metodos muestran la lista en el activity, llamar despues de tener la lista de vacunas
         */
        generarLineaLayoutVertical();
        inicializarAdaptadorRV(crearAdaptador(lista));
    }

    public void generarLineaLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
    }


    public VacunasAdapter crearAdaptador(List<VacunaDTO> vacunas) {
        VacunasAdapter adaptador = new VacunasAdapter(getApplicationContext(), vacunas);
        return adaptador;
    }

    public void inicializarAdaptadorRV(VacunasAdapter adapatador) {
        rv.setAdapter(adapatador);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(VacunasActivity.this, HijosActivity.class);
        startActivity(intent);
        finish();
    }
}
