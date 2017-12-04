package py.fpuna.com.agendapediatricaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import py.fpuna.com.agendapediatricaapp.adapter.VacunasAdapter;
import py.fpuna.com.agendapediatricaapp.apis.LoginData;
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

        final RadioButton radio1 = (RadioButton) findViewById(R.id.radio1);
        final RadioButton radio2 = (RadioButton) findViewById(R.id.radio2);
        final RadioButton radio3 = (RadioButton) findViewById(R.id.radio3);
        String options[] = {"Vacuna","Fecha","Aplicada"};



        String paramHijo;
        String idhijo = "0";
        //parametros enviados en el otro activity
        Bundle param = getIntent().getExtras();
        if(param != null){
            paramHijo = param.getString("hijo");
            idhijo = param.getString("id");
            nombreHijo.setText(paramHijo);
        }

        radio1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    radio2.setChecked(false);
                    radio3.setChecked(false);
                    List<VacunaDTO> lista = LoginData.getListVacunas();
                    Collections.sort(lista, new Comparator<VacunaDTO>(){

                        @Override
                        public int compare(VacunaDTO o1, VacunaDTO o2) {
                            return o1.getDescripcionVacunas().compareTo(o2.getDescripcionVacunas());
                        }
                    });
                    generarLineaLayoutVertical();
                    inicializarAdaptadorRV(crearAdaptador(lista));
                }
            }
        });

        radio2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    radio1.setChecked(false);
                    radio3.setChecked(false);
                    List<VacunaDTO> lista = LoginData.getListVacunas();
                    Collections.sort(lista, new Comparator<VacunaDTO>(){
                        @Override
                        public int compare(VacunaDTO o1, VacunaDTO o2) {
                            return o1.getFechaAplicacion().compareTo(o2.getFechaAplicacion());
                        }
                    });
                    generarLineaLayoutVertical();
                    inicializarAdaptadorRV(crearAdaptador(lista));
                }
            }
        });

        radio3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    radio1.setChecked(false);
                    radio2.setChecked(false);
                    List<VacunaDTO> lista = LoginData.getListVacunas();
                    Collections.sort(lista, new Comparator<VacunaDTO>(){
                        @Override
                        public int compare(VacunaDTO o1, VacunaDTO o2) {
                            return o1.getAplicada().compareTo(o2.getAplicada());
                        }

                    });

                    generarLineaLayoutVertical();
                    inicializarAdaptadorRV(crearAdaptador(lista));
                }
            }
        });

        //lista de vacunas
        // agregar elementos de la lista
        List<VacunaDTO> lista = new ArrayList<>();
        for (VacunaDTO vacuna: LoginData.getListVacunas()) {
            System.out.println(vacuna.toString());
            if(vacuna.getIdHijo()
                    == Integer.parseInt(idhijo)){
                lista.add(vacuna);
            }
        }


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
