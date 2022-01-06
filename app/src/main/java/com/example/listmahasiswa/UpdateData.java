package com.example.listmahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UpdateData extends AppCompatActivity {

    protected Cursor cursor;
    DBHelper database;
    RadioGroup rgKelas,rgJeniskelamin;
    RadioButton rbKelas,rbJeniskelamin;
    EditText nim,nama,tempat_lahir,tgl_lahir,alamat;
    Button update;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        database = new DBHelper(this);
        nama = findViewById(R.id.nama);
        nim = findViewById(R.id.nim);
        tempat_lahir = findViewById(R.id.tempat_lahir);
        tgl_lahir = findViewById(R.id.tgl_lahir);
        alamat = findViewById(R.id.alamat);
        update = findViewById(R.id.update);
        rgKelas = (RadioGroup)findViewById(R.id.kelas);
        rgJeniskelamin = (RadioGroup)findViewById(R.id.jenis_kelamin);

        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM mahasiswa WHERE nama = '" +
                getIntent().getStringExtra("nama")+"'",null);
        cursor.moveToFirst();
        if (cursor.getCount() >0){
            cursor.moveToPosition(0);
            nama.setText(cursor.getString(1).toString());
            nim.setText(cursor.getString(2).toString());
            tempat_lahir.setText(cursor.getString(5).toString());
            tgl_lahir.setText(cursor.getString(6).toString());
            alamat.setText(cursor.getString(7).toString());
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idKelas = rgKelas.getCheckedRadioButtonId();
                int idJeniskelamin = rgJeniskelamin.getCheckedRadioButtonId();
                rbKelas = (RadioButton)findViewById(idKelas);
                rbJeniskelamin = (RadioButton)findViewById(idJeniskelamin);
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("UPDATE mahasiswa SET nama='" +
                        nama.getText().toString() +"',nim='" +
                        nim.getText().toString() +"',kelas='" +
                        rbKelas.getText().toString() + "',jenis_kelamin='" +
                        rbJeniskelamin.getText().toString() + "',tempat_lahir='" +
                        tempat_lahir.getText().toString() +"',tgl_lahir='" +
                        tgl_lahir.getText().toString() +"',alamat='" +
                        alamat.getText().toString() +"' WHERE nama = '" +
                        getIntent().getStringExtra("nama") + "'");
                Toast.makeText(UpdateData.this, "Data Berhasil Di Update", Toast.LENGTH_SHORT).show();
                ListData.ld.RefreshList();
                finish();
            }
        });
    }
}