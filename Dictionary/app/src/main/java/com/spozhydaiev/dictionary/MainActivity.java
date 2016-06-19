package com.spozhydaiev.dictionary;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.spozhydaiev.dictionary.db.DictionaryDBHandler;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener, TextWatcher {

    public static final int TRANSLATE_ACTIVITY = 1234;
    public static final String LOG_TAG = "myLog";

    private Toolbar toolbar;
    private AutoCompleteTextView actv;
    private Button translateBtn;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> actvAdapter;

    final ArrayList<String> latestWords = new ArrayList<>();
    final ArrayList<String> hintWords = new ArrayList<>();

    private DictionaryDBHandler dbHandler;

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

        dbHandler = new DictionaryDBHandler(this);
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

        ContentValues contentValues = new ContentValues();
        String word = actv.getText().toString();
        String translate = "translate";

        SQLiteDatabase db = dbHandler.getWritableDatabase();

        Log.d(LOG_TAG, "--Insert in table--");

        contentValues.put("searchWord", word);
        contentValues.put("translate", translate);
        // вставляем запись и получаем ее ID
        long rowID = db.insert("words", null, contentValues);
        Log.d(LOG_TAG, "row inserted, ID = " + rowID);

        latestWords.add(0, word);
        adapter.notifyDataSetChanged();

        if (!(hintWords.contains(word))) {
            hintWords.add(word);
            actvAdapter.notifyDataSetChanged();
        }

        Intent intent = new Intent(this, TranslateActivity.class);
        intent.putExtra(TranslateActivity.KEY_WORD, word);
        intent.putExtra(TranslateActivity.KEY_TRANSLATE, translate);
        startActivityForResult(intent, TRANSLATE_ACTIVITY);
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
