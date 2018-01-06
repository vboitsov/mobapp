package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core;

import android.support.v7.widget.RecyclerView;

import ua.org.rshu.fmi.mobapp.persistent.BasicEntity;
import ua.org.rshu.fmi.mobapp.view.adapter.DataPostSetAdapter;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public interface EntitiesListPresenter<T extends BasicEntity> {

    void bindView(EntitiesListFragment allNotesFragment);

    void unbindView();

    void subscribeRecyclerViewForPagination(RecyclerView recyclerView);

    void disposePagination();

    void setDataToAdapter(DataPostSetAdapter<T> dataPostSetAdapter);

}
