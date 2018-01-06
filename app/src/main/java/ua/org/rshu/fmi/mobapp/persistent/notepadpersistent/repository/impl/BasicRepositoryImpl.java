package ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.repository.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.Optional;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.database.DatabaseManager;
import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.entity.NotepadEntity;
import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.repository.BasicRepository;
import ua.org.rshu.fmi.mobapp.util.PaginationArgs;

import static ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.database.LavernaContract.LavernaBaseTable.COLUMN_ID;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public abstract class BasicRepositoryImpl<T extends NotepadEntity>  implements BasicRepository<T> {

    /**
     * A name of a table represented by the repository.
     */
    protected final String mTableName;

    @Nullable
    protected SQLiteDatabase mDatabase;

    public BasicRepositoryImpl(@NonNull String mTableName) {
        this.mTableName = mTableName;
    }

    @Override
    public boolean add(@NonNull T entity) {
        boolean result = false;
        mDatabase.beginTransaction();
        try {
            result = mDatabase.insertOrThrow(mTableName, null, toContentValues(entity)) != -1;
            mDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
            Logger.e(e, "Error while doing transaction.");
        } finally {
            mDatabase.endTransaction();
        }
        Logger.d("Table name: %s\nOperation: add\nNotepadEntity: %s\nResult: %s", mTableName, entity, result);
        return result;
    }

    @Override
    public boolean remove(@NonNull String id) {
        boolean result = false;
        mDatabase.beginTransaction();
        try {
            result = mDatabase.delete(mTableName, COLUMN_ID + "= '" + id + "'", null) != 0;
            mDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
            Logger.e(e, "Error while doing transaction.");
        } finally {
            mDatabase.endTransaction();
        }
        Logger.d("Table name: %s\nOperation: remove\nId: %s\nResult: %s", mTableName, id, result);
        return result;
    }

    @NonNull
    @Override
    public Optional<T> getById(@NonNull String id) {
        Cursor cursor = mDatabase.rawQuery(
                "SELECT * FROM " + mTableName
                        + " WHERE " + COLUMN_ID + " = '" + id + "'",
                new String[]{});
        Logger.d("Table name: %s\nOperation: getById\nId: %s\nCursor: %s", mTableName, id, (cursor != null));
        if (cursor != null && cursor.moveToFirst()) {
            return Optional.of(toEntity(cursor));
        }
        return Optional.absent();
    }

    @Override
    public boolean openDatabaseConnection() {
        if (mDatabase != null) {
            Logger.init().methodCount(Thread.currentThread().getStackTrace().length);
            Logger.w("Connection is already opened");
            Logger.init().methodCount(2);
            return false;
        }
        mDatabase = DatabaseManager.getInstance().openConnection();
        Logger.i("Connection is opened");
        return true;
    }

    @Override
    public boolean closeDatabaseConnection() {
        if (mDatabase == null) {
            Logger.init().methodCount(Thread.currentThread().getStackTrace().length);
            Logger.w("Connection is already closed");
            Logger.init().methodCount(2);
            return false;
        }
        DatabaseManager.getInstance().closeConnection();
        mDatabase = null;
        Logger.i("Connection is closed");
        return true;
    }

    @Override
    public boolean isConnectionOpened() {
        return mDatabase != null;
    }

    /**
     * A method which retrieves objects from the database by a raw SQL query.
     * @param query a {@code String} object of a raw SQL query
     * @return a list of objects.
     */
    @NonNull
    protected List<T> getByRawQuery(String query) {
        Cursor cursor = mDatabase.rawQuery(query, new String[]{});
        Logger.d("Raw query: %s\nCursor: %s", query, (cursor != null));
        List<T> entities = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                entities.add(toEntity(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return entities;
    }

    /**
     * A method which retrieves objects from the database by a query with LIKE operator.
     * @param columnName a name of the column to find by.
     * @param name a value for a find by.
     * @param paginationArgs a limit and a offset of a pagination.
     * @return a list of entities.
     */
    @NonNull
    protected List<T> getByName(@NonNull String columnName,
                                @NonNull String name,
                                @NonNull PaginationArgs paginationArgs) {
        String query = "SELECT * FROM " + mTableName
                + " WHERE ('" + name + "'='' OR "+ columnName + " LIKE '%" + name + "%')"
                + " LIMIT " + paginationArgs.limit
                + " OFFSET " + paginationArgs.offset;
        return getByRawQuery(query);
    }

    /**
     * A method which executes a raw update query.
     * @param query a string oject with query.
     * @return a result of an update.
     */
    protected boolean rawUpdateQuery(@NonNull String query) {
        Cursor cursor = mDatabase.rawQuery(query, null);
        Logger.d("Raw update: %s\nCursor: %s", query, (cursor != null));
        if (cursor != null) {
            cursor.moveToFirst();
            cursor.close();
            return true;
        }
        return false;
    }

    /**
     * A method which converts a received entity into a ContentValues object.
     * @param entity a {@code ProfileDependedEntity} extended object to convert.
     * @return a {@code ContentValues} object.
     */
    protected abstract ContentValues toContentValues(T entity);

    /**
     * A method which parse a received cursor result of a query into an entity.
     * @param cursor a {@code Cursor} with a data to convert into entity.
     * @return a {@code ProfileDependedEntity} object.
     */
    protected abstract T toEntity(Cursor cursor);
}
