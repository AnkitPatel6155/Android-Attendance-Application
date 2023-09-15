package com.example.attendancesystem1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class studentpage extends AppCompatActivity {

    Button slogin;
    EditText susername,spass,scode;
    DBHelper d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentpage);


        susername=findViewById(R.id.suser);
        spass=findViewById(R.id.spass);
        slogin=findViewById(R.id.slogin);
        scode=findViewById(R.id.sc);

        d = new DBHelper(this);

        slogin=findViewById(R.id.slogin);
        slogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = susername.getText().toString();
                String pass = spass.getText().toString();
                String ssc = scode.getText().toString();



                if(user.equals("")||pass.equals(""))
                    Toast.makeText(studentpage.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = d.checksusernamepassword(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(studentpage.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(),studentlogin.class);
                        intent.putExtra("rollno",user);
                        intent.putExtra("code",ssc);
                        startActivity(intent);
                    }else{
                        Toast.makeText(studentpage.this, "Invalid Student", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void register(View view) {
        startActivity(new Intent(getApplicationContext(),studentregistration.class));
    }
}
