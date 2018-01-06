package ua.org.rshu.fmi.mobapp.dagger.modules;

import android.support.annotation.NonNull;

import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.repository.FmiRepository;
import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.repository.core.NoteRepository;
import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.repository.core.TagRepository;
import ua.org.rshu.fmi.mobapp.service.fmiservices.FmiService;
import ua.org.rshu.fmi.mobapp.service.fmiservices.imp.FmiServiceImp;
import ua.org.rshu.fmi.mobapp.service.notepadservices.core.NoteService;
import ua.org.rshu.fmi.mobapp.service.notepadservices.core.TagService;
import ua.org.rshu.fmi.mobapp.service.notepadservices.core.impl.NoteServiceImpl;
import ua.org.rshu.fmi.mobapp.service.notepadservices.core.impl.TagServiceImpl;

import dagger.Module;
import dagger.Provides;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

@Module
public class ServiceModule {

    @NonNull
    @Provides
    static NoteService provideNoteService(NoteRepository noteRepository, TagService tagService) {
        return new NoteServiceImpl(noteRepository,tagService);
    }

    @NonNull
    @Provides
    static TagService provideTagsService(TagRepository tagRepository) {
        return new TagServiceImpl(tagRepository);
    }

    @NonNull
    @Provides
    static FmiService provideFmiService(FmiRepository fmiRepository) {
        return new FmiServiceImp(fmiRepository);
    }

}
