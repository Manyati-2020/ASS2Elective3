package com.example.petcarehotel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends AppCompatActivity {
    DatabaseHelperAdd d;
    EditText ad1, ad2, ad3, ad4;
    Button ba1, ba2, ba3, ba4, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ad1 = (EditText) findViewById(R.id.name);
        ad2 = (EditText) findViewById(R.id.owner);
        ad3 = (EditText) findViewById(R.id.cage);
        ad4 = (EditText) findViewById(R.id.id);

        ba1 = (Button) findViewById(R.id.bt1);
        b2 = (Button) findViewById(R.id.bt2);
        ba3 = (Button) findViewById(R.id.bt3);
        ba4 = (Button) findViewById(R.id.bt4);

        b2 = (Button) findViewById(R.id.canc);
        d = new DatabaseHelperAdd(this);

        AddData();
        ViewAll();
        UpdateData();
       DeleteData();


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main.this, LoginAct.class);
                startActivity(i);
            }
        });

    }

    public void AddData() {
        ba1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = d.insertData(ad1.getText().toString(),
                        ad2.getText().toString(),
                        ad3.getText().toString());
                if (isInserted = true)
                    Toast.makeText(Main.this, "Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Main.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void UpdateData() {
        ba2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean inUpdate = d.UpdateData(ad4.getText().toString(), ad1.getText().toString(),
                        ad2.getText().toString(), ad3.getText().toString());
                if (inUpdate == true)
                    Toast.makeText(Main.this, "Data updated", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Main.this, "Data Not updated", Toast.LENGTH_LONG).show();

            }
        });
    }
    public void ViewAll() {
        ba3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = d.getAllData();
                if (res.getCount() == 0) {
                    showMessege("Error ", " no Data found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id :" + res.getString(0) + "\n");
                    buffer.append("Name :" + res.getString(1) + "\n");
                    buffer.append("Owner :" + res.getString(2) + "\n");
                    buffer.append("Cage:" + res.getString(3) + "\n\n");
                }
                //show all data
                showMessege("data", buffer.toString());
            }
        });
    }
    public void showMessege(String title, String Messege) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable((true));
        builder.setTitle(title);
        builder.setMessage(Messege);
        builder.show();

    }
    public void DeleteData() {
        ba4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleteRows = d.deleteData(ad1.getText().toString());
                if (deleteRows > 0)
                    Toast.makeText(Main.this, "Data Deleted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Main.this, "Data Not Deleted", Toast.LENGTH_LONG).show();


            }
        });
    }
}

