package com.example.listmahasiswa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class ListData extends AppCompatActivity {

    String[] daftar;
    ListView listView;
    Menu menu;
    protected Cursor cursor;
    DBHelper database;
    public static ListData ld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdata);

        BottomNavigationView bottomNavigationView  = findViewById(R.id.bottom_menu);

        bottomNavigationView.setSelectedItemId(R.id.menu_view);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_view:
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu_home:
                        startActivity(new Intent(getApplicationContext(),Dashboard.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu_insert:
                        startActivity(new Intent(getApplicationContext(),InputData.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu_exit:
                        showDialog();
//                        startActivity(new Intent(getApplicationContext(),Login.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        ld = this;
        database = new DBHelper(this);
        RefreshList();
    }

    //EXIT
    private void showDialog(){
        AlertDialog.Builder alertExit = new AlertDialog.Builder(this);
        alertExit.setTitle("Yakin ingin keluar ?");

        alertExit.setMessage("klik Ya untuk keluar")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(ListData.this,Login.class));
            }
        })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = alertExit.create();
        alertDialog.show();

    }

    //otomatis menampilkan data yang sudah terupdate
    public void RefreshList() {
        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM mahasiswa",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0;i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(1).toString();
        }
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,daftar));
        listView.setSelected(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Lihat Data","Update Data","Hapus Data"};
                AlertDialog.Builder builder = new AlertDialog.Builder(ListData.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {
                        switch (item){
                            case 0:
                                Intent i = new Intent(getApplicationContext(), Detail.class);
                                i.putExtra("nama", selection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent in = new Intent(getApplicationContext(), UpdateData.class);
                                in.putExtra("nama", selection);
                                startActivity(in);
                                break;
                            case 2:
                                SQLiteDatabase db = database.getWritableDatabase();
                                db.execSQL("DELETE FROM mahasiswa WHERE nama = '" + selection + "'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter)listView.getAdapter()).notifyDataSetInvalidated();
    }
}