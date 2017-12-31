package com.example.mahmoud.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditStudent extends AppCompatActivity {
    int id;
    EditText name ,email,number;
    Button add;
    StudentDataSurce studentDataSurce;
    Student student1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        final Intent intent = getIntent();
        id = intent.getIntExtra("id",0);

        name=(EditText)findViewById(R.id.tname);
        email=(EditText)findViewById(R.id.temail);
        number=(EditText)findViewById(R.id.tnumber);
        add=(Button)findViewById(R.id.add);
        add.setText("Edit");

        studentDataSurce=new StudentDataSurce(getApplicationContext());

        studentDataSurce.open();
        Student student = studentDataSurce.getStudent(id);
        studentDataSurce.close();

        name.setText(student.getName());
        email.setText(student.getEmali());
        number.setText(student.getNumber()+"");


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //cheack for data
                if (!name.getText().toString().isEmpty()&& !email.getText().toString().isEmpty()&&!number.getText().toString().isEmpty())
                {
                    if (student1.rgxData(name.getText().toString(),"txt")
                            && student1.rgxData(number.getText().toString(),"number")&&
                            student1.rgxData(email.getText().toString(),"email")) {
                        studentDataSurce.open();//open connection and add data and close connction show toast
                        studentDataSurce.updateStudent(id, name.getText().toString(), email.getText().toString(), Integer.parseInt(number.getText().toString()));
                        studentDataSurce.close();
                        Toast.makeText(getApplicationContext(), "Down!", Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent(getApplicationContext(), DesplayAll.class);
                        startActivity(intent1);
                    }else {
                        Toast.makeText(getApplicationContext()," Error In Data",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Filed Please Entar Data",Toast.LENGTH_LONG).show();
                }
            }
        });//


    }
}
