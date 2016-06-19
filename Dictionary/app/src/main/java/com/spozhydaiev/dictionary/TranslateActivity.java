package com.spozhydaiev.dictionary;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TranslateActivity extends Activity implements View.OnClickListener{

    public static final String KEY_WORD = "KEY_WORD";
    public static final String KEY_TRANSLATE = "KEY_TRANSLATE";

    private TextView textView;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        textView = (TextView) findViewById(R.id.translateTextView);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(this);

        if (bundle != null) {
            String word = bundle.getString(KEY_WORD);
            String translate = bundle.getString(KEY_TRANSLATE);
            textView.setText(word + " - " + translate);
        }
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
