package com.example.thanyani.reportcard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by THANYANI on 2017/07/25.
 */

public class StudentDatabase extends SQLiteOpenHelper {


    private static final String TABLE_STUDENT = "student";
    private final String COLUMN_ID ="studentId";
    private final String COLUMN_STUDENT_NO = "studentNo";
    private final String COLUMN_NAME = "name";
    private final String COLUMN_GENDER = "gender";
    private final String COLUMN_MARK1 = "mark1";
    private final String COLUMN_MARK2 = "mark2";
    private final String COLUMN_MARK3 = "mark3";
    private final static String DATABASE_NAME = "student.db";
    private final static int DATABASE_VERSION = 2;
    private final String DATABASE_CREATE = "create table " + TABLE_STUDENT + "(" +COLUMN_ID + " integer primary key autoincrement, "+ COLUMN_STUDENT_NO + " text not null, " + COLUMN_NAME + " text not null, " + COLUMN_GENDER + " text not null, " + COLUMN_MARK1 + " text not null, " + COLUMN_MARK2 + " text not null, " + COLUMN_MARK3 + " text not null);";


    public StudentDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);

    }

    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //id, std_num, name, gender, mark,mark2,mark3
        contentValues.put(COLUMN_STUDENT_NO, student.getStudentNo());
        contentValues.put(COLUMN_NAME, student.getName());
        contentValues.put(COLUMN_GENDER, student.getGender());
        contentValues.put(COLUMN_MARK1, student.getMark1());
        contentValues.put(COLUMN_MARK2, student.getMark2());
        contentValues.put(COLUMN_MARK3, student.getMark3());

        db.insert(TABLE_STUDENT, null, contentValues);
    }

    public ArrayList<String> getAllStudents() {
        ArrayList<String> students = new ArrayList<>();
        //select all query
        String selectQuery = "SELECT * FROM " + TABLE_STUDENT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String stdNo = cursor.getString(1);
                String name = cursor.getString(2);
                String gender = cursor.getString(3);
                int mrk1 = cursor.getInt(4);
                int mrk2 = cursor.getInt(5);
                int mrk3 = cursor.getInt(6);

                String display = id + "Student No: " + stdNo + " " + "Name: " + " " + name + " " + "Gender: " + " " + gender + " " + "Mark1: " + " " + mrk1 + " " + "Mark2: " + " " + mrk2 + " " + "Mark3: " + " " + mrk3;
                students.add(display);
                System.out.println("STudent Number "+cursor.getString(1));

            }
            while (cursor.moveToNext());
        }
        return students;

    }

    public int deleteStudent(String x) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs = {x};
        int count = db.delete(this.TABLE_STUDENT,this. COLUMN_NAME + " =?",whereArgs);
        db .close();
        return count;

    }

    public int updateShow(Student student) {

        String args[] = {String.valueOf(student.getName())};
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_STUDENT_NO, student.getStudentNo());
        cv.put(COLUMN_NAME, student.getName());
        cv.put(COLUMN_GENDER, student.getGender());
        cv.put(COLUMN_MARK1, student.getMark1());
        cv.put(COLUMN_MARK2, student.getMark2());
        cv.put(COLUMN_MARK3, student.getMark3());
        return db.update(TABLE_STUDENT, cv, COLUMN_NAME + " = ?", args);

    }

    public Student getAStudent(int id) {
        Student s = new Student();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_STUDENT + " where " + COLUMN_ID + " = ? ";
        String args[] = {String.valueOf(id)};
        Cursor c = db.rawQuery(selectQuery, args);
        if (c.moveToFirst()) {

            s.setStudentNo(c.getInt(c.getColumnIndex(COLUMN_STUDENT_NO)));
            s.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
            s.setStudentNo(c.getInt(c.getColumnIndex(COLUMN_MARK3)));
            s.setMark1(c.getInt(c.getColumnIndex(COLUMN_MARK1)));
            s.setMark2(c.getInt(c.getColumnIndex(COLUMN_MARK2)));
            s.setMark3(c.getInt(c.getColumnIndex(COLUMN_MARK3)));
            s.setGender(c.getString(c.getColumnIndex(COLUMN_GENDER)));



        }
        return s;
    }



}
