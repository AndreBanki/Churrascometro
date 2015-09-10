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
        ArrayList<String> tipos = new ArrayList<String>(
                Arrays.asList("Homens", "Mulheres", "Crianças"));
        int nTabs = tipos.size();

        for (int i = 0; i < nTabs; i++) {
            TabSpec tab = tabHost.newTabSpec(tipos.get(i));
            tab.setIndicator(tipos.get(i));
            Intent intent = new Intent(this, ConfigTabActivity.class);
            intent.putExtra("tipo",tipos.get(i));
            tab.setContent(intent);
            tabHost.addTab(tab);
        }
    }
}
