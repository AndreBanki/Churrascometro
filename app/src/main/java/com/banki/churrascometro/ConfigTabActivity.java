package com.banki.churrascometro;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TextView;

import com.banki.utils.EditInteger;

import java.util.ArrayList;
import java.util.Arrays;

public class ConfigTabActivity extends AppCompatActivity
{
    private Churrasco churrasco = new Churrasco();
    int tipo;
    SharedPreferences settings;

    private EditInteger[] edit = new EditInteger[Churrasco.nTiposIngredientes];

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_config);

        tipo = getIntent().getIntExtra("tipoConvidado",0);
        settings = getSharedPreferences(Churrasco.PREFS_NAME, 0);
    }

    @Override
    protected void onPause() {
        for (int i=Churrasco.CARNE; i<=Churrasco.REFRI; i++)
            churrasco.setParametroConsumo(tipo, i, edit[i].getIntValue());

        churrasco.saveParametrosConsumo(settings, tipo);
        super.onPause();
    }

    @Override
    protected void onResume() {
        churrasco.restoreParametrosConsumo(settings, tipo);
        inicializaControles();
        super.onResume();
    }

    private void inicializaControles() {
        edit[Churrasco.CARNE] = (EditInteger) findViewById(R.id.editCarne);
        edit[Churrasco.LINGUICA] = (EditInteger) findViewById(R.id.editLinguica);
        edit[Churrasco.CERVEJA] = (EditInteger) findViewById(R.id.editCerveja);
        edit[Churrasco.REFRI] = (EditInteger) findViewById(R.id.editRefri);

        for (int i= Churrasco.CARNE; i<= Churrasco.REFRI; i++)
            edit[i].setIntValue(churrasco.getParametroConsumo(tipo, i));
    }
}
