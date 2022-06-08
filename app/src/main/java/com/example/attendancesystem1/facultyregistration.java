package com.example.attendancesystem1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class facultyregistration extends AppCompatActivity {

    Button fsubmit;
    public static EditText name,collage, year,department, password,pass2,fsubcode;
    DBHelper d;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultyregistration);

        name = findViewById(R.id.fname);
        collage=findViewById(R.id.fcollage);
        year = findViewById(R.id.fyear);
        department = findViewById(R.id.fdepartment);
        password = findViewById(R.id.fPassword);
        pass2=findViewById(R.id.fPassword2);
        fsubcode=findViewById(R.id.fscode);

        d = new DBHelper(this);

        fsubmit = (Button) findViewById(R.id.btfsubmit);
        fsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fn = name.getText().toString();
                String fc = collage.getText().toString();
                String fy = year.getText().toString();
                String de = department.getText().toString();
                String fp = password.getText().toString();
                String fp2=pass2.getText().toString();
                String fsc=fsubcode.getText().toString();


                if(fn.equals("")||fc.equals("")||fy.equals("")||de.equals("")||fp.equals("")||fp2.equals("")||fsc.equals(""))
                    Toast.makeText(facultyregistration.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(fp.equals(fp2)){
                        Boolean checkuser = d.checkfusername(fn);
                        if(checkuser==false){
                            Boolean insert = d.insertDatafaculty(fn,fc,fy,de,fp,fsc);
                            if(insert==true){
                                Toast.makeText(facultyregistration.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),facultypage.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(facultyregistration.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(facultyregistration.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(facultyregistration.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                } }
        });

    }
}