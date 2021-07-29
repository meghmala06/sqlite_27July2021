package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler(@Nullable Context context)
            {
        super(context,"StudentDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table studentdetails (name TEXT primary key,contact TEXT ,dob TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists studentdetails");

    }

    public boolean insertdata(String name,String contact,String dob)
    {

        SQLiteDatabase sqldb=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("contact",contact);
        cv.put("dob",dob);
        long result=sqldb.insert("studentdetails",null,cv);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor getdata() {

            //getWritableDatabase()...Create and/or open a database that will be used for reading and writing.
        SQLiteDatabase db=this.getWritableDatabase();

        // rawQuery(String sql,String[] selectionArgs)
        //Runs the provided SQL and returns a Cursor over the result set.
        Cursor cursor=db.rawQuery("Select * from studentdetails ",null);
        return  cursor;


    }

    public boolean updateData(String name ,String contact,String dob)
    {
        //SQLiteDatabase Exposes methods to manage a SQLite database.
        //SQLiteDatabase has methods to create, delete, execute SQL commands,
        // and perform other common database management tasks.
        SQLiteDatabase db=this.getWritableDatabase();
        //ContentValues class is used to store a set of values that the ContentResolver can process.
        ContentValues content=new ContentValues();
        content.put("contact",contact);
        content.put("dob",dob);

        Cursor cursor=db.rawQuery("Select * from studentdetails where name=?",new String[]{name});
        if(cursor.getCount()>0) {
            long result = db.update("studentdetails", content, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else
            {
            return false;
        }



    }

    public boolean delteData(String name )
    {
        //SQLiteDatabase Exposes methods to manage a SQLite database.
        //SQLiteDatabase has methods to create, delete, execute SQL commands,
        // and perform other common database management tasks.
        SQLiteDatabase db=this.getWritableDatabase();
        //ContentValues class is used to store a set of values that the ContentResolver can process.
        ContentValues content=new ContentValues();


        Cursor cursor=db.rawQuery("Select * from studentdetails where name=?",new String[]{name});
        if(cursor.getCount()>0) {
            long result = db.delete("studentdetails", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else
        {
            return false;
        }



    }


}
