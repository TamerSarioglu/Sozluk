package com.tamersarioglu.sozluk;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class KelimelerDAO {

    public ArrayList<Kelimeler> tumKelimeler(Veritabani vt) {

        ArrayList<Kelimeler> kelimelerArrayList = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM kelimeler", null);

        while (c.moveToNext()) {
            Kelimeler k = new Kelimeler(c.getInt(c.getColumnIndex("kelime_id")),
                    c.getString(c.getColumnIndex("ingilizce")),
                    c.getString(c.getColumnIndex("turkce")));

            kelimelerArrayList.add(k);

        }

        return kelimelerArrayList;
    }

    public ArrayList<Kelimeler> kelimeAra(Veritabani vt, String aramaKelime) {

        ArrayList<Kelimeler> kelimelerArrayList = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM kelimeler WHERE ingilizce like '%"+aramaKelime+"%'",null);

        while (c.moveToNext()) {
            Kelimeler k = new Kelimeler(c.getInt(c.getColumnIndex("kelime_id")),
                    c.getString(c.getColumnIndex("ingilizce")),
                    c.getString(c.getColumnIndex("turkce")));

            kelimelerArrayList.add(k);

        }

        return kelimelerArrayList;
    }

}
