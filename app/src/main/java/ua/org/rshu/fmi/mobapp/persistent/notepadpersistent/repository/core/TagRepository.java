package ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.repository.core;

import android.support.annotation.NonNull;

import java.util.List;

import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.entity.Tag;
import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.repository.BasicRepository;
import ua.org.rshu.fmi.mobapp.util.PaginationArgs;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public interface TagRepository extends BasicRepository<Tag> {

    /**
     * A method which retrieves an amount of entities from a start position by a name.
     * @param name a required name.
     * @param paginationArgs a limit and a offset of a pagination.
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
    List<Tag> getByNote(String noteId);
}
