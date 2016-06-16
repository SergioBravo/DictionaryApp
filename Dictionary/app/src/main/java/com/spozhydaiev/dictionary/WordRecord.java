package com.spozhydaiev.dictionary;

public class WordRecord {
    private int id;
    private String searchWord;
    private String translate;

    public WordRecord() {

    }

    public WordRecord(int id, String searchWord, String translate) {
        this.id = id;
        this.searchWord = searchWord;
        this.translate = translate;
    }

    public WordRecord(String searchWord, String translate) {
        this.searchWord = searchWord;
        this.translate = translate;
    }

    public int getID() {
        return id;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public String getTranslate() {
        return translate;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }
}
