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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class studentlogin extends AppCompatActivity {

    Button mark, scan , viwe;
    public static TextView qr, scode,scode1, sroll;
    DBHelper d;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogin);

        d=new DBHelper(this);

        qr = findViewById(R.id.textView5);
        scode = findViewById(R.id.eroll2);
        scode1=findViewById(R.id.timestamp);
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
                TextView textView = (TextView) findViewById(R.id.timestamp);
                String sc = getIntent().getStringExtra("code");
                scode.setText(sc);
                String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());
                textView.setText(sc+timestamp);
            }
        });


        String roll = getIntent().getStringExtra("rollno");
        sroll.setText(roll);
        String sc = getIntent().getStringExtra("code");
        scode.setText(sc);
        String sc1 = getIntent().getStringExtra("code");
        scode1.setText(sc1);



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
                String ts= scode1.getText().toString();

                if (ts.equals(qr1)) {

                    HashMap<String,Object> m=new HashMap<String,Object>();
                    m.put("Roll_no",sroll.getText().toString());
                    m.put("datetime",scode1.getText().toString());
                    FirebaseDatabase.getInstance().getReference().child(subcode1).push().setValue(m);

                    Boolean insert = d.Attendance(roll1,subcode1);
                    if(insert==true) {
                        Toast.makeText(studentlogin.this, "Attendance Marked", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),studentpage.class));
                    }

                } else if (qr1.equals("")) {
                    Toast.makeText(studentlogin.this, "Please Scan the QR code", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(studentlogin.this, "Please Rescan the QR code", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}