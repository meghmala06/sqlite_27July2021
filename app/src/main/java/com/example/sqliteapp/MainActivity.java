package com.example.sqliteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {

    Button mbtnInsert,mbtnupdate,mbtnDelete,mbtnView;
    EditText medtName,medtDob,medtContact;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbtnInsert=findViewById(R.id.btnInsert);

        medtName=findViewById(R.id.edtName);
        medtContact=findViewById(R.id.edtContact);
        medtDob=findViewById(R.id.edtDob);
        mbtnView=findViewById(R.id.btnView);
        DatabaseHandler db=new DatabaseHandler(this);

        mbtnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametxt=medtName.getText().toString();
                String dobtxt=medtDob.getText().toString();
                String contacttxt=medtContact.getText().toString();

                Boolean insertresult=db.insertdata(nametxt,contacttxt,dobtxt);
                if(insertresult==true)
                {
                    Toast.makeText(MainActivity.this,"Inserted Successfully",Toast.LENGTH_LONG).show();
                }

            }
        });
        mbtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=db.getdata();
                if(res.getCount()==0)
                {
                    Toast.makeText(getApplicationContext(),"noentry exists",Toast.LENGTH_LONG).show();
                    return;

                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext())
                {
                    buffer.append("Name:"+res.getString(0)+"\n");
                    buffer.append("Contact:"+res.getString(1)+"\n");
                    buffer.append("Date of Birth:"+res.getString(2)+"\n");

                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("User entries");
                    builder.setMessage(buffer.toString());
                    builder.show();

                     /*

                    String contact=res.getString(1).toString();
                    Log.d("Contact No....",contact);
                    */

                }

            }
        });



    }
}