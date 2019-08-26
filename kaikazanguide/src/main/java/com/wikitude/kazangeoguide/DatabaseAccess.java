package com.wikitude.kazangeoguide;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAccess{

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance ;
    Cursor c = null;
    //создание конструктора для загрузки данных класса-загрузчика БД
    private DatabaseAccess (Context context){
        this.openHelper=new MyDatabase(context);
    }
    //создание метода для проверки состояния БД
    public static DatabaseAccess getInstance(Context context){
        if (instance==null){
            instance=new DatabaseAccess(context);
        }
        return instance;
    }
    //Доступ к БДоткрыт
    public void open(){
        this.db=openHelper.getWritableDatabase();

    }
    //Доступ к БД закрыт
    public void close(){

        if (db!=null){
            this.db.close();
        }
    }
    //Метод отправляющий БД запрос в формате SQL, возвращает значение в виде строки
    public String getBuildingInfo(String building_name){
        c= db.rawQuery("Select building_information from buildings where building_name= '"+building_name+"'",new String[]{});
        StringBuffer buffer =new StringBuffer();
        while(c.moveToNext()){
            String building_information = c.getString(0);
            buffer.append(""+building_information);
        }

        return buffer.toString();
    }

}
