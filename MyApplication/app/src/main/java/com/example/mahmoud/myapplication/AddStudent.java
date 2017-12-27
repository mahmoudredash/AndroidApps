package com.example.mahmoud.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudent extends AppCompatActivity {

    EditText name ,email,number;
    Button add;
    StudentDataSurce studentDataSurce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        name=(EditText)findViewById(R.id.tname);
        email=(EditText)findViewById(R.id.temail);
        number=(EditText)findViewById(R.id.tnumber);
        add=(Button)findViewById(R.id.add);

        studentDataSurce=new StudentDataSurce(getApplicationContext());


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //cheack for data
                if (!name.getText().toString().isEmpty()&& !email.getText().toString().isEmpty()&&!number.getText().toString().isEmpty())
                {
                    studentDataSurce.open();//open connection and add data and close connction show toast
                    studentDataSurce.createStudent(name.getText().toString(),email.getText().toString(),Integer.parseInt(number.getText().toString()));
                    studentDataSurce.close();
                    Toast.makeText(getApplicationContext(),"Down!",Toast.LENGTH_LONG).show();
                    name.setText("");email.setText("");number.setText("");
                }else{
                    Toast.makeText(getApplicationContext(),"Filed Please Entar Data",Toast.LENGTH_LONG).show();
                }
            }
        });//


    }
}
