package com.misiontic.messageapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText txtEmail,txtPassUno,txtPassDos;
    private Button btnRegistro;
    private FirebaseAuth myAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.myAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.txtEmail    = findViewById(R.id.txtEmailRegistro);
        this.txtPassUno  = findViewById(R.id.txtContraRegistro_1);
        this.txtPassDos  = findViewById(R.id.txtContraRegistro_2);
        this.btnRegistro = findViewById(R.id.btnRegistro);

        this.btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txtEmail.getText().toString().isEmpty() || txtPassUno.getText().toString().isEmpty() || txtPassDos.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                }else {
                    if(txtPassUno.getText().toString().equals(txtPassDos.getText().toString())){
                        registrarUsuario(txtEmail.getText().toString(),txtPassUno.getText().toString());
                    }else{
                        Toast.makeText(RegisterActivity.this, "Las contraseñas no coinsiden", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuario = this.myAuth.getCurrentUser();
        if(usuario!=null){
            //Implementar metodo
        }
    }

    private void registrarUsuario(String email, String password) {

        this.myAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    Toast.makeText(RegisterActivity.this, "Registro completado con exito", Toast.LENGTH_SHORT).show();
                    Intent goToLogin = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(goToLogin);
                    finish();

                }else{
                    Toast.makeText(RegisterActivity.this, "Error al realizar el registro", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}