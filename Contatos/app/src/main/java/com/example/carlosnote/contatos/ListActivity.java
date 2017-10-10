package com.example.carlosnote.contatos;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.carlosnote.contatos.Model.Dominio.Contato;
import com.example.carlosnote.contatos.Model.Dominio.RepositorioContatos;
import com.example.carlosnote.contatos.Model.SingletonContatos;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener{

    private EditText edtPesquisa;
    private Button btnLimpar;
    private ListView lsvContatos;
    private ArrayAdapter<Contato> arrayContatos;
    private FloatingActionButton fab;

    private RepositorioContatos repositorioContatos;

    private Contato deleteOrUpdate;

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
        arrayContatos = new ArrayAdapter<Contato>(this,android.R.layout.simple_list_item_1);
        lsvContatos.setAdapter(arrayContatos);

        //ativando botão limpar
        btnLimpar.setOnClickListener(this);

        //criar o banco
        repositorioContatos = new RepositorioContatos(this);

        //habilitar o click longo
        lsvContatos.setOnItemLongClickListener(this);


    }


    @Override
    protected void onResume() {
        super.onResume();

        reload();

    }

    public void reload(){
        //getContatos
        ArrayList<Contato> listContatos = new ArrayList<>();
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


    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        deleteOrUpdate = this.arrayContatos.getItem(i);

        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setTitle("Deletar ou Alterar");
        build.setMessage("Escolha entre deletar ou alterar");
        build.setPositiveButton("Deletar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteContato();
            }
        });
        build.setNegativeButton("Alterar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alterarContato();
            }
        });
        build.setNeutralButton("Cancelar", null);
        build.show();
        return true;
    }

    public void deleteContato(){
        repositorioContatos.deleteContato(deleteOrUpdate);
        reload();
    }

    public void alterarContato(){
        Intent it = new Intent(this,AddContatoActivity.class);
        //enviar objeto contato
        it.putExtra("contato", deleteOrUpdate);
        //chamar a outra tela
        startActivity(it);
    }
}
