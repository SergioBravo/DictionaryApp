package com.spozhydaiev.dictionary;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener, TextWatcher {

    private Toolbar toolbar;
    private AutoCompleteTextView actv;
    private Button translateBtn;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> actvAdapter;

    final ArrayList<String> latestWords = new ArrayList<>();
    final ArrayList<String> hintWords = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initAutoCompleteTextView();
        initTranslateButton();

        translateBtn.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, R.layout.list_item, latestWords);
        listView.setAdapter(adapter);

        hintWords.add("понедельник");
        hintWords.add("вторник");
        hintWords.add("среда");
        hintWords.add("четверг");
        hintWords.add("пятница");
        hintWords.add("суббота");
        hintWords.add("воскресенье");
        hintWords.add("сурок");
        hintWords.add("сумка");

        actvAdapter = new ArrayAdapter(
                this, android.R.layout.simple_dropdown_item_1line, hintWords);
        actv.setAdapter(actvAdapter);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
    }

    private void initAutoCompleteTextView() {
        actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        actv.addTextChangedListener(this);
    }

    private void initTranslateButton() {
        translateBtn = (Button) findViewById(R.id.translateButton);
    }

    @Override
    public void onClick(View v) {
        String notFind = actv.getText().toString();
        latestWords.add(0, notFind);
        adapter.notifyDataSetChanged();

        if (!(hintWords.contains(notFind))) {
            hintWords.add(notFind);
            actvAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
