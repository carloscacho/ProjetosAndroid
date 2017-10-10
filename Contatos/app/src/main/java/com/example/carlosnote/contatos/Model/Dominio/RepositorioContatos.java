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

    public boolean updateContato(Contato contato){
        try{
            ContentValues value = new ContentValues();
            value.put("NOME", contato.getNome());
            value.put("TELEFONE", contato.getTelefone());
            value.put("SPNTELEFONE", contato.getTipoTelefone());
            value.put("DATASESP", contato.getDataEspecial());
            value.put("SPNDATASESP", contato.getTipoData());

            String[] ids = new String[]{String.valueOf(contato.getId())};

            conexao.update("CONTATOS",value,"_id = ?", ids);
            return true;
        }catch (SQLException ex){
            Log.e("Erro ao atualizar", ex.getMessage());
            return false;
        }


    }

    public void deleteContato(Contato contato){
        String[] ids = new String[]{String.valueOf(contato.getId())};
        conexao.delete("CONTATOS","_id = ?", ids);
    }



    public ArrayList<Contato> getListContatos(){
        ArrayList<Contato> contatos = new ArrayList<Contato>();

        Cursor cursor = conexao.query("CONTATOS",null,null,null,null,null,"NOME COLLATE NOCASE");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{
                int id = cursor.getInt(Contato.ID);
                String nome = cursor.getString(Contato.NOME);
                String telefone = cursor.getString(Contato.TELEFONE);
                String data = cursor.getString(Contato.DATAESP);

                //spn
                int tipoTelefone = cursor.getInt(Contato.TIPOTELEFONE);
                int tipoData = cursor.getInt(Contato.TIPODATAESP);

                //criando e addicionando contato
                Contato tempContato = new Contato(id,nome,telefone,tipoTelefone,data,tipoData);
                contatos.add(tempContato);

            }while (cursor.moveToNext());
        }
        return contatos;
    }

}
