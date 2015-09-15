package com.banki.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

public class EditInteger extends EditText{
    public EditInteger(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public int getIntValue() {
        int value = 0;
        try {
            value = Integer.parseInt(getText().toString());
        }
        catch (NumberFormatException e) {
            Log.e("Churrascômetro", Log.getStackTraceString(e));
        }
        return value;
    }

    public void setIntValue(int value) {
        if (value < 0)
            value = 0;
        setText(String.valueOf(value));
    }

    public void increment() {
        int value = getIntValue();
        setIntValue(++value);
    }

    public void decrement() {
        int value = getIntValue();
        setIntValue(--value);
    }
}
