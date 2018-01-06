package ua.org.rshu.fmi.mobapp.dagger.components;

import dagger.Component;
import ua.org.rshu.fmi.mobapp.dagger.modules.PresenterModule;
import ua.org.rshu.fmi.mobapp.dagger.modules.RepositoryModule;
import ua.org.rshu.fmi.mobapp.dagger.modules.ServiceModule;
import ua.org.rshu.fmi.mobapp.view.activity.noteeditor.impl.NoteEditorActivityImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist.core.impl.GroupsListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.newslist.impl.NewsListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.noteslist.impl.NotesListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.forgroup.GroupScheduleListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.forteacher.TeacherScheduleListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.teacherslist.core.impl.TeacherListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.notecontent.NoteContentFragmentImpl;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

@Component(modules = {RepositoryModule.class, ServiceModule.class, PresenterModule.class})
public interface AppComponent {

    void inject(NotesListFragmentImpl notesListFragment);

    void inject(NewsListFragmentImpl newsListFragment);

//    void inject(CreditsListFragmentImpl creditsListFragment);
//
//    void inject(ExamsListFragmentImpl ExamsListFragment);

    void inject(TeacherListFragmentImpl teachersListFragment);

    void inject(GroupsListFragmentImpl groupsListFragment);

    void inject(NoteContentFragmentImpl noteContentFragment);

    void inject(NoteEditorActivityImpl noteEditorActivity);

    void inject(TeacherScheduleListFragmentImpl teacherScheduleListFragment);

    void inject(GroupScheduleListFragmentImpl groupScheduleListFragmentImpl);
}
