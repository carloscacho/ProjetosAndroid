package com.example.carlosnote.contatos.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Carlos note on 11/09/2017.
 */

public class DataBase extends SQLiteOpenHelper {

    public DataBase(Context context){
        super(context,"LISTACONTATOS",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(scriptSQL.getSqlContatos());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
