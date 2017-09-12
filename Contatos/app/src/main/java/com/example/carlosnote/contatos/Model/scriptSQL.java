package com.example.carlosnote.contatos.Model;

/**
 * Created by Carlos note on 11/09/2017.
 */

public class scriptSQL {

    public static String getSqlContatos(){

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("CREATE TABLE IF NOT EXISTS CONTATOS(");
        strBuilder.append("_id  INTEGER   NOT NULL   PRIMARY KEY  AUTOINCREMENT,");
        strBuilder.append("NOME VARCHAR(100),");
        strBuilder.append("TELEFONE VARCHAR(14),");
        strBuilder.append("SPNTELEFONE INTEGER,");
        strBuilder.append("DATASESP VARCHAR(14),");
        strBuilder.append("SPNDATASESP INTEGER");
        strBuilder.append(");");

        return strBuilder.toString();

    }
}
