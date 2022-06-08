package com.example.attendancesystem1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class studentregistration extends AppCompatActivity {

    Button ssubmit;
    TextView roll, name, year, branch, password,pass2,subcode;
    DBHelper d;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentregistration);

        roll = findViewById(R.id.roll);
        name = findViewById(R.id.name);
        year = findViewById(R.id.year);
        branch = findViewById(R.id.branch);
        password = findViewById(R.id.password1);
        pass2=findViewById(R.id.password2);
        subcode=findViewById(R.id.sscode);

        d = new DBHelper(this);

        ssubmit = (Button) findViewById(R.id.btsubmit);
        ssubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String r = roll.getText().toString();
                String n = name.getText().toString();
                String y = year.getText().toString();
                String b = branch.getText().toString();
                String p = password.getText().toString();
                String p2=pass2.getText().toString();
                String sc=subcode.getText().toString();

                if(r.equals("")||n.equals("")||y.equals("")||b.equals("")||p.equals("")||p2.equals("")||sc.equals(""))
                    Toast.makeText(studentregistration.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(p.equals(p2)){
                        Boolean checkuser = d.checkroll(r);
                        if(checkuser==false){
                            Boolean insert = d.insertData(r,n,y,b,p,sc);
                            if(insert==true){
                                Toast.makeText(studentregistration.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),studentpage.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(studentregistration.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(studentregistration.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(studentregistration.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                } }
        });

    }
}