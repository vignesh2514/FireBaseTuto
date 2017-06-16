package com.ench.v7ench.firebasetuto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
private Button button,buttonsec;
    EditText editTextn,editTexte;
    String stringn,stringe;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
editTextn=(EditText) findViewById(R.id.name);
        editTexte=(EditText) findViewById(R.id.email);
        button=(Button) findViewById(R.id.button2);
        buttonsec=(Button) findViewById(R.id.hello);
        buttonsec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Listingjingalo.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringn=editTextn.getText().toString();
                stringe=editTexte.getText().toString();

                HashMap<String,String> datamap= new HashMap<String, String>();
                datamap.put("Name",stringn);
                datamap.put("Email",stringe);
                mDatabase.push().setValue(datamap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "SUCCESS  ", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(MainActivity.this,Listingjingalo.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Errrr", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
    }


}
