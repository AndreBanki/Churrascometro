package com.banki.churrascometro;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IncrementButton extends Button {

    protected EditInteger associatedEdit = null;

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
    }

    protected void changeValue() {
        associatedEdit.increment();
    }

    public void setAssociatedEdit(EditInteger associatedEdit) {
        this.associatedEdit = associatedEdit;
    }
}
