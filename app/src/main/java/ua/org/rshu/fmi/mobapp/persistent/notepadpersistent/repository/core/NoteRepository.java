package ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.repository.core;

import android.support.annotation.NonNull;

import java.util.List;

import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.entity.Note;
import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.repository.BasicRepository;
import ua.org.rshu.fmi.mobapp.util.PaginationArgs;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public interface NoteRepository extends BasicRepository<Note> {


    /**
     * A method which retrieves an amount of entities from required position by a profile id.
     * @param paginationArgs arguments of pagination such as offset and limit.
     * @return a list of entities.
     */
    @NonNull
    List<Note> getNotes(@NonNull PaginationArgs paginationArgs);

    /**
     * A method which creates a relation between a note and an tag.
     * @param noteId an id of the note.
     * @param tagId an id of the tag.
     * @return a result of an insertion.
     */
    boolean addTagToNote(@NonNull String noteId, @NonNull String tagId);

    /**
     * A method which destroys a relation between a note and an tag.
     * @param noteId an id of the note.
     * @param tagId an id of the tag.
     * @return a result of a removing.
     */
    boolean removeTagFromNote(@NonNull String noteId, @NonNull String tagId);

    /**
     * A method which retrieves an amount of entities from a start position by a title.
     * @param title a required name.
     * @param paginationArgs a limit and a offset of a pagination.
     * @return a list of entities.
     */
    @NonNull
    List<Note> getByTitle(@NonNull String title, @NonNull PaginationArgs paginationArgs);

    /**
     * A method which retrieves an amount of entities from a start position by a tag id.
     * @param tagId an id of the tag.
     * @param paginationArgs a limit and a offset of a pagination.
     * @return a list of entities.
     */
    @NonNull
    List<Note> getByTag(@NonNull String tagId, @NonNull PaginationArgs paginationArgs);

}