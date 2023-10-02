package com.azhar.kamusindojawa.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.azhar.kamusindojawa.model.ModelKamus;

import java.util.ArrayList;

/**
 * Created by Azhar Rivaldi on 31-08-2023
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * LinkedIn : https://www.linkedin.com/in/azhar-rivaldi
 */

public class DatabaseAccess {
    private final SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context){
        if(instance == null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open(){
        this.database = openHelper.getWritableDatabase();
    }

    public void close(){
        if(database != null){
            this.database.close();
        }
    }

    public ArrayList<ModelKamus> getKamusJawa(){
        ArrayList<ModelKamus> modelKamusArrayList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM KamusJawaIndonesia", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            ModelKamus modelKamus = new ModelKamus();
            modelKamus.setStrKata(cursor.getString(1));
            modelKamusArrayList.add(modelKamus);
            cursor.moveToNext();
        }
        cursor.close();
        return modelKamusArrayList;
    }

    public ArrayList<ModelKamus> getKamusIndonesia(){
        ArrayList<ModelKamus> modelKamusArrayList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM KamusJawaIndonesia ORDER BY indonesia ASC", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            ModelKamus modelKamus = new ModelKamus();
            modelKamus.setStrKata(cursor.getString(2));
            modelKamusArrayList.add(modelKamus);
            cursor.moveToNext();
        }
        cursor.close();
        return modelKamusArrayList;
    }

    public ArrayList<ModelKamus> getSearchJawa(String keyword){
        ArrayList<ModelKamus> modelKamusArrayList = new ArrayList<>();
        String queryString = "SELECT * FROM KamusJawaIndonesia WHERE jawa LIKE '%" + keyword +"%' ORDER BY jawa ASC";
        Cursor cursor = database.rawQuery(queryString, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            ModelKamus modelKamus = new ModelKamus();
            modelKamus.setStrKata(cursor.getString(1));
            modelKamusArrayList.add(modelKamus);
            cursor.moveToNext();
        }
        cursor.close();
        return modelKamusArrayList;
    }

    public ArrayList<ModelKamus> getSearchIndonesia(String keyword){
        ArrayList<ModelKamus> modelKamusArrayList = new ArrayList<>();
        String queryString = "SELECT * FROM KamusJawaIndonesia WHERE indonesia LIKE '%" + keyword +"%' ORDER BY indonesia ASC";
        Cursor cursor = database.rawQuery(queryString, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            ModelKamus modelKamus = new ModelKamus();
            modelKamus.setStrKata(cursor.getString(2));
            modelKamusArrayList.add(modelKamus);
            cursor.moveToNext();
        }
        cursor.close();
        return modelKamusArrayList;
    }

    public String getSelectedJawa(String kataJawa){
        String queryString = "SELECT * FROM KamusJawaIndonesia WHERE jawa='"+ kataJawa +"'";
        Cursor cursor = database.rawQuery(queryString, null);
        cursor.moveToFirst();
        String arti = cursor.getString(2);
        cursor.close();
        return arti;
    }

    public String getSelectedIndonesia(String kataIndonesia){
        String queryString = "SELECT * FROM KamusJawaIndonesia WHERE indonesia='"+ kataIndonesia +"'";
        Cursor cursor = database.rawQuery(queryString, null);
        cursor.moveToFirst();
        String arti = cursor.getString(1);
        cursor.close();
        return arti;
    }

}
