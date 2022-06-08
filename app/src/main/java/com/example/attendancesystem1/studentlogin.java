package com.example.attendancesystem1;

import static java.text.DateFormat.DEFAULT;

import static javax.xml.datatype.DatatypeConstants.DATETIME;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class studentlogin extends AppCompatActivity {

    Button mark, scan , viwe;
    public static TextView qr, scode, sroll;
    DBHelper d;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogin);

        d=new DBHelper(this);

        qr = findViewById(R.id.textView5);
        scode = findViewById(R.id.eroll2);
        sroll = findViewById(R.id.eroll);
        viwe=findViewById(R.id.mark3);
        viwe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AttendanceViwe.class));
            }
        });


        scan = findViewById(R.id.mark2);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), scannerView.class));
            }
        });


        String roll = getIntent().getStringExtra("rollno");
        sroll.setText(roll);

        String sc = getIntent().getStringExtra("code");
        scode.setText(sc);



       /* String qr2=getIntent().getStringExtra("qr");
        TextView qr3=findViewById(R.id.textView5);
        qr3.setText(qr2);*/


        mark = findViewById(R.id.mark);
        mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String roll1=sroll.getText().toString();
                String subcode1 = scode.getText().toString();
                String qr1 = qr.getText().toString();

                if (subcode1.equals(qr1)) {

                    HashMap<String,Object> m=new HashMap<String,Object>();
                    m.put("Roll_no",sroll.getText().toString());
                   // m.put("datetime",DATETIME.toString());
                    FirebaseDatabase.getInstance().getReference().child(qr1).push().setValue(m);

                    Boolean insert = d.Attendance(roll1,qr1);
                    if(insert==true) {
                        Toast.makeText(studentlogin.this, "Attendance Marked", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),studentpage.class));
                    }

                } else if (qr1.equals("")) {
                    Toast.makeText(studentlogin.this, "Please Scan the QR code", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(studentlogin.this, "Something Wrong!!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}