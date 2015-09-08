package com.banki.churrascometro;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class ConfigActivity extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        TabHost tabHost = getTabHost();

        TabSpec tab1 = tabHost.newTabSpec("Homens");
        tab1.setIndicator("Homens");
        Intent intent1 = new Intent(this, ConfigTabActivity.class);
        tab1.setContent(intent1);

        TabSpec tab2 = tabHost.newTabSpec("Mulheres");
        tab2.setIndicator("Mulheres");
        Intent intent2 = new Intent(this, ConfigTabActivity.class);
        tab2.setContent(intent2);

        TabSpec tab3 = tabHost.newTabSpec("Crianças");
        tab3.setIndicator("Crianças");
        Intent intent3 = new Intent(this, ConfigTabActivity.class);
        tab3.setContent(intent3);

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
    }
}
