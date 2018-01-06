package ua.org.rshu.fmi.mobapp.service.notepadservices.form;

import android.support.annotation.NonNull;

import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.entity.NotepadEntity;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public interface Form<T extends NotepadEntity> {

    /**
     * Converts the form to an entity.
     * @return an converted entity.
     */
    @NonNull
    T toEntity(@NonNull String id);
}
