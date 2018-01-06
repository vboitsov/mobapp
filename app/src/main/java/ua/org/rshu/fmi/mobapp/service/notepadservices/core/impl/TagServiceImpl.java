package ua.org.rshu.fmi.mobapp.service.notepadservices.core.impl;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.common.base.Optional;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.entity.Tag;
import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.repository.core.TagRepository;
import ua.org.rshu.fmi.mobapp.service.notepadservices.core.TagService;
import ua.org.rshu.fmi.mobapp.service.notepadservices.form.TagForm;
import ua.org.rshu.fmi.mobapp.service.notepadservices.impl.BasicServiceImpl;
import ua.org.rshu.fmi.mobapp.util.PaginationArgs;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public class TagServiceImpl extends BasicServiceImpl<Tag, TagForm> implements TagService {

    private final TagRepository mTagRepository;

    @Inject
    public TagServiceImpl(@NonNull TagRepository tagRepository) {
        super(tagRepository);
        mTagRepository = tagRepository;
    }

    @Override
    public Optional<String> create(@NonNull TagForm tagForm) {
        String tagId = UUID.randomUUID().toString();
        if(validateForCreate(tagForm.getName()) && mTagRepository.add(tagForm.toEntity(tagId))) {
            return Optional.of(tagId);
        }
        return Optional.absent();
    }

    //TODO: Resolve this problem by put update method only in Note Service.
    @Deprecated
    @Override
    public boolean update(@NonNull String id, @NonNull TagForm form) {
        return false;
    }

    @NonNull
    @Override
    public List<Tag> getByName(@NonNull String name, @NonNull PaginationArgs paginationArgs) {
        return mTagRepository.getByName(name, paginationArgs);
    }

    @NonNull
    @Override
    public List<Tag> getByNote(@NonNull String noteId) {
        return mTagRepository.getByNote(noteId);
    }

    /**
     * A method which validates a form in the create method.
     * @param name an id of the entity to validate.
     * @return a boolean result of validation.
     */
    private boolean validateForCreate(String name) {
        return !TextUtils.isEmpty(name);
    }
}
