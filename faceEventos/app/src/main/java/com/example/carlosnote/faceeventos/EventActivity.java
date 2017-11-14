package com.example.carlosnote.faceeventos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class EventActivity extends AppCompatActivity {
    private ImageView imgFotoEvento;
    private TextView txtNome;
    private TextView txtDesc;
    private TextView txtData;
    private TextView txtComp;
    private TextView txtNaoResp;
    private TextView txtTalvez;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        imgFotoEvento = (ImageView) findViewById(R.id.imgFotoEvento);
        txtNome = (TextView) findViewById(R.id.txtNomeEvento);
        txtDesc = (TextView) findViewById(R.id.txtDescEvento);
        txtNaoResp = (TextView) findViewById(R.id.txtSemResp);
        txtTalvez = (TextView) findViewById(R.id.txtTalvez);
        txtComp = (TextView) findViewById(R.id.txtComp);
        txtData = (TextView) findViewById(R.id.txtDataEvento);

    }
}
