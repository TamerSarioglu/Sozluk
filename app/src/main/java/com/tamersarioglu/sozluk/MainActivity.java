package com.tamersarioglu.sozluk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<Kelimeler> kelimelerListe;
    private KelimelerAdapter adapter;
    private Veritabani vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VeriTabaniKopyala();
        init();

        vt = new Veritabani(this);

        toolbar.setTitle("Sözlük Uygulaması");
        setSupportActionBar(toolbar);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        kelimelerListe = new KelimelerDAO().tumKelimeler(vt);

        adapter = new KelimelerAdapter(this, kelimelerListe);
        recyclerView.setAdapter(adapter);


    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.arama_menu, menu);

        MenuItem item = menu.findItem(R.id.action_ara);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.e("gönderilen Arama", query);
        aramaYap(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.e("harf girdikçe Arama", newText);
        aramaYap(newText);
        return true;
    }

    public void VeriTabaniKopyala(){
        DatabaseCopyHelper copyHelper = new DatabaseCopyHelper(this);
        try {
            copyHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        copyHelper.openDataBase();
    }

    public void aramaYap(String aramaKelime){
        kelimelerListe = new KelimelerDAO().kelimeAra(vt,aramaKelime);
        adapter = new KelimelerAdapter(this,kelimelerListe);

        recyclerView.setAdapter(adapter);
    }
}