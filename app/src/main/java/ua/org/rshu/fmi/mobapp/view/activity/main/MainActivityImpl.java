package ua.org.rshu.fmi.mobapp.view.activity.main;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.org.rshu.fmi.mobapp.R;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.noteslist.impl.NotesListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.util.consts.FragmentConst;

public class MainActivityImpl extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;

    @BindView(R.id.toolbar) Toolbar mToolBar;

    private Bundle mSavedInstanceState;
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSavedInstanceState = savedInstanceState;
        setContentView(ua.org.rshu.fmi.mobapp.R.layout.activity_main);
        setOrientationByUserDeviceConfiguration();
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);
        ActionBarDrawerToggle mToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolBar, ua.org.rshu.fmi.mobapp.R.string.navigation_drawer_open, ua.org.rshu.fmi.mobapp.R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        ((NavigationView) findViewById(ua.org.rshu.fmi.mobapp.R.id.nav_view)).setNavigationItemSelectedListener(this);

        NotesListFragmentImpl notesListFragment = new NotesListFragmentImpl();
        menuStartSelectFragment(notesListFragment, FragmentConst.TAG_NOTES_LIST_FRAGMENT);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (ua.org.rshu.fmi.mobapp.R.id.nav_item_all_notes == id) {
            NotesListFragmentImpl notesListFragment = new NotesListFragmentImpl();
            menuStartSelectFragment(notesListFragment, FragmentConst.TAG_NOTES_LIST_FRAGMENT);
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * A method which check user device screen orientation at start app,
     * and set needed screen orientation for app work
     */
    private void setOrientationByUserDeviceConfiguration() {
        if (getResources().getConfiguration().smallestScreenWidthDp < 600) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    /**
     * A method which create and replace new fragment in current container for fragment , with
     * adding to back stack
     *
     * @param fragment a fragment what we create
     * @param tag      a tag name of fragment
     */
    private void menuStartSelectFragment(Fragment fragment, String tag) {
        if (mSavedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .replace(ua.org.rshu.fmi.mobapp.R.id.constraint_container, fragment, tag)
//                    .addToBackStack(tag)
                    .commit();
        }
    }
}