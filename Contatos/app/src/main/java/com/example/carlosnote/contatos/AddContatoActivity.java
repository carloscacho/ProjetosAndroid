package com.example.carlosnote.contatos;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.carlosnote.contatos.Model.Dominio.Contato;
import com.example.carlosnote.contatos.Model.Dominio.RepositorioContatos;

import java.util.ArrayList;

public class AddContatoActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtNome;
    private EditText edtTelefone;
    private EditText edtData;

    private FloatingActionButton btnSalvar;
    private ImageButton imgFoto;

    private Spinner spnTelefone;
    private Spinner spnData;

    private ArrayAdapter<String> arrayTelefone;
    private ArrayAdapter<String> arrayData;

    private RepositorioContatos contatos;

    private Contato contatoUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contato);
        //Campos de texto
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtData = (EditText) findViewById(R.id.edtDatas);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        //Botões
        btnSalvar = (FloatingActionButton) findViewById(R.id.fabSalvar);
        imgFoto = (ImageButton) findViewById(R.id.imgFoto);
        //Caixa de seleção
        spnData = (Spinner) findViewById(R.id.spnDatas);
        spnTelefone = (Spinner) findViewById(R.id.spnTefone);
        //array adpters
        arrayTelefone = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        arrayData = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        //set dorp
        arrayData.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        arrayTelefone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //adpatar
        spnTelefone.setAdapter(arrayTelefone);
        spnData.setAdapter(arrayData);


        arrayData.addAll(new String[]{"Aniversario","Datas Comemorativas", "outros"});
        arrayTelefone.addAll(new String[]{"Celular","Casa","Trabalho","Outros"});



        contatos = new RepositorioContatos(this);
        btnSalvar.setOnClickListener(this);

        Intent it = getIntent();
        if(it.getExtras() != null && it.getExtras().containsKey("contato")){
            contatoUp = (Contato) it.getExtras().getSerializable("contato");
            preecherDados();
        }else {
            contatoUp = new Contato();
        }

    }

    @Override
    public void onClick(View view) {
        if(view.getId() ==  R.id.fabSalvar){
            salvarDados();
        }
    }

    public void salvarDados(){
        Contato contato = new Contato();
        contato.setId(contatoUp.getId());
        contato.setNome(edtNome.getText().toString());
        contato.setTelefone(edtTelefone.getText().toString());
        contato.setDataEspecial(edtData.getText().toString());

        //spn
        contato.setTipoData(spnData.getSelectedItemPosition());
        contato.setTipoTelefone(spnTelefone.getSelectedItemPosition());

        if(contatoUp.getId() == 0 )
            contatos.insertContato(contato);
        else
            contatos.updateContato(contato);

        finish();

    }

    public void preecherDados(){
        edtNome.setText(contatoUp.getNome());
        edtTelefone.setText(contatoUp.getTelefone());
        edtData.setText(contatoUp.getDataEspecial());

        spnData.setVerticalScrollbarPosition(contatoUp.getTipoData());
        spnTelefone.setVerticalScrollbarPosition(contatoUp.getTipoTelefone());
    }
}
