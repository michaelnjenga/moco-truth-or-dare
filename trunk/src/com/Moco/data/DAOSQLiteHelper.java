package com.Moco.data;

import static android.provider.BaseColumns._ID;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The Class StopsSQLiteHelper.
 */
public class DAOSQLiteHelper extends SQLiteOpenHelper {

    /** The Constant DATABASE_NAME. */
    private static final String DATABASE_NAME = "mocotod.db3";
    
    /** The Constant DATABASE_VERSION. */
    private static final int DATABASE_VERSION = 1;
    
    /** Spots table name */
    protected static final String ITEM_TABLE_NAME = "items";
    
    /** Spots columns name */
    //public static final String SPOTS_ID = "item_id";
    protected static final String ITEM_CONTENT = "item_content";
    protected static final String ITEM_TYPE = "item_type";
    protected static final String ITEM_DIRTY = "item_dirty";
    protected static final String ITEM_SOUND_ID = "item_sound_id";
    protected static final String ITEM_PICURE_ID = "item_picture_id";
    
    protected static final String[] ITEM_ALL_COLUMS = { _ID, ITEM_CONTENT, ITEM_TYPE, 
                                                        ITEM_DIRTY, ITEM_SOUND_ID, 
                                                        ITEM_PICURE_ID};

    /**
     * Instantiates a new stops database helper.
     * 
     * @param context
     *            the context
     */
    public DAOSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        
    }

    
    /*
     * (non-Javadoc)
     * 
     * @see
     * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
     * .SQLiteDatabase)
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        
        //Spots
        db.execSQL("CREATE TABLE  if not exists " + ITEM_TABLE_NAME + " (" +
                   _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                   ITEM_CONTENT + " TEXT NOT NULL, " +
                   ITEM_TYPE + " INT NOT NULL, " +
                   ITEM_DIRTY + " INT NOT NULL, " +
                   ITEM_SOUND_ID + " TEXT, " +
                   ITEM_PICURE_ID + " TEXT " +
                   ");");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
     * .SQLiteDatabase, int, int)
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE_NAME);
        onCreate(db);

    }

    protected void closeCursor(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }
    
}
