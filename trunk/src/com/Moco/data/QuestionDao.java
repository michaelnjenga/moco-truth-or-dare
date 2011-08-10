package com.Moco.data;

import static android.provider.BaseColumns._ID;

import com.Moco.MocoTruthOrDare;
import com.Moco.Utility.Logger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class QuestionDao extends DAOSQLiteHelper {

    private Context mContext;
    
    public QuestionDao(Context context) {
        super(context);
        this.mContext = context;
    }
    /**
     * Insert.
     *
     * @param Question the spot
     * @return the spot
     */
    public Question insert(Question question) {
        if (question.getItemId() == 0) {
            //SQLiteDatabase db = getWritableDatabase();
            return createNew(question);
        } else {
            String msg = " Failed to insert a existing Question " + question.getItemId() + ".";
            Log.d("QuestionDAL - ", msg);
            throw new RuntimeException(msg);
        }
    }

    /**
     * Gets the spots.
     *
     * @return the spots
     */
    public List<Question> getQuestions(int dirty) {
        List<Question> questions = new ArrayList<Question>();
        Cursor cursor = null;

        try {
            SQLiteDatabase db = getReadableDatabase();
            cursor = db.query(ITEM_TABLE_NAME, ITEM_ALL_COLUMS, "item_dirty = " + dirty, null, null, null, null);
            while (cursor.moveToNext()) {
                questions.add(createMeetingFromCursorData(cursor));
            }
            db.close();
        } finally {
            closeCursor(cursor);
        }

        Logger.d("MocoTruthOrDare - ", " Get " + questions.size() + " questions and dares from database.");
        
        return questions;
    }

    public List<Question> getQuestions(int type, int dirty) {
        List<Question> questions = new ArrayList<Question>();
        Cursor cursor = null;

        try {
            SQLiteDatabase db = getReadableDatabase();
  
            cursor = db.query(ITEM_TABLE_NAME, ITEM_ALL_COLUMS, "item_type = " + type +" AND item_dirty = " + dirty, null, null, null, null);
            while (cursor.moveToNext()) {
                questions.add(createMeetingFromCursorData(cursor));
            }
            db.close();
        } finally {
            closeCursor(cursor);
        }

        Logger.d("MocoTruthOrDare - ", " Get " + questions.size() + " questions and dares from database.");
        
        return questions;
    }
    
    /**
     * Delete all.
     */
    public void deleteAll() {
        Logger.d("MocoTruthOrDare - ", "Delete all questions.");
        SQLiteDatabase db = getWritableDatabase();
        db.delete(ITEM_TABLE_NAME, null, null);
    }

    /**
     * Delete.
     *
     * @param Question the spot
     */
    public void delete(Question question) {
        Logger.d("MocoTruthOrDare - ", "delete Question " + question.getItemContent() + ".");
        if (question.getItemId() != 0) {
            SQLiteDatabase db = getWritableDatabase();
            db.delete(ITEM_TABLE_NAME, _ID + " = ?", new String[]{Long.toString(question.getItemId())});
        }
    }

    /**
     * Creates the new spot.
     *
     * @param Question the spot
     * @return the spot
     */
    private Question createNew(Question question) {
        Logger.d("MocoTruthOrDare - ", "Insert new Question " + question.getItemContent() + ".");
        
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = createContentValues(question);
        int id = (int) db.insertOrThrow(ITEM_TABLE_NAME, null, values);
        db.close();
        return new Question(id, question);
    }

    /**
     * Creates the content values.
     *
     * @param Question the spot
     * @return the content values
     */
    private ContentValues createContentValues(Question question) {
        ContentValues values = new ContentValues();
            
        values.put(ITEM_CONTENT, question.getItemContent());
        values.put(ITEM_TYPE, question.getItemType());
        values.put(ITEM_DIRTY, question.getItemDirty());
        values.put(ITEM_SOUND_ID, question.getSoundId());
        values.put(ITEM_PICURE_ID, question.getPictureId());

        return values;
    }

    /**
     * Creates the meeting from cursor data.
     *
     * @param cursor the cursor
     * @return the spot
     */
    private Question createMeetingFromCursorData(Cursor cursor) {
        int id = cursor.getInt(0);
        String itemContent = cursor.getString(1);
        int itemType = cursor.getInt(2);
        int itemDirty = cursor.getInt(3);
        String itemSoundId = cursor.getString(4);
        String itemPictureId = cursor.getString(5);
        
        return new Question(id, itemContent, itemType, itemDirty, 
                            itemSoundId, itemPictureId);
    }
    

}
