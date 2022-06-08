package com.example.attendancesystem1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class facultypage extends AppCompatActivity {

    Button flogin,fregister;
    public static EditText fusername,fpass,fscode;
    DBHelper d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultypage);



        fusername=findViewById(R.id.fuser);
        fpass=findViewById(R.id.fpass);
        fscode=findViewById(R.id.fcode);
        fregister=findViewById(R.id.fregister);
        fregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),facultyregistration.class));
            }
        });
        d = new DBHelper(this);
        flogin=findViewById(R.id.flogin);
        flogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = fusername.getText().toString();
                String pass = fpass.getText().toString();



                if(user.equals("")||pass.equals(""))
                    Toast.makeText(facultypage.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = d.checkfusernamepassword(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(facultypage.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), facultylogin.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(facultypage.this, "Invalid Faculty", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
