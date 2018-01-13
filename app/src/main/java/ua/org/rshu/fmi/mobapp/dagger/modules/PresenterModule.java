package ua.org.rshu.fmi.mobapp.dagger.modules;

import dagger.Module;
import dagger.Provides;
import ua.org.rshu.fmi.mobapp.service.fmiservices.FmiService;
import ua.org.rshu.fmi.mobapp.service.notepadservices.core.NoteService;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.courselist.CoursesListPresenter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.courselist.impl.CoursesListPresenterImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.creditslist.forgroup.GroupCreditsListPresenterImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.creditslist.forteacher.TeacherCreditsListPresenterImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.examslist.forgroup.GroupExamsListPresenterImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.examslist.forteacher.TeacherExamsListPresenterImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist.core.GroupsListPresenter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist.core.impl.GroupsListPresenterImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.newslist.NewsListPresenter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.newslist.impl.NewsListPresenterImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.noteslist.NotesListPresenter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.noteslist.impl.NotesListPresenterImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.forgroup.GroupScheduleListPresenterImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.forteacher.TeacherScheduleListPresenterImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.teacherslist.core.TeacherListPresenter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.teacherslist.core.impl.TeacherListPresenterImpl;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */
@Module
public class PresenterModule {

    @Provides
    static NotesListPresenter provideNotesListPresenter(NoteService noteService) {
        return new NotesListPresenterImpl(noteService);
    }

    @Provides
    static NewsListPresenter provideNewsListPresenter(FmiService fmiService) {
        return new NewsListPresenterImpl(fmiService);
    }

    @Provides
    static TeacherListPresenter provideTeacherListPresenter(FmiService fmiService) {
        return new TeacherListPresenterImpl(fmiService);
    }

    @Provides
    static GroupsListPresenter provideGroupsListPresenter(FmiService fmiService) {
        return new GroupsListPresenterImpl(fmiService);
    }

    @Provides
    static GroupExamsListPresenterImpl provideGroupExamsListPresenter(FmiService fmiService) {
        return new GroupExamsListPresenterImpl(fmiService);
    }

    @Provides
    static TeacherExamsListPresenterImpl provideTeachersListPresenter(FmiService fmiService) {
        return new TeacherExamsListPresenterImpl(fmiService);
    }

    @Provides
    static GroupCreditsListPresenterImpl provideGroupCreditsListPresenter(FmiService fmiService) {
        return new GroupCreditsListPresenterImpl(fmiService);
    }

    @Provides
    static TeacherCreditsListPresenterImpl provideTeacherCreditsListPresenter(FmiService fmiService) {
        return new TeacherCreditsListPresenterImpl(fmiService);
    }

    @Provides
    static GroupScheduleListPresenterImpl provideGroupSchedulePresenter (FmiService fmiService) {
        return new GroupScheduleListPresenterImpl(fmiService);
    }

    @Provides
    static TeacherScheduleListPresenterImpl provideTeacherSchedulePresenter (FmiService fmiService) {
        return new TeacherScheduleListPresenterImpl(fmiService);
    }

    @Provides
    static CoursesListPresenter provideCoursesListPresenter (FmiService fmiService) {
        return new CoursesListPresenterImpl(fmiService);
    }
}
