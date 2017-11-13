package ua.org.rshu.fmi.mobapp.service.impl;

import android.support.annotation.NonNull;

import com.google.common.base.Optional;

import ua.org.rshu.fmi.mobapp.persistent.entity.Entity;
import ua.org.rshu.fmi.mobapp.persistent.repository.BasicRepository;
import ua.org.rshu.fmi.mobapp.service.BasicService;
import ua.org.rshu.fmi.mobapp.service.form.Form;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public abstract class BasicServiceImpl<T1 extends Entity, T2 extends Form> implements BasicService<T1, T2> {

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
