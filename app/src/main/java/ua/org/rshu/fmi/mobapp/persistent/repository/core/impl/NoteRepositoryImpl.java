package ua.org.rshu.fmi.mobapp.persistent.repository.core.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

import java.util.List;

import ua.org.rshu.fmi.mobapp.persistent.database.LavernaContract;
import ua.org.rshu.fmi.mobapp.persistent.entity.Note;
import ua.org.rshu.fmi.mobapp.persistent.repository.core.NoteRepository;
import ua.org.rshu.fmi.mobapp.persistent.repository.impl.BasicRepositoryImpl;
import ua.org.rshu.fmi.mobapp.util.PaginationArgs;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public class NoteRepositoryImpl extends BasicRepositoryImpl<Note> implements NoteRepository {
    private static final String TAG = "NoteRepoImpl";

    public NoteRepositoryImpl() {
        super(LavernaContract.NotesTable.TABLE_NAME);
    }

    @NonNull
    @Override
    protected ContentValues toContentValues(@NonNull Note entity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(LavernaContract.LavernaBaseTable.COLUMN_ID, entity.getId());
        contentValues.put(LavernaContract.NotesTable.COLUMN_TITLE, entity.getTitle());
        contentValues.put(LavernaContract.NotesTable.COLUMN_CREATION_TIME, entity.getCreationTime());
        contentValues.put(LavernaContract.NotesTable.COLUMN_UPDATE_TIME, entity.getUpdateTime());
        contentValues.put(LavernaContract.NotesTable.COLUMN_CONTENT, entity.getContent());
        contentValues.put(LavernaContract.NotesTable.COLUMN_HTML_CONTENT, entity.getHtmlContent());
        return contentValues;
    }

    @NonNull
    @Override
    protected Note toEntity(@NonNull Cursor cursor) {
        return new Note(
                cursor.getString(cursor.getColumnIndex(LavernaContract.LavernaBaseTable.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(LavernaContract.NotesTable.COLUMN_TITLE)),
                cursor.getLong(cursor.getColumnIndex(LavernaContract.NotesTable.COLUMN_CREATION_TIME)),
                cursor.getLong(cursor.getColumnIndex(LavernaContract.NotesTable.COLUMN_UPDATE_TIME)),
                cursor.getString(cursor.getColumnIndex(LavernaContract.NotesTable.COLUMN_CONTENT)),
                cursor.getString(cursor.getColumnIndex(LavernaContract.NotesTable.COLUMN_HTML_CONTENT)));
    }

    @NonNull
    @Override
    public List<Note> getNotes(@NonNull PaginationArgs paginationArgs) {
        String query = "SELECT * FROM " + super.mTableName
                + " LIMIT " + paginationArgs.limit
                + " OFFSET " + paginationArgs.offset;
        return getByRawQuery(query);
    }

    @Override
    public boolean addTagToNote(@NonNull String noteId, @NonNull String tagId) {
        boolean result = false;
        mDatabase.beginTransaction();
        try {
            ContentValues contentValues = toNoteTagsContentValues(noteId, tagId);
            result = mDatabase.insert(LavernaContract.NotesTagsTable.TABLE_NAME, null, contentValues) != -1;
            mDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
            Logger.e(e, "Error while doing transaction.");
        } finally {
            mDatabase.endTransaction();
        }
        Logger.d("Table name: %s\nOperation: addTagToNote\nNote id: %s\nTag id: %s\nResult: %s",
                LavernaContract.NotesTable.TABLE_NAME, noteId, tagId, result);
        return result;
    }

    @Override
    public boolean removeTagFromNote(@NonNull String noteId, @NonNull String tagId) {
        boolean result = false;
        mDatabase.beginTransaction();
        try {
            String query = LavernaContract.NotesTagsTable.COLUMN_TAG_ID + " = '" + tagId + "' AND "
                    + LavernaContract.NotesTagsTable.COLUMN_NOTE_ID + " = '" + noteId + "'";
            result = mDatabase.delete(LavernaContract.NotesTagsTable.TABLE_NAME, query, null) != 0;
            mDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
            Logger.e(e, "Error while doing transaction.");
        } finally {
            mDatabase.endTransaction();
        }
        Logger.d("Table name: %s\nOperation: removeTagToNote\nNote id: %s\nTag id: %s\nResult: %s",
                LavernaContract.NotesTable.TABLE_NAME, noteId, tagId, result);
        return result;
    }


    @NonNull
    @Override
    public List<Note> getByTitle(@NonNull String title, @NonNull PaginationArgs paginationArgs) {
        return super.getByName(LavernaContract.NotesTable.COLUMN_TITLE, title, paginationArgs);
    }

    @NonNull
    @Override
    public List<Note> getByTag(@NonNull String tagId, @NonNull PaginationArgs paginationArgs) {
        String query = "SELECT *"
                + " FROM " + LavernaContract.NotesTable.TABLE_NAME
                + " INNER JOIN " + LavernaContract.NotesTagsTable.TABLE_NAME
                + " ON " + LavernaContract.NotesTable.TABLE_NAME + "." + LavernaContract.LavernaBaseTable.COLUMN_ID
                + "=" + LavernaContract.NotesTagsTable.TABLE_NAME + "." + LavernaContract.NotesTagsTable.COLUMN_NOTE_ID
                + " WHERE " + LavernaContract.NotesTagsTable.TABLE_NAME + "." + LavernaContract.NotesTagsTable.COLUMN_TAG_ID + "='" + tagId + "'"
                + " LIMIT " + paginationArgs.limit
                + " OFFSET " + paginationArgs.offset;
        return getByRawQuery(query);
    }

    @Override
    public boolean update(@NonNull Note entity) {
        boolean result = false;
        mDatabase.beginTransaction();
        try {
            result = mDatabase.update(mTableName,toContentValues(entity),LavernaContract.LavernaBaseTable.COLUMN_ID + "= ?", new String[]{entity.getId()}) != -1;
            mDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
            Logger.e(e, "Error while doing transaction.");
        } finally {
            mDatabase.endTransaction();
        }
        Logger.d("Table name: %s\nOperation: update\nEntity: %s\nResult: %s", mTableName, entity, result);
        return result;
    }

    /**
     * A method which converts received tags and a note into a {@code ContentValues} for
     * a NotesTags table.
     */
    @NonNull
    private ContentValues toNoteTagsContentValues(@NonNull String noteId, @NonNull String tagId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(LavernaContract.NotesTagsTable.COLUMN_NOTE_ID, noteId);
        contentValues.put(LavernaContract.NotesTagsTable.COLUMN_TAG_ID, tagId);
        return contentValues;
    }
}
