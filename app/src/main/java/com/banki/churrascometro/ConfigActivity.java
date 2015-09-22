package com.banki.churrascometro;

import android.app.ActionBar;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import java.util.ArrayList;
import java.util.Arrays;

public class ConfigActivity extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_config);

        criaHomeButton();
        setupTabs();
    }

    private void criaHomeButton() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setupTabs() {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
