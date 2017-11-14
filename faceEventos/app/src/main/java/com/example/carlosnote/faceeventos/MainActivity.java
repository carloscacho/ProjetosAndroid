package com.example.carlosnote.faceeventos;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button btnLogout;
    private TextView txtNome;
    private TextView txtEmail;
    private ImageView imgFoto;
    private ListView lsvEventos;
    private ArrayAdapter arrayEventos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if(AccessToken.getCurrentAccessToken() == null){
//
//            goLoginPage();
//        }



        txtNome = (TextView) findViewById(R.id.txtNome);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        imgFoto = (ImageView) findViewById(R.id.imgFoto);
        lsvEventos = (ListView) findViewById(R.id.lsvEventos);

        btnLogout = (Button) findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                goLoginPage();
            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            String nome = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            txtNome.setText(nome);
            txtEmail.setText(email);
            //imgFoto.setImageURI(photoUrl);
            Glide.with(this)
                    .load(photoUrl) // the uri you got from Firebase
                    .centerCrop()
                    .into(imgFoto);

        }else{
            goLoginPage();
        }

        GraphRequest request = GraphRequest.newGraphPathRequest(
                AccessToken.getCurrentAccessToken(),
                "/search",
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        // Insert your code here
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("q", "cuiaba");
        parameters.putString("type", "event");
        parameters.putString("fields", "name,place,attending_count,cover,description,noreply_count,maybe_count");
        request.setParameters(parameters);
        request.executeAsync();

    }

    private void goLoginPage(){
        Intent it = new Intent(this, LoginActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(it);
    }
}
