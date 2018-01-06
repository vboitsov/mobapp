package ua.org.rshu.fmi.mobapp;

import android.app.Application;

import io.realm.Realm;
import ua.org.rshu.fmi.mobapp.dagger.components.AppComponent;
import ua.org.rshu.fmi.mobapp.dagger.components.DaggerAppComponent;
import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.database.DatabaseManager;


/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */


public class FMIApplication extends Application {
    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        DatabaseManager.initializeInstance(this);
        sAppComponent = DaggerAppComponent.create();

    }

    public static AppComponent getsAppComponent() {
        return sAppComponent;
    }

}
