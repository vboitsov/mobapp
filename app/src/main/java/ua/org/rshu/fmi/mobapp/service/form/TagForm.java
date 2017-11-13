package ua.org.rshu.fmi.mobapp.service.form;

import android.support.annotation.NonNull;

import ua.org.rshu.fmi.mobapp.persistent.entity.Tag;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public class TagForm implements Form<Tag> {

    @NonNull
    private String name;

    public TagForm(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public Tag toEntity(@NonNull String id) {
        return new Tag(id, name, System.currentTimeMillis(), System.currentTimeMillis());
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }
}
