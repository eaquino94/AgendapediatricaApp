package py.fpuna.com.agendapediatricaapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import py.fpuna.com.agendapediatricaapp.R;
import py.fpuna.com.agendapediatricaapp.VacunasActivity;
import py.fpuna.com.agendapediatricaapp.dto.HijosDTO;


/**
 * Created by jaime on 10/04/17.
 */

public class HijosAdapter extends RecyclerView.Adapter<HijosAdapter.ResultadoViewHolder>{
    private Context context;
    private List<HijosDTO> hijos;

    public HijosAdapter(Context context, List<HijosDTO> hijos) {
        this.context = context;
        this.hijos = hijos;

    }


    @Override
    public HijosAdapter.ResultadoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_hijos, parent, false);
        return new HijosAdapter.ResultadoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HijosAdapter.ResultadoViewHolder holder, int position) {
       HijosDTO item = hijos.get(position);

        holder.nombre.setText(item.getNombres()+ " " + item.getApellidos());
        holder.id.setText(String.valueOf(item.getId()));





    }

    @Override
    public int getItemCount() {
        return hijos.size();
    }

    public class ResultadoViewHolder extends RecyclerView.ViewHolder{

        private TextView id;
        private TextView nombre;



        public ResultadoViewHolder(final View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.nombreHijo);
            id = (TextView) itemView.findViewById(R.id.idHijo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, VacunasActivity.class);
                    intent.putExtra("hijo", nombre.getText());
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            });





        }
    }






}
