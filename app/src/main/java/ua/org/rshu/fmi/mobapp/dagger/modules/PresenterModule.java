package ua.org.rshu.fmi.mobapp.dagger.modules;

import ua.org.rshu.fmi.mobapp.service.core.NoteService;

import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.noteslist.NotesListPresenter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.noteslist.impl.NotesListPresenterImpl;


import dagger.Module;
import dagger.Provides;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */
@Module
public class PresenterModule {

    @Provides
    static NotesListPresenter provideNotesListPresenter(NoteService noteService) {
        return new NotesListPresenterImpl(noteService);
    }

}
