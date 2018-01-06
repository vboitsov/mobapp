package ua.org.rshu.fmi.mobapp.view.adapter;

import java.util.List;

import ua.org.rshu.fmi.mobapp.persistent.BasicEntity;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public interface DataPostSetAdapter<T1 extends BasicEntity> {

    void setData(List<T1> data);

}
