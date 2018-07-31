package com.app.fran.copanamo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.fran.copanamo.R;
import com.app.fran.copanamo.entidades.FaseFinal;
import com.squareup.picasso.Picasso;

import java.util.List;

public class QuartasRecyclerAdapter extends RecyclerView.Adapter<QuartasRecyclerAdapter.ViewHolder> {

    private final List<FaseFinal> quartasList;

    public QuartasRecyclerAdapter(final List<FaseFinal> quartasList) {
        this.quartasList = quartasList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
       private TextView data;
       private TextView horario;
       private ImageView img_sel1,img_sel2;
       private TextView nome_sel1,nome_sel2;
       private TextView placar1,placar2;

        public ViewHolder(final View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.data);
            horario = itemView.findViewById(R.id.horario);
            img_sel1 = itemView.findViewById(R.id.img_sel1_oitavas);
            img_sel2 = itemView.findViewById(R.id.img_sel2_oitavas);
            nome_sel1 = itemView.findViewById(R.id.nome_sel1);
            nome_sel2 = itemView.findViewById(R.id.nome_sel2);
            placar1 = itemView.findViewById(R.id.placar1);
            placar2 = itemView.findViewById(R.id.placar2);

        }
    }

    @Override
    public QuartasRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.linha_quartas, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(QuartasRecyclerAdapter.ViewHolder linhaViewHolder , int position) {

        FaseFinal linha = quartasList.get(position);

        Picasso.with(linhaViewHolder.img_sel1.getContext())
                .load(linha.getUrl_sel1())
                .into(linhaViewHolder.img_sel1);

        Picasso.with(linhaViewHolder.img_sel2.getContext())
                .load(linha.getUrl_sel2())
                .into(linhaViewHolder.img_sel2);

        linhaViewHolder.data.setText(linha.getData()+",");
        linhaViewHolder.horario.setText(linha.getHorario());
        linhaViewHolder.nome_sel1.setText(linha.getNome_sel1());
        linhaViewHolder.nome_sel2.setText(linha.getNome_sel2());
        linhaViewHolder.placar1.setText(linha.getPlacar1());
        linhaViewHolder.placar2.setText(linha.getPlacar2());


    }

    @Override
    public int getItemCount() {
        return quartasList.size();
    }
}

