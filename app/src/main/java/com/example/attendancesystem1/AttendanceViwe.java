package com.example.attendancesystem1;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AttendanceViwe extends AppCompatActivity {

    DBHelper d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_viwe);

        ListView listView = (ListView) findViewById(R.id.listView);

        d=new DBHelper(this);

        //populate an ArrayList<String> from the database and then view it
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = d.Viweatt();
        if(data.getCount() == 0){
            Toast.makeText(this, "No attendance found!!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
               // theList.add(data.getString(0));
                theList.add(data.getString(1));
                theList.add(data.getString(2));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);
            }
        }


    }
}