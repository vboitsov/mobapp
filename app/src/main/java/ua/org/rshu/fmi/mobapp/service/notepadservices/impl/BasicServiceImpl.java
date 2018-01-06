package ua.org.rshu.fmi.mobapp.service.notepadservices.impl;

import android.support.annotation.NonNull;

import com.google.common.base.Optional;

import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.entity.NotepadEntity;
import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.repository.BasicRepository;
import ua.org.rshu.fmi.mobapp.service.notepadservices.BasicService;
import ua.org.rshu.fmi.mobapp.service.notepadservices.form.Form;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public abstract class BasicServiceImpl<T1 extends NotepadEntity, T2 extends Form> implements BasicService<T1, T2> {

    private final BasicRepository<T1> basicRepository;

    public BasicServiceImpl(@NonNull BasicRepository<T1> basicRepository) {
        this.basicRepository = basicRepository;
    }

    @Override
    public boolean openConnection() {
        return basicRepository.openDatabaseConnection();
    }

    @Override
    public boolean isConnectionOpened() {
        return basicRepository.isConnectionOpened();
    }

    @Override
    public boolean closeConnection() {
        return basicRepository.closeDatabaseConnection();
    }

    @NonNull
    @Override
    public Optional<T1> getById(@NonNull String id) {
        return basicRepository.getById(id);
    }

    @Override
    public boolean remove(@NonNull String id) {
        return basicRepository.remove(id);
    }
}
