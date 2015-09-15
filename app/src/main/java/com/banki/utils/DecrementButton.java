package com.banki.utils;

import android.content.Context;
import android.util.AttributeSet;

import com.banki.utils.IncrementButton;

public class DecrementButton extends IncrementButton {
    public DecrementButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void changeValue() {
        associatedEdit.decrement();
    }
}
