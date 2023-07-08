package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;
public class signup extends AppCompatActivity {
    Button b3;
    EditText et1,et;
    String a;
    int counter=0;

    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign);
        b3=findViewById(R.id.b3);
        et1=findViewById(R.id.et1);
        et=findViewById(R.id.et);
        db = openOrCreateDatabase("userDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS user(username varchar PRIMARY kEY,password varchar)");
        b3.setOnClickListener(v -> {
            if (et1.getText().toString().length() == 0 || et.getText().toString().length()
                    == 0) {
                Toast.makeText(getApplicationContext(), "Please enter details",Toast.LENGTH_LONG).show();
                return;

            }
            if(et1.getText().toString().length()<8){
                Toast.makeText(getApplicationContext(), "Password must contain 8 characters",Toast.LENGTH_LONG).show();
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM user WHERE username='"+et.getText()+"'", null);
            if(c.moveToFirst()){
                a=c.getString(0);
                counter=1;
            }
            if(counter==0) {
                db.execSQL("INSERT INTO user VALUES('" + et.getText() + "','" + et1.getText() + "');");
                showMessage("Message", "User Created Successfully");
                clearText();
            }else{
                showMessage("error","User already created");
                counter=0;
            }
        });

    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder b=new AlertDialog.Builder(this);
        b.setCancelable(true);
        b.setTitle(title);
        b.setMessage(message);
        b.show();
    }
    private void clearText()
    {
        et.setText("");
        et1.setText("");

    }


}