package com.example.ca1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class show extends AppCompatActivity {
    ListView lstvw;
    DatabaseReference myref;
    String s = "";
    String ss = "";
    ArrayAdapter adapter;
    TextView name1;
    ArrayList<String> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        lstvw = findViewById(R.id.list);
        name1 = findViewById(R.id.name);

        myref = FirebaseDatabase.getInstance().getReference();

        name1.setText("Details");

        myref.child("Details").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
               list = new ArrayList<>();


                for (DataSnapshot ds : iterable) {
                    s = "" + ds.getValue();
                    ss = "" + ss + ds.getValue();

                    list.add(s);


                }
                 adapter = new ArrayAdapter(show.this, android.R.layout.simple_expandable_list_item_1, list);
                lstvw.setAdapter(adapter);

                //  Toast.makeText(show.this, ""+ss, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


       lstvw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                String selectedFromList =(String) (lstvw.getItemAtPosition(myItemInt));
                Toast.makeText(show.this, ""+selectedFromList, Toast.LENGTH_SHORT).show();
                String [] lets=selectedFromList.split(" ");
                String gg="";
                for(String s:lets)
                {
                    gg=gg+s+"\n";
                }
                String []gets=gg.split("\\R",-1);
                Toast.makeText(show.this, ""+gets[2], Toast.LENGTH_SHORT).show();
                if(Float.parseFloat(""+gets[2])>=6.6 || Float.parseFloat(""+gets[2])<=10.0 ) {
                    startActivity(new Intent(show.this, myJson.class));
                }
                else
                {
                    Toast.makeText(show.this, "No valid Drive for you ...!", Toast.LENGTH_SHORT).show();
                }

            }
        });



        // Toast.makeText(this, list.size()+""+list+s+ss, Toast.LENGTH_SHORT).show();

    }
}