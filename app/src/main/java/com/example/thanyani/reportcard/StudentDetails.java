package com.example.thanyani.reportcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class StudentDetails extends AppCompatActivity {

    private boolean update;
    private StudentDatabase sd;
    private int id;

    static private EditText StdNo;
    private EditText Name;
    private EditText Mrk1;
    private EditText Mrk2;
    private EditText Mrk3;
    Spinner gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        sd = new StudentDatabase(this);
        StdNo = (EditText) findViewById(R.id.no);
        Name = (EditText) findViewById(R.id.name);
        Mrk1 = (EditText) findViewById(R.id.mark1);
        Mrk2 = (EditText) findViewById(R.id.mark2);
        Mrk3 = (EditText) findViewById(R.id.mark3);
        gender = (Spinner) findViewById(R.id.gender);



    }

    public void addOnDB(View view) {


        long i = 0;

        String studentNu = StdNo.getText().toString();
        String name = Name.getText().toString();
        String mark1 = Mrk1.getText().toString();
        String mark2 = Mrk2.getText().toString();
        String mark3 = Mrk3.getText().toString();
        String myGen = gender.getSelectedItem().toString();


        Student student = new Student(Integer.parseInt(studentNu), name, myGen, Integer.parseInt(mark1), Integer.parseInt(mark2), Integer.parseInt(mark3));


        sd.addStudent(student);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }

    public void delete(View view) {
        EditText name = (EditText) findViewById(R.id.name);
        String names = name.getText().toString();
        sd.deleteStudent(names);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void update(View view) {

        String studentNu = StdNo.getText().toString();
        String name = Name.getText().toString();
        String mark1 = Mrk1.getText().toString();
        String mark2 = Mrk2.getText().toString();
        String mark3 = Mrk3.getText().toString();
        String myGen = gender.getSelectedItem().toString();

        Student student = new Student(Integer.parseInt(studentNu), name, myGen, Integer.parseInt(mark1), Integer.parseInt(mark2), Integer.parseInt(mark3));


        if (update) {
            student.setStudentNo(Integer.parseInt(studentNu));
            student.setName(name);
            student.setGender(myGen);
            student.setMark1(Integer.parseInt(mark1));
            student.setMark2(Integer.parseInt(mark2));
            student.setMark3(Integer.parseInt(mark3));
            sd.updateShow(student);
        } else {
            sd.updateShow(student);
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
