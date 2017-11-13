package ua.org.rshu.fmi.mobapp.service.core;

import android.support.annotation.NonNull;

import ua.org.rshu.fmi.mobapp.persistent.entity.Tag;
import ua.org.rshu.fmi.mobapp.service.BasicService;
import ua.org.rshu.fmi.mobapp.service.form.TagForm;
import ua.org.rshu.fmi.mobapp.util.PaginationArgs;

import java.util.List;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public interface TagService extends BasicService<Tag, TagForm> {

    /**
     * A method which retrieves an amount of entities from a start position by a name.
     * @param name a required name.
     * @param paginationArgs arguments of pagination such as offset and limit.
     * @return a list of entities.
     */
    @NonNull
    List<Tag> getByName(@NonNull String name, @NonNull PaginationArgs paginationArgs);

    /**
     * A method which retrieves all entities by a note id.
     * @param noteId an id of note.
     * @return a list of entities.
     */
    @NonNull
    List<Tag> getByNote(@NonNull String noteId);
}
