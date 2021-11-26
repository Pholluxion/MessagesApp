package com.misiontic.messageapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextView goToReg;
    private EditText txtEmail,txtPass;
    private Button btnLogIn;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.firebaseAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.goToReg  = findViewById(R.id.btnGoToRegistro);
        this.txtEmail = findViewById(R.id.txtEmailLogin);
        this.txtPass  = findViewById(R.id.txtContraLogin);
        this.btnLogIn = findViewById(R.id.btnLogin);

        this.btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txtEmail.getText().toString().isEmpty() || txtPass.getText().toString().isEmpty()){

                    Toast.makeText(LoginActivity.this, "Debe llenar el campo de correo y contraseña", Toast.LENGTH_SHORT).show();

                }else{

                    logIn(txtEmail.getText().toString(),txtPass.getText().toString());

                }
            }
        });

        this.goToReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!=null){
            //implementar si ya inicio
   /*         Intent intent =  new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();*/
        }
    }

    private void logIn(String email, String pass) {

        this.firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Ocurrio un error", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}