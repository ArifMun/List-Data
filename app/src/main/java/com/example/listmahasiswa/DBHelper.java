package com.example.listmahasiswa;
import static androidx.room.RoomMasterTable.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
public class DBHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "mahasiswa_table";
    public static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mahasiswa.db";

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql ="CREATE TABLE mahasiswa(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nama VARCHAR(30) null,nim INT UNIQUE null," +
                "kelas VARCHAR(10) null," +
                "jenis_kelamin VARCHAR(20) null," +
                "tempat_lahir TEXT null," +
                "tgl_lahir TEXT null," +
                "alamat TEXT null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db0, int db1, int db2) {
        db0.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db0);
    }
}
