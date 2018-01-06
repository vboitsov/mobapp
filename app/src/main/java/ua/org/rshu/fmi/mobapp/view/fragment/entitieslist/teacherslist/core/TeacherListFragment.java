package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.teacherslist.core;

import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Teacher;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.EntitiesListWithProgressbarFragment;

/**
 * Created by vb on 18/11/2017.
 */

public interface TeacherListFragment extends EntitiesListWithProgressbarFragment {

    void showNext(Teacher teacher);

}
