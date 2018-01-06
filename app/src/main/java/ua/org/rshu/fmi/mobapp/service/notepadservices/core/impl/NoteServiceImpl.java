package ua.org.rshu.fmi.mobapp.service.notepadservices.core.impl;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.common.base.Optional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;

import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.entity.Note;
import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.entity.Tag;
import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.repository.core.NoteRepository;
import ua.org.rshu.fmi.mobapp.service.notepadservices.core.NoteService;
import ua.org.rshu.fmi.mobapp.service.notepadservices.core.TagService;
import ua.org.rshu.fmi.mobapp.service.notepadservices.form.NoteForm;
import ua.org.rshu.fmi.mobapp.service.notepadservices.form.TagForm;
import ua.org.rshu.fmi.mobapp.service.notepadservices.impl.BasicServiceImpl;
import ua.org.rshu.fmi.mobapp.service.notepadservices.util.NoteTextParser;
import ua.org.rshu.fmi.mobapp.util.PaginationArgs;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public class NoteServiceImpl extends BasicServiceImpl<Note, NoteForm> implements NoteService {

    private final NoteRepository mNoteRepository;

    private final TagService mTagService;

    @Inject
    public NoteServiceImpl(@NonNull NoteRepository noteRepository,
                           @NonNull TagService tagService) {
        super(noteRepository);
        mNoteRepository = noteRepository;
        mTagService = tagService;
    }

    @Override
    public Optional<String> create(@NonNull NoteForm noteForm) {
        String noteId = UUID.randomUUID().toString();
        noteForm.setContent(NoteTextParser.parseSingleQuotes(noteForm.getContent()));
        if (validateForCreate(noteForm.getTitle()) && mNoteRepository.add(noteForm.toEntity(noteId))) {
            parseContent(noteId, noteForm.getContent());
            return Optional.of(noteId);
        }
        return Optional.absent();
    }


    @NonNull
    @Override
    public List<Note> getNotes(@NonNull PaginationArgs paginationArgs) {
        return mNoteRepository.getNotes(paginationArgs);
    }


    @NonNull
    @Override
    public List<Note> getByTitle(@NonNull String title, @NonNull PaginationArgs paginationArgs) {
        return mNoteRepository.getByTitle(title, paginationArgs);
    }

    @NonNull
    @Override
    public List<Note> getByTag(@NonNull String tagId, @NonNull PaginationArgs paginationArgs) {
        return mNoteRepository.getByTag(tagId, paginationArgs);
    }

    @Override
    public boolean update(@NonNull String id, @NonNull NoteForm noteForm) {
        noteForm.setContent(NoteTextParser.parseSingleQuotes(noteForm.getContent()));
        if (validateForUpdate(noteForm.getTitle()) && mNoteRepository.update(noteForm.toEntity(id))) {
            parseContent(id, noteForm.getContent());
            return true;
        }
        return false;
    }

    /**
     * A method which calls methods of parsing tasks and tags from a note content.
     * @param noteId an id of a note.
     * @param content a text content from a note.
     */
    private void parseContent(String noteId, String content) {
        parseTags(noteId, content);
    }

    /**
     * A method which parses tags from a note content and then creates new tags or removes relations
     * between a note and tags.
     * @param noteId an id of note.
     * @param content a text content from a note.
     */
    private void parseTags(String noteId, String content) {
        Set<String> tagNames =  NoteTextParser.parseTags(content);
        mTagService.openConnection();
        for (Tag tag : mTagService.getByNote(noteId)) {
            removeTagsFromNote(tag, tagNames, noteId);
        }
        for (String tagName : tagNames) {
            createTagAndAddToNote(tagName, noteId);
        }
        mTagService.closeConnection();
    }

    /**
     * A method which removes a relation a note and tag, if the last one is not present in content
     * anymore.
     * @param tag a tag to check.
     * @param tagNames a set of tags parsed from a note.
     * @param noteId an id of a note.
     */
    private void removeTagsFromNote(Tag tag, Set<String> tagNames, String noteId) {
        if (tagNames.contains(tag.getName())) {
            tagNames.remove(tag.getName());
        } else {
            mNoteRepository.removeTagFromNote(noteId, tag.getId());
        }
    }

    /**
     * A method which creates a tag and a relation between the tag and a note.
     * @param tagName a name of a tag.
     * @param noteId an id on a note.
     */
    private void createTagAndAddToNote(String tagName, String noteId) {
        List<Tag> tags = mTagService.getByName(tagName, new PaginationArgs(0, 1));
        if (tags.size() != 0) {
            mNoteRepository.addTagToNote(noteId, tags.get(0).getId());
            return;
        }
        Optional<String> tagIdOptional = mTagService.create(new TagForm(tagName));
        if(tagIdOptional.isPresent()) {
            mNoteRepository.addTagToNote(noteId, tagIdOptional.get());
        }
    }

    /**
     * A method which validates a form in the create method.
     * @param title a title of an entity.
     * @return a boolean result of a validation.
     */
    private boolean validateForCreate(String title) {
        return !TextUtils.isEmpty(title);
    }

    /**
     * A method which validates a form in the update method.
     * @param title a title of an entity.
     * @return a boolean result of a validation.
     */
    private boolean validateForUpdate(String title) {
        return !TextUtils.isEmpty(title);
    }
}
