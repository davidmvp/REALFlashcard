package com.example.davidmvp23.flashcard;

/**
 * Created by davidmvp23 on 2/28/15.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;


public class FlashcardDBAdapter {

    private SQLiteDatabase db;
    private FlashcardDBHelper dbHelper;
    private Context context = null;

    private static final String DB_NAME = "flash.db";
    private static final int DB_VERSION = 3;  // when you add or delete fields, you must update the version number!

    private static final String Card_TABLE = "flashcards";
    public static final String Card_ID = "card_id";   // column 0
    public static final String Card_subject = "card_subject";
    public static final String Card_question = "card_question";
    public static final String Card_answer = "card_answer";
    public static final String[] Card_COLS = {Card_ID, Card_subject, Card_question, Card_answer};

    public FlashcardDBAdapter(Context ctx) {
        context = ctx;
        dbHelper = new FlashcardDBHelper(context, DB_NAME, null, DB_VERSION);
    }

    public void open() throws SQLiteException {
        try {

            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbHelper.getReadableDatabase();
        }
    }

    public void close() {
        db.close();
    }


    private static class FlashcardDBHelper extends SQLiteOpenHelper {

        // SQL statement to create a new database table
        private static final String DB_CREATE = "CREATE TABLE " + Card_TABLE
                + " (" + Card_ID + " INTEGER PRIMARY KEY AUTOINCREMENT);";

        public FlashcardDBHelper(Context context, String name, CursorFactory fct, int version) {
            super(context, name, fct, version);
        }

        @Override
        public void onCreate(SQLiteDatabase adb) {
            adb.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase adb, int oldVersion, int newVersion) {
            Log.w("GPAdb", "upgrading from version " + oldVersion + " to "
                    + newVersion + ", destroying old data");
            // drop old table if it exists, create new one
            // better to migrate existing data into new table
            adb.execSQL("DROP TABLE IF EXISTS " );
            onCreate(adb);
        }
    } // GPAdbHelper class



}
