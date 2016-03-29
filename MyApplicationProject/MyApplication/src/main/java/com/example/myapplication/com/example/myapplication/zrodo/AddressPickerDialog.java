package com.example.myapplication.com.example.myapplication.zrodo;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.example.myapplication.R;

/**
 * Created by grandry.xu on 15-5-18.
 */
public class AddressPickerDialog extends Dialog implements DialogInterface.OnCancelListener{
    public AddressPickerDialog(Context context) {
       this(context,0);
    }

    public AddressPickerDialog(Context context, int theme) {
        this(context, true, null);
    }

    protected AddressPickerDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        setContentView(R.layout.picker_dialog);


    }

    @Override
    public void onCancel(DialogInterface dialog) {
        this.dismiss();
    }
}
