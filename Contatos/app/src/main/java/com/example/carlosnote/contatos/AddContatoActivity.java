package com.example.carlosnote.contatos;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.carlosnote.contatos.Model.Dominio.Contato;
import com.example.carlosnote.contatos.Model.Dominio.RepositorioContatos;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

    private int PERMISSAO_REQUEST = 3;
    private int GALERIA = 1;

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

        //permisão da galeria
        galeriaPermissao();
        imgFoto.setOnClickListener(this);

        arrayData.addAll(new String[]{"Aniversario","Datas Comemorativas", "outros"});
        arrayTelefone.addAll(new String[]{"Celular","Casa","Trabalho","Outros"});

        //implementar datePicker
        DateDialog dateClick = new DateDialog();
        edtData.setOnClickListener(dateClick);
        edtData.setOnFocusChangeListener(dateClick);

        //Mascara de telefone
        edtTelefone.addTextChangedListener(new PhoneNumberFormattingTextWatcher("BR"));


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

        if(view  == imgFoto){
            AlertDialog.Builder build = new AlertDialog.Builder(this);
            build.setTitle("Galeria ou Camera");
            build.setMessage("Escolha entre Galeria ou Camera");
            build.setPositiveButton("Galeria", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    galeria();
                }
            });
            build.setNegativeButton("Camera", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    camera();
                }
            });
            build.setNeutralButton("Cancelar", null);
            build.show();
        }
    }

    public void galeria(){
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(intent, GALERIA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == GALERIA) {
            Uri selectedImage = data.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
            imgFoto.setImageBitmap(thumbnail);
        }

    }

    public void galeriaPermissao(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSAO_REQUEST);
            }
        }
    }

    public void camera(){

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

    public void selecionarData(){
        Calendar cal = Calendar.getInstance();
        int ano = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePiker = new DatePickerDialog(this,new DateReturn(),ano,mes,dia);
        datePiker.show();
    }

    public class DateDialog implements View.OnClickListener, View.OnFocusChangeListener{

        @Override
        public void onClick(View view) {
            selecionarData();
        }

        @Override
        public void onFocusChange(View view, boolean b) {
            if(b){
               selecionarData();
            }
        }
    }

    public class DateReturn implements DatePickerDialog.OnDateSetListener{

        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Calendar dateCaledar = Calendar.getInstance();
            dateCaledar.set(i,i1,i2);

            Date date = new Date();
            date = dateCaledar.getTime();

            DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
            edtData.setText(format.format(date));

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSAO_REQUEST) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        // A permissão foi concedida. Pode continuar
            } else {
        // A permissão foi negada. Precisa ver o que deve ser desabilitado
            }
            return;
        }
    }
}
