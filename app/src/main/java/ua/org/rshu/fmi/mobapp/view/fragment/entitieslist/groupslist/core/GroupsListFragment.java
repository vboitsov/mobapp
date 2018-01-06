package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist.core;

import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Group;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.EntitiesListWithProgressbarFragment;

/**
 * Created by vb on 19/11/2017.
 */

public interface GroupsListFragment extends EntitiesListWithProgressbarFragment {

    void showNext(Group group);

}
