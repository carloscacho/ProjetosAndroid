package com.example.carlosnote.contatos.Model;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Carlos note on 11/09/2017.
 */

public class SingletonContatos {

    private static SQLiteDatabase conex = null;
    private static SingletonContatos instance = null;

    private SingletonContatos(Context context){
        try {
            DataBase dataBase = new DataBase(context);
            instance.conex = dataBase.getWritableDatabase();
            Log.i("#Banco", "O Banco foi criado com sucesso");
        }catch (SQLException ex){
            ex.printStackTrace();
            Log.i("#Banco", "Erro ao criar o Banco");
        }
    }

    public static SQLiteDatabase getInstance(Context context){
        if(instance == null){
            instance = new SingletonContatos(context);
        }
        return instance.getConex();
    }

    private SQLiteDatabase getConex(){
        return conex;
    }

}
