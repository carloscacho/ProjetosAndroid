package com.example.carlosnote.contatos;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.carlosnote.contatos.Model.Dominio.RepositorioContatos;
import com.example.carlosnote.contatos.Model.SingletonContatos;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtPesquisa;
    private Button btnLimpar;
    private ListView lsvContatos;
    private ArrayAdapter<String> arrayContatos;
    private FloatingActionButton fab;

    private RepositorioContatos repositorioContatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);


        //Meu codigo
        edtPesquisa = (EditText) findViewById(R.id.edtPesquisa);
        btnLimpar = (Button) findViewById(R.id.btnClear);
        lsvContatos = (ListView) findViewById(R.id.lsvContatos);

        //criar o adpater e colocar na lista
        arrayContatos = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        lsvContatos.setAdapter(arrayContatos);

        //ativando botão limpar
        btnLimpar.setOnClickListener(this);

        //criar o banco
        repositorioContatos = new RepositorioContatos(this);


    }

    @Override
    protected void onResume() {
        super.onResume();

        //getContatos
        ArrayList<String> listContatos = new ArrayList<>();
        listContatos = repositorioContatos.getListContatos();
        arrayContatos.clear();
        arrayContatos.addAll(listContatos);
    }

    @Override
    public void onClick(View view) {
        if(view == btnLimpar){
            edtPesquisa.setText("");
        }else if(view == fab){
            //chamar outra tela
            Intent it = new Intent(this,AddContatoActivity.class);
            startActivity(it);
        }
    }
}
