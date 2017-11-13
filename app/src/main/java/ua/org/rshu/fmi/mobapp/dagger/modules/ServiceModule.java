package ua.org.rshu.fmi.mobapp.dagger.modules;

import android.support.annotation.NonNull;

import ua.org.rshu.fmi.mobapp.persistent.repository.core.NoteRepository;
import ua.org.rshu.fmi.mobapp.persistent.repository.core.TagRepository;
import ua.org.rshu.fmi.mobapp.service.core.NoteService;
import ua.org.rshu.fmi.mobapp.service.core.TagService;
import ua.org.rshu.fmi.mobapp.service.core.impl.NoteServiceImpl;
import ua.org.rshu.fmi.mobapp.service.core.impl.TagServiceImpl;

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

}
