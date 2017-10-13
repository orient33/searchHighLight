package com.example.adm.demo;

import android.app.Activity;
import android.os.Bundle;

import com.example.adm.demo.search.SearchFragment;

public class DemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        getFragmentManager().beginTransaction().add(R.id.container, new SearchFragment()).commit();
    }
}
