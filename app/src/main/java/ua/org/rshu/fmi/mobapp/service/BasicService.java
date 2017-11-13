package ua.org.rshu.fmi.mobapp.service;

import android.support.annotation.NonNull;

import com.google.common.base.Optional;

import ua.org.rshu.fmi.mobapp.persistent.entity.Entity;
import ua.org.rshu.fmi.mobapp.service.form.Form;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public interface BasicService<T1 extends Entity, T2 extends Form> {

    /**
     * A method which creates an entity by a received form.
     * @param form a form with a data.
     * @return an {@code Optional} object which may contain an id of the entity in case of
     * successful creation.
     */
    Optional<String> create(@NonNull T2 form);

    /**
     * A method which opens a connection to a repository.
     */
    boolean openConnection();

    /**
     * A method which closes a connection to a repository.
     */
    boolean closeConnection();

    boolean isConnectionOpened();

    /**
     * A method which removes an entity by an id.
     * @param id an id of an entity.
     */
    boolean remove(@NonNull String id);

    /**
     * A method which returns an entity by an id.
     * @param id an id of a required entity
     * @return a required entity.
     */
    @NonNull
    Optional<T1> getById(@NonNull String id);

    /**
     * A method which updates an entity.
     * @param id an id of the entity.
     * @param form a form with a data.
     */
    boolean update(@NonNull String id, @NonNull T2 form);
}
