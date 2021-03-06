package ua.org.rshu.fmi.mobapp.service.notepadservices.core;

import android.support.annotation.NonNull;

import java.util.List;

import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.entity.Note;
import ua.org.rshu.fmi.mobapp.service.notepadservices.BasicService;
import ua.org.rshu.fmi.mobapp.service.notepadservices.form.NoteForm;
import ua.org.rshu.fmi.mobapp.util.PaginationArgs;

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
