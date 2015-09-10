package com.banki.churrascometro;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

public class ConfigTabActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_config);

        TextView titulo = (TextView)findViewById(R.id.editTitulo);
        String tipo = getIntent().getStringExtra("tipo");
        titulo.append(tipo);
    }
}
