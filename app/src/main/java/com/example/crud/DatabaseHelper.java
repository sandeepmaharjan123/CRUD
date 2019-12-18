package com.example.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.crud.models.Student;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mydb";
    private static final int DB_VERSION = 1;


    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Student.QUERY_CREATE_STUDENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertStudent(Student student) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            String insert_std = "INSERT INTO students (name, email, phone) VALUES ('" + student.getName() + "', '" + student.getEmail() + "', '" + student.getPhone() + "')";
            db.execSQL(insert_std);
            return true;
        } catch (Exception e) {
            Log.d("create_error", e.toString());
            return false;
        }
    }

    public List<Student> displayStudents() {
        List<Student> studentList = new ArrayList<>();
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM students", null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    studentList.add(new Student(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
                }
                cursor.close();
            }
            return studentList;
        } catch (Exception e) {
            Log.d("selection_error", e.toString());
            return studentList;
        }
    }

    public void deleteStudent(int id) {
        try {
            SQLiteDatabase db = getReadableDatabase();
            db.delete("students", "id=?", new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d("delete_error", e.toString());
        }
    }

    public boolean updateStudentDetails(int id, String name, String email, String phone) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("name", name);
            cv.put("email", email);
            cv.put("phone", phone);

            db.update("students", cv, "id=?", new String[]{String.valueOf(id)});
            return true;
        } catch (Exception e) {
            Log.d("update_error", e.toString());
            return false;
        }

    }
}
