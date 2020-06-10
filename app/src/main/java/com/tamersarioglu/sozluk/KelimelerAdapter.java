package com.tamersarioglu.sozluk;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class KelimelerAdapter extends RecyclerView.Adapter<KelimelerAdapter.CartTasarimTutucu> {
    private Context mContext;
    private List<Kelimeler> kelimelerListe;

    public KelimelerAdapter(Context mContext, List<Kelimeler> kelimelerListe) {
        this.mContext = mContext;
        this.kelimelerListe = kelimelerListe;
    }

    @NonNull
    @Override
    public CartTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kart_tasarim, parent, false);
        return new CartTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartTasarimTutucu holder, final int position) {

        final Kelimeler kelime = kelimelerListe.get(position);
        holder.textViewIngilizce.setText(kelime.getIngilizce());
        holder.textViewTurkce.setText(kelime.getTurkce());

        holder.kelimeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext.getApplicationContext(), DetayActivity.class);
                intent.putExtra("nesne", kelime);

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kelimelerListe.size();
    }

    public static class CartTasarimTutucu extends RecyclerView.ViewHolder {

        private TextView textViewIngilizce;
        private TextView textViewTurkce;
        private CardView kelimeCard;

        public CartTasarimTutucu(@NonNull View itemView) {
            super(itemView);
            textViewIngilizce = itemView.findViewById(R.id.textView_Detay_Ingilizce);
            textViewTurkce = itemView.findViewById(R.id.textViewTurkce);
            kelimeCard = itemView.findViewById(R.id.kelime_CardView);
        }
    }


}
