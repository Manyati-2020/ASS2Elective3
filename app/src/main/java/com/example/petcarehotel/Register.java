package com.example.petcarehotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {


    DatabaseHelper BB;
    EditText txtUserName, txtPassword, txtConfPassword;
    Button bRegister,bCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        BB = new DatabaseHelper(this);
        txtUserName = (EditText) findViewById(R.id.user);
        txtPassword = (EditText) findViewById(R.id.pass);
        txtConfPassword = (EditText) findViewById(R.id.confPassword);
        bRegister = (Button) findViewById(R.id.reg);
        bCancel = (Button) findViewById(R.id.canc);


        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Main = new Intent(Register.this, LoginAct.class);
                startActivity(Main);
            }
        });

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = txtUserName.getText().toString().trim();
                String pwd = txtPassword.getText().toString().trim();
                String conf_pwd = txtConfPassword.getText().toString().trim();

                if (pwd.equals(conf_pwd)) {

                    long val = BB.addUser(user, pwd);
                    if (val > 0) {
                        Toast.makeText(Register.this, "You are now Registered ", Toast.LENGTH_SHORT).show();
                        Intent move = new Intent(Register.this, LoginAct.class);
                        startActivity(move);
                    } else {
                        Toast.makeText(Register.this, "Registration Error ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Register.this, "Password does not match ", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}