package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import com.example.myapplication.com.example.myapplication.zrodo.AddressPickerDialog;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void click(){
        AddressPickerDialog dialog=new AddressPickerDialog(this);
        dialog.show();
    }
    
}
