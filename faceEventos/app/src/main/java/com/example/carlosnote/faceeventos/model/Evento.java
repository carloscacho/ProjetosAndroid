package com.example.carlosnote.faceeventos.model;

import java.io.Serializable;

/**
 * Created by Carlos note on 13/11/2017.
 */

public class Evento implements Serializable {
    private String nome;
    private String descricao;
    private String data;
    private int comparecer;
    private int talvez;
    private int naoResp;
    private String photoURL;

    public Evento(String nome, String descricao, String data, int comparecer, int talvez, int naoResp, String photoURL) {
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.comparecer = comparecer;
        this.talvez = talvez;
        this.naoResp = naoResp;
        this.photoURL = photoURL;
    }

    public Evento() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getComparecer() {
        return comparecer;
    }

    public void setComparecer(int comparecer) {
        this.comparecer = comparecer;
    }

    public int getTalvez() {
        return talvez;
    }

    public void setTalvez(int talvez) {
        this.talvez = talvez;
    }

    public int getNaoResp() {
        return naoResp;
    }

    public void setNaoResp(int naoResp) {
        this.naoResp = naoResp;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }
}
