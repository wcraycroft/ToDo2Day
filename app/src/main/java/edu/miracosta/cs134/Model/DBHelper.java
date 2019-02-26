package edu.miracosta.cs134.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TAG = DBHelper.class.getSimpleName();

    // STEP 1) Define all database info in constants
    public static final String DATABASE_NAME = "ToDo2Day";
    public static final String DATABASE_TABLE = "Tasks";
    public static final String FIELD_PRIMARY_KEY = "_id";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_IS_DONE = "is_done";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create all database tables (Tasks)
        // 1) Determine whether to read or write the database (CREATE = write)
        // Open a writable connection to the ToDo2Day database
        db = getWritableDatabase();

        // Execute the create table statement

        String sql = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY, %s TEXT, %s INTEGER)",
                DATABASE_TABLE, FIELD_PRIMARY_KEY, FIELD_DESCRIPTION, FIELD_IS_DONE);
        db.execSQL(sql);

        // Log SQL string
        Log.i(TAG, sql);

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
