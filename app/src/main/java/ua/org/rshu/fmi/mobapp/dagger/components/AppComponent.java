package ua.org.rshu.fmi.mobapp.dagger.components;

import ua.org.rshu.fmi.mobapp.FMIApplication;
import ua.org.rshu.fmi.mobapp.dagger.modules.PresenterModule;
import ua.org.rshu.fmi.mobapp.dagger.modules.RepositoryModule;
import ua.org.rshu.fmi.mobapp.dagger.modules.ServiceModule;
import ua.org.rshu.fmi.mobapp.view.activity.noteeditor.impl.NoteEditorActivityImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.noteslist.impl.NotesListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.notecontent.NoteContentFragmentImpl;
import dagger.Component;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

@Component(modules = {RepositoryModule.class, ServiceModule.class, PresenterModule.class})
public interface AppComponent {
    //TODO:temp compoment, remove and change it later.
    void inject(FMIApplication application);

    void inject(NotesListFragmentImpl notesListFragment);

    void inject(NoteContentFragmentImpl noteContentFragment);

    void inject(NoteEditorActivityImpl noteEditorActivity);
}
