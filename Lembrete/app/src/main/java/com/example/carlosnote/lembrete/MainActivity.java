package com.example.carlosnote.lembrete;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtLbt;
    private EditText edtDatas;
    private CheckBox checkBoxUrgente;
    private Button btnSalvar;
    //private ArrayList<String> arrayLembretes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        edtLbt = (EditText) findViewById(R.id.edtLembrete);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);

        //novos inputs
        edtDatas  = (EditText) findViewById(R.id.edtData);
        checkBoxUrgente = (CheckBox) findViewById(R.id.checkUrgente);

        //habilitar o click do botão
        btnSalvar.setOnClickListener(this);

//        //iniciar array de lembretes
//        arrayLembretes = new ArrayList<String>();

    }

    @Override
    public void onClick(View view) {
        if(view == btnSalvar){
            salvarLembrete();
        }
    }

    private void salvarLembrete() {
        //salvar o lembrete na outra tela
        String lembrete = edtLbt.getText().toString();
        //TODO capturar as outras informações

        //TODO Salvar info no Banco


        //criar intenção de troca de tela
        Intent it = new Intent(this, SalvamentoActivity.class);

        //salvar paramentros para enviar
        it.putExtra("conteudo", lembrete);


        //trocar de tela
        startActivity(it);
    }
}
