package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist;

import android.support.v7.widget.RecyclerView;

import ua.org.rshu.fmi.mobapp.persistent.entity.Entity;
import ua.org.rshu.fmi.mobapp.service.form.Form;
import ua.org.rshu.fmi.mobapp.view.adapter.DataPostSetAdapter;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public interface EntitiesListPresenter<T1 extends Entity, T2 extends Form> /*extends MenuItemCompat.OnActionExpandListener */ {

    void bindView(EntitiesListFragment allNotesFragment);

    void unbindView();

    void subscribeRecyclerViewForPagination(RecyclerView recyclerView);

    void disposePagination();

    void setDataToAdapter(DataPostSetAdapter<T1> dataPostSetAdapter);

}
