package ua.org.rshu.fmi.mobapp.service.core;

import android.support.annotation.NonNull;

import ua.org.rshu.fmi.mobapp.persistent.entity.Note;
import ua.org.rshu.fmi.mobapp.service.BasicService;
import ua.org.rshu.fmi.mobapp.service.form.NoteForm;
import ua.org.rshu.fmi.mobapp.util.PaginationArgs;

import java.util.List;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public interface NoteService extends BasicService<Note, NoteForm> {


    /**
     * A method which returns an amount of entities from a start position by a profile id.
     * @param paginationArgs arguments of pagination such as offset and limit.
     * @return a list of entities.
     */
    @NonNull
    List<Note> getNotes(@NonNull PaginationArgs paginationArgs);

    /**
     * A method which retrieves an amount of entities from a start position by a title.
     *
     * @param title          a required name.
     * @param paginationArgs arguments of pagination such as offset and limit.
     * @return a list of entities.
     */
    @NonNull
    List<Note> getByTitle(@NonNull String title, @NonNull PaginationArgs paginationArgs);

    /**
     * A method which retrieves an amount of entities from a start position by a tag id.
     *
     * @param tagId          an id of the tag.
     * @param paginationArgs arguments of pagination such as offset and limit.
     * @return a list of entities.
     */
    @NonNull
    List<Note> getByTag(@NonNull String tagId, @NonNull PaginationArgs paginationArgs);
}
