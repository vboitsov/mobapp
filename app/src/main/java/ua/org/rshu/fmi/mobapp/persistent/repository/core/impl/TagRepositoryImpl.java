package ua.org.rshu.fmi.mobapp.persistent.repository.core.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;

import java.util.List;

import ua.org.rshu.fmi.mobapp.persistent.database.LavernaContract.NotesTagsTable;
import ua.org.rshu.fmi.mobapp.persistent.entity.Tag;
import ua.org.rshu.fmi.mobapp.persistent.repository.core.TagRepository;
import ua.org.rshu.fmi.mobapp.persistent.repository.impl.BasicRepositoryImpl;
import ua.org.rshu.fmi.mobapp.util.PaginationArgs;

import static ua.org.rshu.fmi.mobapp.persistent.database.LavernaContract.TagsTable.COLUMN_CREATION_TIME;
import static ua.org.rshu.fmi.mobapp.persistent.database.LavernaContract.TagsTable.COLUMN_ID;
import static ua.org.rshu.fmi.mobapp.persistent.database.LavernaContract.TagsTable.COLUMN_NAME;
import static ua.org.rshu.fmi.mobapp.persistent.database.LavernaContract.TagsTable.COLUMN_UPDATE_TIME;
import static ua.org.rshu.fmi.mobapp.persistent.database.LavernaContract.TagsTable.TABLE_NAME;
/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public class TagRepositoryImpl extends BasicRepositoryImpl<Tag> implements TagRepository {

    public TagRepositoryImpl() {
        super(TABLE_NAME);
    }

    @NonNull
    @Override
    protected ContentValues toContentValues(@NonNull Tag entity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, entity.getId());
        contentValues.put(COLUMN_NAME, entity.getName());
        contentValues.put(COLUMN_CREATION_TIME, entity.getCreationTime());
        contentValues.put(COLUMN_UPDATE_TIME, entity.getUpdateTime());
        return contentValues;
    }

    @NonNull
    @Override
    protected Tag toEntity(@NonNull Cursor cursor) {
        return new Tag(
                cursor.getString(cursor.getColumnIndex(COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                cursor.getLong(cursor.getColumnIndex(COLUMN_CREATION_TIME)),
                cursor.getLong(cursor.getColumnIndex(COLUMN_UPDATE_TIME)));
    }

    @NonNull
    @Override
    public List<Tag> getByName(@NonNull String name, @NonNull PaginationArgs paginationArgs) {
        return super.getByName(COLUMN_NAME, name, paginationArgs);
    }

    @NonNull
    @Override
    public List<Tag> getByNote(@NonNull String noteId) {
        String query = "SELECT * FROM " + TABLE_NAME
                + " INNER JOIN " + NotesTagsTable.TABLE_NAME
                + " ON " + NotesTagsTable.TABLE_NAME + "." + NotesTagsTable.COLUMN_TAG_ID
                + " = " + TABLE_NAME + "." + COLUMN_ID
                + " WHERE " + NotesTagsTable.TABLE_NAME + "." + NotesTagsTable.COLUMN_NOTE_ID
                + " = '" + noteId + "'";
        return getByRawQuery(query);
    }

    @Override
    public boolean update(@NonNull Tag entity) {
        String query = "UPDATE " + TABLE_NAME
                + " SET "
                + COLUMN_NAME + "='" + entity.getName() + "', "
                + COLUMN_UPDATE_TIME + "='" + entity.getUpdateTime() + "'"
                + " WHERE " + COLUMN_ID + "='" + entity.getId() + "'";
        return super.rawUpdateQuery(query);
    }
}
