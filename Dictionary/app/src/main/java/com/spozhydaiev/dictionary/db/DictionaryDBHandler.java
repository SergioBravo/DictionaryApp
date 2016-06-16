package com.spozhydaiev.dictionary.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.spozhydaiev.dictionary.WordRecord;

import java.util.ArrayList;
import java.util.List;

public class DictionaryDBHandler extends SQLiteOpenHelper implements IDictionaryDB{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dictionary";
    private static final String TABLE_WORDS = "words";
    private static final String KEY_ID = "id";
    private static final String KEY_WORD = "searchWord";
    private static final String KEY_TRANSLATE = "translate";

    public DictionaryDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WORDS_TABLE = "CREATE TABLE " + TABLE_WORDS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_WORD + " TEXT,"
                + KEY_TRANSLATE + " TEXT" + ")";
        db.execSQL(CREATE_WORDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);

        onCreate(db);
    }

    @Override
    public void addWord(WordRecord wordRecord) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_WORD, wordRecord.getSearchWord());
        values.put(KEY_TRANSLATE, wordRecord.getTranslate());

        db.insert(TABLE_WORDS, null, values);
        db.close();
    }

    @Override
    public WordRecord getWordRecord(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_WORDS, new String[]{KEY_ID,
                        KEY_WORD, KEY_TRANSLATE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        WordRecord wordRecord = new WordRecord(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        return wordRecord;
    }

    @Override
    public List<WordRecord> getAllWords() {
        List<WordRecord> wordRecordList = new ArrayList<WordRecord>();
        String selectQuery = "SELECT  * FROM " + TABLE_WORDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                WordRecord wordRecord = new WordRecord();
                wordRecord.setID(Integer.parseInt(cursor.getString(0)));
                wordRecord.setSearchWord(cursor.getString(1));
                wordRecord.setTranslate(cursor.getString(2));
                wordRecordList.add(wordRecord);
            } while (cursor.moveToNext());
        }

        return wordRecordList;
    }
}
