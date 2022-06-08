package com.example.attendancesystem1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String dbname="Attendancesystem.db";
    public DBHelper(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create Table student(SN INTEGER PRIMARY KEY AUTOINCREMENT ,roll varchar NOT NULL, name TEXT  NOT NULL,year VARCHAR NOT NULL,branch text not null,Password VARCHAR NOT NULL,subcode varchar not null) ");   // create table

        sqLiteDatabase.execSQL("create Table faculty(SN INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT NOT NULL,collage text not null ,year VARCHAR NOT NULL,department text not null,Password VARCHAR NOT NULL,fsubcode varchar not null) ");   // create table

        sqLiteDatabase.execSQL("create Table Attendance(Roll_no varchar not null,Subject_code varchar not null,Marked_at DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists student");
        sqLiteDatabase.execSQL("drop Table if exists faculty");
    }
    public Boolean Attendance(String Roll_no,String Subject_code) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Roll_no",Roll_no);
        contentValues.put("Subject_code",Subject_code);

        long result = MyDB.insert("Attendance",null,contentValues);

        if (result == -1) return false;
        else
            return true;
    }
    public  Boolean insertData(String roll,String name,String year,String branch,String password,String subcode) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("roll", roll);
        contentValues.put("name", name);
        contentValues.put("year", year);
        contentValues.put("branch", branch);
        contentValues.put("Password", password);
        contentValues.put("subcode",subcode);

        long result = MyDB.insert("student", null, contentValues);

        if (result == -1) return false;
        else
            return true;
    }
    public  Boolean insertDatafaculty(String name,String collage,String year,String department,String password,String fsubcode){
        SQLiteDatabase MyDB =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("collage",collage);
        contentValues.put("year",year);
        contentValues.put("department",department);
        contentValues.put("Password",password);
        contentValues.put("fsubcode",fsubcode);

        long result= MyDB.insert("faculty",null,contentValues);

        if(result==-1) return false;
        else
            return  true;
    }
    public Boolean checkfusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from faculty where name = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean checkfusernamepassword(String name, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from faculty where name = ? and password = ?", new String[] {name,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkroll(String roll) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from student where roll = ?", new String[]{roll});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean checksusernamepassword(String roll, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from student where roll = ? and password = ?", new String[] {roll,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Cursor  Viweatt(){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from Attendance",null,null);
        return cursor;

    }



}
