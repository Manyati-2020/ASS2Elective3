package com.example.petcarehotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginAct extends AppCompatActivity {
    EditText ed1, ed2;
    Button b1, b2, b3;
    DatabaseHelper BB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_act);

        BB = new DatabaseHelper(this);
        ed1 = findViewById(R.id.user);
        ed2 = findViewById(R.id.pass);
        b1 = findViewById(R.id.log);
        b2 = findViewById(R.id.canc);
        b3 = findViewById(R.id.reg);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

       /* b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });*/
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String user = ed1.getText().toString().trim();
                String pwd = ed2.getText().toString().trim();
                boolean res = BB.checkUser(user, pwd);
                if (res == true) {
                    Toast.makeText(LoginAct.this, "Successfully Logged in !!", Toast.LENGTH_SHORT).show();
                    Intent mov = new Intent(LoginAct.this,Main.class);
                    startActivity(mov);
                } else {
                    Toast.makeText(LoginAct.this, "Login Error!!  ", Toast.LENGTH_SHORT).show();

                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(LoginAct.this, Register.class);
                startActivity(register);
            }
        });
    }
}