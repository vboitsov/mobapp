package ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public class Note extends NotepadEntity {

    @NonNull
    private String title;

    /**
     * A date of the model's creation in milliseconds.
     */
    private long creationTime;

    /**
     * A date of the model's creation in milliseconds.
     */
    private long updateTime;

    /**
     * A plain text of a note's content.
     */
    @NonNull
    private String content;

    /**
     * A note's content in html.
     */
    @NonNull
    private String htmlContent;

    public Note(String id,
                @NonNull String title,
                long creationTime,
                long updateTime,
                @NonNull String content,
                @NonNull String htmlContent) {
        this.id = id;
        this.title = title;
        this.creationTime = creationTime;
        this.updateTime = updateTime;
        this.content = content;
        this.htmlContent = htmlContent;
    }

    private Note(Parcel in) {
        id = in.readString();
        title = in.readString();
        creationTime = in.readLong();
        updateTime = in.readLong();
        content = in.readString();
        htmlContent = in.readString();
    }

    @NonNull
    @Override
    public String toString() {
        return "Note{" + super.toString() +
                ", creationTime=" + creationTime +
                ", updateTime=" + updateTime +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeLong(creationTime);
        dest.writeLong(updateTime);
        dest.writeString(content);
        dest.writeString(htmlContent);
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
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

    public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>() {

        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
