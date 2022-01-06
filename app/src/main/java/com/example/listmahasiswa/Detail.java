package com.example.listmahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Detail extends AppCompatActivity {

    protected Cursor cursor;
    DBHelper database;
    TextView nama,nim,kelas,jenis_kelamin,tempat_lahir,alamat,tgl_lahir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        database = new DBHelper(this);
        nama = findViewById(R.id.nama);
        nim = findViewById(R.id.nim);
        kelas = findViewById(R.id.kelas);
        jenis_kelamin = findViewById(R.id.jenis_kelamin);
        tempat_lahir = findViewById(R.id.tempat_lahir);
        tgl_lahir = findViewById(R.id.tgl_lahir);
        alamat = findViewById(R.id.alamat);

        SQLiteDatabase db = database.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM mahasiswa WHERE nama = '" +
                getIntent().getStringExtra("nama") +"'",null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);
            nama.setText(cursor.getString(1));
            nim.setText(cursor.getString(2));
            kelas.setText(cursor.getString(3));
            jenis_kelamin.setText(cursor.getString(4));
            tempat_lahir.setText(cursor.getString(5));
            tgl_lahir.setText(cursor.getString(6));
            alamat.setText(cursor.getString(7));
        }
    }
}