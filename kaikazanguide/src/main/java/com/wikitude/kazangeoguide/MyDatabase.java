package com.wikitude.kazangeoguide;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
//Создание класса для быстрого доступа к БД из папки android/assets
public class MyDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "Buildings.db";
    private static final int DATABASE_VERSION = 1;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


}