package com.tamersarioglu.sozluk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetayActivity extends AppCompatActivity {

    private Kelimeler kelime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        TextView textViewDetayIngilizce = findViewById(R.id.textView_Detay_Ingilizce);
        TextView textViewDetayTurkce = findViewById(R.id.textView_Detay_Turkce);

        kelime = (Kelimeler) getIntent().getSerializableExtra("nesne");

        String gelenIngilizce = kelime.getIngilizce();
        String gelenTurkce = kelime.getTurkce();

        textViewDetayIngilizce.setText(gelenIngilizce);
        textViewDetayTurkce.setText(gelenTurkce);


    }
}