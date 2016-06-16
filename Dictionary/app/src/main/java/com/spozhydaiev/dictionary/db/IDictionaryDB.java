package com.spozhydaiev.dictionary.db;

import com.spozhydaiev.dictionary.WordRecord;

import java.util.List;

public interface IDictionaryDB {
    public void addWord(WordRecord wordRecord);
    public WordRecord getWordRecord(int id);
    public List<WordRecord> getAllWords();
}
