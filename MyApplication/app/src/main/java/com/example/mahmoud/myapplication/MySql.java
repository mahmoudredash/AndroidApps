package com.example.mahmoud.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mahmoud on 25/12/17.
 */

public class MySql extends SQLiteOpenHelper {

    private static final String DB_NAME="mysqlDataBase";
    private static final int VERSION_NUMBER=1;

    public static final String STUDENT_TABLE="student";
    public static final String ID="id";
    public static final String NAME="name";
    public static final String Email="email";
    public static final String NUMBER  = "number";

    private static final String DB_CREATE = "create table  IF NOT EXISTS  "+STUDENT_TABLE+"( " +
            ID+" integer not null primary key autoincrement, " +
            NAME+" text not null," +
            Email+" text not null ," +
            NUMBER+" integer not null )";

    private static final String DB_Delet="DROP TABLE  IF  EXISTS"+STUDENT_TABLE;

    public MySql(Context context)
    {
        super(context,DB_NAME,null,VERSION_NUMBER);//create database in sqllite
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { //get execut  if object from class this
        sqLiteDatabase.execSQL(DB_CREATE);//create table

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {//if edit VERSION_NUMBER using this func
            sqLiteDatabase.execSQL(DB_Delet);//delete table
            onCreate(sqLiteDatabase);//create table

    }
}
