package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.noteslist;

import ua.org.rshu.fmi.mobapp.persistent.entity.Note;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.EntitiesListFragment;

/**
 * @author Andrii Bei <psihey1@gmail.com>
 */

public interface NotesListFragment extends EntitiesListFragment {

    void showSelectedNote(Note note);

    String getSearchQuery();

    void switchToNormalMode();

    void switchToSearchMode();
}
