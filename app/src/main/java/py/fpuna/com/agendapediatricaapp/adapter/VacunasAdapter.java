package py.fpuna.com.agendapediatricaapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import py.fpuna.com.agendapediatricaapp.R;
import py.fpuna.com.agendapediatricaapp.dto.HijosDTO;
import py.fpuna.com.agendapediatricaapp.dto.VacunaDTO;


/**
 * Created by jaime on 10/04/17.
 */

public class VacunasAdapter extends RecyclerView.Adapter<VacunasAdapter.ResultadoViewHolder>{
    private Context context;
    private List<VacunaDTO> vacunas;

    public VacunasAdapter(Context context, List<VacunaDTO> vacunas) {
        this.context = context;
        this.vacunas = vacunas;

    }


    @Override
    public VacunasAdapter.ResultadoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_vacunas, parent, false);
        return new VacunasAdapter.ResultadoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(VacunasAdapter.ResultadoViewHolder holder, int position) {
       VacunaDTO item = vacunas.get(position);

        holder.nombre.setText(item.getDescripcionVacunas());
        holder.fecha.setText(String.valueOf(item.getFechaAplicacion()));
        holder.aplicada.setText(String.valueOf(item.getAplicada()));


    }

    @Override
    public int getItemCount() {
        return vacunas.size();
    }

    public class ResultadoViewHolder extends RecyclerView.ViewHolder{

        private TextView aplicada;
        private TextView nombre;
        private TextView fecha;



        public ResultadoViewHolder(final View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.nombreVacuna);
            aplicada = (TextView) itemView.findViewById(R.id.vacunaAplicada);
            fecha = (TextView) itemView.findViewById(R.id.vacunaFecha);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });





        }
    }








}
