package ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity;

import android.os.Parcel;
import android.os.Parcelable;

import ua.org.rshu.fmi.mobapp.persistent.BasicEntity;

/**
 * Created by vb on 15/11/2017.
 */

public class News implements BasicEntity, Parcelable {

    private final String date;

    private final String pic;

    private final String title;

    private final String text;

    public News(String date, String pic, String title, String text) {
        this.pic = pic;
        this.date = date;
        this.title = title;
        this.text = text;
    }

    private News (Parcel in) {
        this.pic = in.readString();
        this.date = in.readString();
        this.title = in.readString();
        this.text = in.readString();
    }

    public String getDate() {
        return date;
    }

    public String getPic() {
        return pic;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.pic);
        dest.writeString(this.date);
        dest.writeString(this.title);
        dest.writeString(this.text);
    }

    public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {

        @Override
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
}
