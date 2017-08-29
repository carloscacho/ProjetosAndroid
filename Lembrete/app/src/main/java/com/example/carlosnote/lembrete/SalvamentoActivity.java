package com.example.carlosnote.lembrete;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SalvamentoActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnFechar;
    private ListView lsvLembretes;
    private ArrayAdapter<String> arrayLembretes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salvamento);

        btnFechar = (Button) findViewById(R.id.btnFechar);
        lsvLembretes = (ListView) findViewById(R.id.lsvLembretes);

        //setando os clicks
        btnFechar.setOnClickListener(this);

        //criar o array adpater
        arrayLembretes = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        lsvLembretes.setAdapter(arrayLembretes);

        //receber os paramentros
        Bundle bundle = getIntent().getExtras();

        //SALVAMENTO DE STRING
        //String lembrenteRecebido = bundle.getString("conteudo");

        //addicinar dentro do lembrete
        //arrayLembretes.add(lembrenteRecebido);

        //salvamento de lista
        ArrayList<String> lembretes = bundle.getStringArrayList("conteudo");

        arrayLembretes.addAll(lembretes);


    }

    @Override
    public void onClick(View view) {
        if(view == btnFechar){
            //fechar tela
            finish();
        }
    }
}
