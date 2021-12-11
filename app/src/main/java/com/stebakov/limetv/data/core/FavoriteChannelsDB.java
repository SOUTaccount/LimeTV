package com.stebakov.limetv.data.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class FavoriteChannelsDB extends SQLiteOpenHelper {
    private static final String DB_NAME = "favorite";
    private static final int DB_VER = 1;
    private static final String DB_TABLE = "channels";
    private static final String COLUMN_NAME = "names";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_FAVORITE = "fvr";
    private static final String KEY_ID = "_id";

    public FavoriteChannelsDB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE " + DB_TABLE + "(" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT," + COLUMN_IMAGE +
                " TEXT," + " TEXT," + COLUMN_FAVORITE + " TEXT" + ");");
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            String query = String.format("DELETE TABLE IF EXISTS %s)", DB_TABLE);
            db.execSQL(query);
            onCreate(db);
        }
    }

    public void insertData(String name, String image, String favorite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_IMAGE, image);
        values.put(COLUMN_FAVORITE, favorite);
        db.insertWithOnConflict(DB_TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }


    public void deleteData(String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE, COLUMN_NAME + "= ?", new String[]{task});
        db.close();
    }

    public void upgradeFavorite(String name, String favorite){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FAVORITE,favorite);
        String selection = COLUMN_NAME + "=?";
        String [] selectionArgs = {name};
        db.update(DB_TABLE,contentValues,selection,selectionArgs);
    }

    public ArrayList<String> getNames(){
        ArrayList<String> alNames=new ArrayList<>();
        String selection = COLUMN_FAVORITE + "= ?";
        String [] selectionArgs = new String [] {"1"};
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(DB_TABLE,new String[]{COLUMN_NAME},selection,selectionArgs,null,null,null);
        while (cursor.moveToNext()){
            int index=cursor.getColumnIndex(COLUMN_NAME);
            alNames.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return alNames;
    }

    public ArrayList<String> getImg(){
        ArrayList<String> alNames=new ArrayList<>();
        String selection = COLUMN_FAVORITE + "= ?";
        String [] selectionArgs = new String [] {"1"};
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(DB_TABLE,new String[]{COLUMN_IMAGE},selection,selectionArgs,null,null,null);
        while (cursor.moveToNext()){
            int index=cursor.getColumnIndex(COLUMN_IMAGE);
            alNames.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return alNames;
    }

    public String getFvr(String name){
        String fvr = "";
        String selection = COLUMN_NAME + "= ?";
        String [] selectionArgs = new String [] {name};
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(DB_TABLE,new String[]{COLUMN_FAVORITE},selection,selectionArgs,null,null,null);
        while (cursor.moveToNext()){
            int index=cursor.getColumnIndex(COLUMN_FAVORITE);
            fvr = cursor.getString(index);
        }
        cursor.close();
        db.close();
        return fvr;
    }

    public int checkTable(){
        int checkable;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM channels", null);
        cursor.moveToFirst();
        checkable = cursor.getInt(0);
        cursor.close();
        return checkable;
    }
}
