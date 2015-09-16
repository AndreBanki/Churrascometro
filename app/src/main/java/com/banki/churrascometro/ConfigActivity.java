package com.banki.churrascometro;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;

import java.util.ArrayList;
import java.util.Arrays;

public class ConfigActivity extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        TabHost tabHost = getTabHost();
        ArrayList<String> tipoConvidado = new ArrayList<String>(
                Arrays.asList("Homens", "Mulheres", "Crianças"));

        for (int tipo = 0; tipo < Churrasco.nTiposConvidados; tipo++) {
            TabSpec tab = tabHost.newTabSpec(tipoConvidado.get(tipo));
            tab.setIndicator(tipoConvidado.get(tipo));

            Intent intent = new Intent(this, ConfigTabActivity.class);
            intent.putExtra("tipoConvidado", tipo);

            tab.setContent(intent);
            tabHost.addTab(tab);
        }
    }
}
