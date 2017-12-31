package com.example.mahmoud.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class DesplayAll extends AppCompatActivity {

    ListView listView;
    StudentDataSurce studentDataSurce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desplay_all);
        //get suorce listview
        listView =(ListView)findViewById(R.id.lstudentdata);
        //create object from database
        studentDataSurce =new StudentDataSurce(getApplicationContext());
        studentDataSurce.open(); //open connection
        final List<Student> students=studentDataSurce.getAllStudent(); //get data and save in lis
        studentDataSurce.close();//close connection
        //get name from list
        String[] names = new String[students.size()];
        for (int i =0;i<students.size();i++){
            names[i]=students.get(i).getName(); //add name in array name
        }
        //get email from list
        String[] email = new String[students.size()];
        for (int i =0;i<students.size();i++){
            email[i]=students.get(i).getEmali();//add email in array email
        }
        //create adapter and add array name
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(DesplayAll.this,android.R.layout.simple_list_item_1,android.R.id.text1,names);
        listView.setAdapter(adapter); //add adapter in listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int postion, long l) {
                new AlertDialog.Builder(DesplayAll.this)
                        .setTitle("Select Your Optiones")
                        .setMessage("Delete OR Update")
                        .setPositiveButton("Edite", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(getApplicationContext(),EditStudent.class);
                                intent.putExtra("id",students.get(postion).getId());
                                startActivity(intent);
                            }
                        })
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                studentDataSurce.open();
                                studentDataSurce.deletStudent(students.get(postion).getId());
                                studentDataSurce.close();
                                Toast.makeText(getApplicationContext(),"Doun !",Toast.LENGTH_LONG).show();
                                Intent intent2 = new Intent(getApplicationContext(),DesplayAll.class);
                                startActivity(intent2);
                            }
                        }) //problem end setPositiveButton execut  ..
                        .show();
            }
        });

    }
}
