package com.example.ca1;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.utilities.Utilities;

public class MainActivity extends AppCompatActivity {
   EditText name,reg,cgpa,branch;
    DatabaseReference myref;
   TextView log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        name=findViewById(R.id.textName);
        reg=findViewById(R.id.textReg);
        cgpa=findViewById(R.id.textCgpa);
        branch=findViewById(R.id.textBranch);
        log=findViewById(R.id.save);
        myref= FirebaseDatabase.getInstance().getReference();
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(""+name.getText()))
                {
                    name.setError("Required Field..");
                    return;
                }
                if(TextUtils.isEmpty(""+reg.getText()))
                {
                    reg.setError("Required Field..");
                    return;
                }
                if(TextUtils.isEmpty(""+cgpa.getText()) || Float.parseFloat(""+cgpa.getText().toString())<0 || Float.parseFloat(""+cgpa.getText().toString())>10  )
                {
                    cgpa.setError("Invalid input..");
                    return;
                }
                if(TextUtils.isEmpty(""+branch.getText()))
                {
                    branch.setError("Required Field..");
                    return;
                }

                myref.child("Details").push().setValue("Name :"+name.getText().toString().replaceAll(" ","")+"\t\t\t\t\t"+"Cgpa: "+cgpa.getText()+"\n"+"Reg: "+reg.getText()+"\t\t\t\t\t"+"Branch :"+branch.getText());

                startActivity(new Intent(MainActivity.this,welcome.class));
            }
        });


    }
}