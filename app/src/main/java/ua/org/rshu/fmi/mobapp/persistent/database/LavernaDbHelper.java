package ua.org.rshu.fmi.mobapp.persistent.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

class LavernaDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Laverna.db";

    private Context mContext;

    LavernaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onConfigure(@NonNull SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL(LavernaContract.NotesTable.SQL_CREATE_NOTES_TABLE);
        db.execSQL(LavernaContract.TagsTable.SQL_CREATE_TAGS_TABLE);
        db.execSQL(LavernaContract.NotesTagsTable.SQL_CREATE_NOTES_TAGS_TABLE);
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(LavernaContract.NotesTable.SQL_DELETE_NOTES_TABLE);
        db.execSQL(LavernaContract.TagsTable.SQL_DELETE_TAGS_TABLE);
        db.execSQL(LavernaContract.NotesTagsTable.SQL_DELETE_NOTES_TAGS_TABLE);
        onCreate(db);
    }

    void deleteDatabase() {
        mContext.deleteDatabase(DATABASE_NAME);
    }
}
