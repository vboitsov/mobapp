package ua.org.rshu.fmi.mobapp.dagger.modules;

import android.support.annotation.NonNull;

import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.repository.FmiRepository;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.repository.impl.FmiRepositoryImpl;
import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.repository.core.NoteRepository;
import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.repository.core.TagRepository;
import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.repository.core.impl.NoteRepositoryImpl;
import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.repository.core.impl.TagRepositoryImpl;

import dagger.Module;
import dagger.Provides;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

@Module
public class RepositoryModule {

    @NonNull
    @Provides
    static NoteRepository provideNotesRepository() {
        return new NoteRepositoryImpl();
    }


    @NonNull
    @Provides
    static TagRepository provideTagsRepository() {
        return new TagRepositoryImpl();
    }

    @NonNull
    @Provides
    static FmiRepository provideFmiRepository() {
        return new FmiRepositoryImpl();
    }

}
