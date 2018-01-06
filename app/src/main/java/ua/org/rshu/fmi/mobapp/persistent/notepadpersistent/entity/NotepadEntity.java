package ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.entity;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import ua.org.rshu.fmi.mobapp.persistent.BasicEntity;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public abstract class NotepadEntity implements BasicEntity, Parcelable {

    @NonNull
    protected String id;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "NotepadEntity{" +
                "id='" + id + '\'' +
                '}';
    }
}
