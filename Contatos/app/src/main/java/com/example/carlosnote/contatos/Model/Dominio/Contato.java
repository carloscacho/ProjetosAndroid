package com.example.carlosnote.contatos.Model.Dominio;

import java.io.Serializable;

/**
 * Created by Carlos note on 02/10/2017.
 */

public class Contato implements Serializable {

    private int id;
    private String nome;
    private String telefone;
    private int tipoTelefone;
    private String dataEspecial;
    private int tipoData;

    public Contato(){
        //nada
    }

    public Contato(int id, String nome,
                   String telefone, int tipoTelefone,
                   String dataEspecial, int tipoData) {
        this.setId(id);
        this.setNome(nome);
        this.setTelefone(telefone);
        this.setTipoTelefone(tipoTelefone);
        this.setDataEspecial(dataEspecial);
        this.setTipoData(tipoData);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(int tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public String getDataEspecial() {
        return dataEspecial;
    }

    public void setDataEspecial(String dataEspecial) {
        this.dataEspecial = dataEspecial;
    }

    public int getTipoData() {
        return tipoData;
    }

    public void setTipoData(int tipoData) {
        this.tipoData = tipoData;
    }
}
