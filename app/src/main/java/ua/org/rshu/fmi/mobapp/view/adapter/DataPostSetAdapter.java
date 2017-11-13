package ua.org.rshu.fmi.mobapp.view.adapter;

import ua.org.rshu.fmi.mobapp.persistent.entity.Entity;

import java.util.List;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public interface DataPostSetAdapter<T1 extends Entity> {

    void setData(List<T1> data);

}
