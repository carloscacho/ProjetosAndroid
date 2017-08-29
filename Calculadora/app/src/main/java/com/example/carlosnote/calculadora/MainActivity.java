package com.example.carlosnote.calculadora;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edtNumero1;
    private EditText edtNumero2;
    private Button btnCalcular;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ligar elementos da activity com o layout
        edtNumero1 = (EditText) findViewById(R.id.edtNum1);
        edtNumero2 = (EditText) findViewById(R.id.edtNum2);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        resultado = (TextView) findViewById(R.id.txtResultado);




        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ler o conteúdo que dos campos de numeros
                if(edtNumero1.getText().toString().equals("") ||
                        edtNumero2.getText().toString().equals("")){
                    //resultado.setText("O campo de numero1  ou numero2 está em branco ");
                    //

                    alert();

                }else {
                    double num1 = Double.parseDouble(edtNumero1.getText().toString());
                    double num2 = Double.parseDouble(edtNumero2.getText().toString());

                    Double res = num1 + num2;

                    resultado.setText(res.toString());
                    //String.valueOf(res);
                }
            }
        });

    }

    public void alert(){
        //Caixa de dialogo para apresentação
//        AlertDialog alert = new AlertDialog.Builder(this).
//                setMessage("O campo de numero1  ou numero2 está em branco ").
//                setPositiveButton("OK",null).show();

        //mensagem que surge na tela e fica por um tempo determinado
        Toast toast = Toast.makeText(this,
                "O campo de numero1  ou numero2 está em branco",
                Toast.LENGTH_LONG);
        toast.show();
    }
}
