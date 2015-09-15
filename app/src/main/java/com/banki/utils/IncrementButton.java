package com.banki.utils;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.banki.utils.EditInteger;

public class IncrementButton extends Button {

    protected EditInteger associatedEdit = null;
    private boolean autoIncrement = false;
    private final long REPEAT_DELAY = 50;
    private Handler repeatUpdateHandler = new Handler();

    public IncrementButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (associatedEdit != null) {
                    changeValue();
                }
            }
        });

        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                autoIncrement = true;
                repeatUpdateHandler.post(new RepetitiveUpdater(IncrementButton.this));
                return false;
            }
        });

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP && autoIncrement) {
                    autoIncrement = false;
                }
                return false;
            }
        });
    }

    protected void changeValue() {
        associatedEdit.increment();
    }

    public void incrementAndRepeat() {
        if (autoIncrement) {
            changeValue();
            repeatUpdateHandler.postDelayed(new RepetitiveUpdater(this), REPEAT_DELAY);
        }
    }

    public void setAssociatedEdit(EditInteger associatedEdit) {
        this.associatedEdit = associatedEdit;
    }
}
