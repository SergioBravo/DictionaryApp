package com.spozhydaiev.dictionary;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class MainActivity extends Activity {

    private Toolbar toolbar;
    private AutoCompleteTextView actv;
    private Button translateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initAutoCompleteTextView();
        initTranslateButton();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
    }

    private void initAutoCompleteTextView() {
        actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
    }

    private void initTranslateButton() {
        translateBtn = (Button) findViewById(R.id.translateButton);
    }
}
