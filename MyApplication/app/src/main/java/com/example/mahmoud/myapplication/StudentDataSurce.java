package com.example.mahmoud.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mahmoud on 25/12/17.
 */

public class StudentDataSurce {
    MySql mySql;
    SQLiteDatabase sqLiteDatabase;
    String [] allColumes = new String[]{mySql.ID,mySql.NAME,mySql.Email,mySql.NUMBER};


    public StudentDataSurce(Context context){
        mySql = new MySql(context);

    }

    //func open connection
    public void open()
    {
        try {
            sqLiteDatabase=mySql.getWritableDatabase();//set data in dataBase

        }catch (Exception e){
            Log.d("ERROR", e.getMessage());
        }
    }
    //close Connection
    public void close()
    {
        sqLiteDatabase.close();
    }

//insert Data in database
    public void createStudent(String name,String email,int number)
    {
        ContentValues list = new ContentValues(); //add key and value
        list.put(mySql.NAME,name); //key == nameColum and value == prametar in func
        list.put(mySql.Email,email);
        list.put(mySql.NUMBER,number);
        sqLiteDatabase.insert(mySql.STUDENT_TABLE,null,list);
    }
//get data usersId from database
    public Student getStudent(int id)
    {
        Student student = new Student();                                                              // ? == new String[]{id+""}
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+mySql.STUDENT_TABLE+" WHERE "+mySql.ID+" = ?",new String[]{id+""});
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            student.setId(cursor.getInt(0));
            student.setName(cursor.getString(1));
            student.setEmali(cursor.getString(2));
            student.setNumber(cursor.getInt(3));
            cursor.close();
        }
        return  student;
    }

//update Data in database
    public void updateStudent(int id,String name,String email,int number) {
        ContentValues list = new ContentValues(); //add key and value
        list.put(mySql.NAME, name); //key == nameColum and value == prametar in func
        list.put(mySql.Email, email);
        list.put(mySql.NUMBER, number);
        sqLiteDatabase.update(mySql.STUDENT_TABLE, list, mySql.ID + " = " + id, null);
    }
//Delete Data from database
    public void deletStudent(int id )
    {
        sqLiteDatabase.delete(mySql.STUDENT_TABLE,mySql.ID+" = "+id,null);
    }
//get data  from database
    public List<Student> getAllStudent()
    {
        List<Student> list = new ArrayList<Student>();

       Cursor cursor=sqLiteDatabase.query(mySql.STUDENT_TABLE,allColumes,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
         Student student = new Student();
         student.setId(cursor.getInt(0));
         student.setName(cursor.getString(1));
         student.setEmali(cursor.getString(2));
         student.setNumber(cursor.getInt(3));
         list.add(student);
         cursor.moveToNext();
        }

        cursor.close();//close connection
        return  list;
    }




}
