package com.example.listmahasiswa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        BottomNavigationView bottomNavigationView  = findViewById(R.id.bottom_menu);
        bottomNavigationView.setSelectedItemId(R.id.menu_home);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_view:
                        startActivity(new Intent(getApplicationContext(), ListData.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu_home:
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu_insert:
                        startActivity(new Intent(getApplicationContext(),InputData.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu_exit:
                        showDialog();
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
    private void showDialog(){
        AlertDialog.Builder alertExit = new AlertDialog.Builder(this);
        alertExit.setTitle("Yakin ingin keluar ?");

        alertExit.setMessage("klik Ya untuk keluar")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Dashboard.this,Login.class));
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

}