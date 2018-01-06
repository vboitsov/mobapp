package ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.repository;


import android.support.annotation.NonNull;

import com.google.common.base.Optional;

import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.entity.NotepadEntity;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public interface BasicRepository<T extends NotepadEntity> {

    /**
     * A method which adds an entity to a database.
     * @param entity an object which extends the Laverna's entity class.
     * @return a boolean result of an insertion.
     */
    boolean add(@NonNull T entity);

    /**
     * A method which removes an entity from a database by its id.
     * @param id an id of the Laverna's entity class.
     * @return a boolean result of a removing
     */
    boolean remove(@NonNull String id);

    /**
     * A method which retrieves an entity from a database by its id.
     * @param id an id of the required entity.
     * @return an {@code Optional} object which may contain the required entity.
     */
    @NonNull
    Optional<T> getById(@NonNull String id);

    /**
     * A method which updates certain fields of entity in a database.
     * @param entity an entity with a new data to update.
     * @return a boolean result of an update.
     */
    boolean update(@NonNull T entity);


    /**
     * A method which opens a database connection.
     * @return a status of the opening.
     */
    boolean openDatabaseConnection();

    /**
     * A method which closes a database connection.
     * @return a status of the closing.
     */
    boolean closeDatabaseConnection();

    /**
     * A method which returns a status of connection opening.
     * @return a status of the opening
     */
    boolean isConnectionOpened();



}

