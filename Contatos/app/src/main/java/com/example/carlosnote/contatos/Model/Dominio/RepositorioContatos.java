package com.example.carlosnote.contatos.Model.Dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.carlosnote.contatos.Model.SingletonContatos;

import java.util.ArrayList;

/**
 * Created by Carlos note on 02/10/2017.
 */

public class RepositorioContatos {

    private SQLiteDatabase conexao;

    public RepositorioContatos(Context context){
        conexao = SingletonContatos.getInstance(context);
    }


    public boolean insertContato(Contato contato){
        try{
            ContentValues value = new ContentValues();
            value.put("NOME", contato.getNome());
            value.put("TELEFONE", contato.getTelefone());
            value.put("SPNTELEFONE", contato.getTipoTelefone());
            value.put("DATASESP", contato.getDataEspecial());
            value.put("SPNDATASESP", contato.getTipoData());
            conexao.insertOrThrow("CONTATOS",null,value);
            return true;
        }catch (SQLException ex){
            Log.e("Erro ao inserir", ex.getMessage());
            return false;
        }


    }

    public ArrayList<String> getListContatos(){
        ArrayList<String> contatos = new ArrayList<String>();

        Cursor cursor = conexao.query("CONTATOS",null,null,null,null,null,null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{
                String nome = cursor.getString(1);
                String telefone = cursor.getString(2);
                contatos.add(nome + "\n" + telefone);
            }while (cursor.moveToNext());
        }
        return contatos;
    }

}
