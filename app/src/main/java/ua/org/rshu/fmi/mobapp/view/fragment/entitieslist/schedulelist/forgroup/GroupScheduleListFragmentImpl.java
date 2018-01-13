package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.forgroup;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import ua.org.rshu.fmi.mobapp.FMIApplication;
import ua.org.rshu.fmi.mobapp.R;
import ua.org.rshu.fmi.mobapp.view.activity.main.MainActivityImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.core.impl.ScheduleListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.util.consts.BundleKeysConst;

/**
 * Created by vb on 05/01/2018.
 */

public class GroupScheduleListFragmentImpl extends ScheduleListFragmentImpl {

    @Inject GroupScheduleListPresenterImpl mGroupScheduleListPresenter;

    private long groupId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        groupId = getArguments().getLong(BundleKeysConst.BUNDLE_GROUP_ID_KEY);
        FMIApplication.getsAppComponent().inject(this);
        mScheduleListPresenter = mGroupScheduleListPresenter;
        setUpToolbar();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public long getGroupId() {
        return groupId;
    }

    private void setUpToolbar() {
        setHasOptionsMenu(true);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Розклад");
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save_pref, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.item_save) {
            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
            if (sharedPref.getLong(BundleKeysConst.BUNDLE_GROUP_ID_KEY, -1) == -1 ||
                    sharedPref.getLong(BundleKeysConst.BUNDLE_GROUP_ID_KEY, -1) != groupId) {
                saveGroup();
            } else {
                removeGroup();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveGroup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Зберегти групу?")
                .setMessage("Ви можете зберегти цю групу як основу і пропускати сторінку вибору")
                .setIcon(R.drawable.ic_save_black_24dp)
                .setCancelable(false)
                .setNegativeButton("Відмінити",
                        (dialog, id) -> dialog.cancel())
                .setPositiveButton("Зберегти", ((dialog, which) -> {

                    SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putLong(BundleKeysConst.BUNDLE_GROUP_ID_KEY, groupId);
                    editor.apply();


                    dialog.cancel();
                }));
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void removeGroup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Видалити групу?")
                .setMessage("Ви можете видалити цю групу як основу")
                .setIcon(R.drawable.ic_save_black_24dp)
                .setCancelable(false)
                .setNegativeButton("Відмінити",
                        (dialog, id) -> dialog.cancel())
                .setPositiveButton("Видалити", ((dialog, which) -> {
                    SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.remove(BundleKeysConst.BUNDLE_GROUP_ID_KEY);
                    editor.apply();
                    ((MainActivityImpl) getActivity()).clearBackStack();

                    dialog.cancel();
                }));
        AlertDialog alert = builder.create();
        alert.show();
    }


}
