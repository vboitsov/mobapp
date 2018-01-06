package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.noteslist;

import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.entity.Note;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.EntitiesListFragment;

/**
 * @author Andrii Bei <psihey1@gmail.com>
 */

public interface NotesListFragment extends EntitiesListFragment {

    void showSelectedNote(Note note);

    String getSearchQuery();

    void switchToNormalMode();

    void switchToSearchMode();
}
