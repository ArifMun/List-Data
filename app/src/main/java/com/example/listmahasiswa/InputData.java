package com.example.listmahasiswa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class InputData extends AppCompatActivity {

    DBHelper database;
    RadioGroup rgKelas,rgJeniskelamin;
    RadioButton rbKelas,rbJeniskelamin;
    EditText nim,nama,tempat_lahir,alamat,tgl_lahir;
    Button tambah,home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        database = new DBHelper(this);
        nama = findViewById(R.id.nama);
        nim = findViewById(R.id.nim);
        tempat_lahir = findViewById(R.id.tempat_lahir);
        tgl_lahir = findViewById(R.id.tgl_lahir);
        alamat = findViewById(R.id.alamat);
        tambah = findViewById(R.id.tambah);
        home = findViewById(R.id.home);
        rgKelas = (RadioGroup)findViewById(R.id.kelas);
        rgJeniskelamin = (RadioGroup)findViewById(R.id.jenis_kelamin);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idKelas = rgKelas.getCheckedRadioButtonId();
                int idJeniskelamin = rgJeniskelamin.getCheckedRadioButtonId();

                rbKelas = (RadioButton)findViewById(idKelas);
                rbJeniskelamin = (RadioButton)findViewById(idJeniskelamin);
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("INSERT INTO mahasiswa(nama,nim,kelas,jenis_kelamin,tempat_lahir,tgl_lahir,alamat) VALUES('" +
                        nama.getText().toString() + "','" +
                        nim.getText().toString() + "','" +
                        rbKelas.getText().toString() + "','" +
                        rbJeniskelamin.getText().toString() + "','" +
                        tempat_lahir.getText().toString() + "','" +
                        tgl_lahir.getText().toString() + "','" +
                        alamat.getText().toString() + "')");
                Toast.makeText(InputData.this, "tersimpan", Toast.LENGTH_SHORT).show();
                Refresh();
            }

            private void Refresh() {
                Intent i = new Intent(getApplicationContext(), InputData.class);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InputData.this,Dashboard.class));
            }
        });
    }
}