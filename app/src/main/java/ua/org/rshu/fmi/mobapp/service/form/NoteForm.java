package ua.org.rshu.fmi.mobapp.service.form;

import android.support.annotation.NonNull;

import ua.org.rshu.fmi.mobapp.persistent.entity.Note;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public class NoteForm implements Form<Note> {

    @NonNull
    private String title;

    @NonNull
    private String content;

    @NonNull
    private String htmlContent;

    public NoteForm(
                    @NonNull String title,
                    @NonNull String content,
                    @NonNull String htmlContent) {
        this.title = title;
        this.content = content;
        this.htmlContent = htmlContent;
    }

    @NonNull
    @Override
    public Note toEntity(@NonNull String id) {
        return new Note(
                id,
                title,
                System.currentTimeMillis(),
                System.currentTimeMillis(),
                content,
                htmlContent);
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    public void setContent(@NonNull String content) {
        this.content = content;
    }

    @NonNull
    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(@NonNull String htmlContent) {
        this.htmlContent = htmlContent;
    }
}
