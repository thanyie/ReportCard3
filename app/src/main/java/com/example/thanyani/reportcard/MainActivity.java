package com.example.thanyani.reportcard;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   // public static final String STUDENT_NO = "studenrNo";
    public static final String UPDATE = "update";
    private  StudentDatabase sd = new StudentDatabase(this);
    private int position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // final StudentDatabase sd = new StudentDatabase(this);
        final ArrayList<String> students = sd.getAllStudents();
        ListView lv= (ListView) findViewById(R.id.list);
        final Student student = new Student();




        //adapter = new CustomArrayAdapter(this,android.R.layout.simple_list_item_1,students);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, students);
        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //  student = sd.deleteStudent();
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Student Report");
                dialog.setMessage("Are you sure you want to ?");
                dialog.setNegativeButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(MainActivity.this, StudentDetails.class);
                        startActivity(intent);
                        // sd=new StudentDatabase();
                        // sd.deleteStudent(student);
                        dialog.dismiss();
                    }
                });
                dialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent =new Intent(MainActivity.this,StudentDetails.class);
                        //intent.putExtra(STUDENT_NO, student.getStudentNo());
                        //  intent.putExtra(UPDATE, true);
                        startActivity(intent);
                    }
                });

                dialog.show();
            }
        });
    }

    public void addStudent(View view){

        Intent intent = new Intent(this, StudentDetails.class);
        startActivity(intent);
    }

    public void delete(View view){

        Intent intent = new Intent(this, StudentDetails.class);
        startActivity(intent);
    }


    public void update(View view){
        Intent intent = new Intent(this, StudentDetails.class);
        startActivity(intent);

    }
}




