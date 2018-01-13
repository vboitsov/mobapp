package ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity;

import android.os.Parcel;
import android.os.Parcelable;

import ua.org.rshu.fmi.mobapp.persistent.BasicEntity;

/**
 * Created by vb on 30/11/2017.
 */

public class Course implements Parcelable, BasicEntity {

    private final long id;

    private final String courseName;

    private final String courseDescription;

    public Course(long id, String courseName, String courseDescription) {
        this.id = id;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
    }

    protected Course(Parcel in) {
        id = in.readLong();
        courseName = in.readString();
        courseDescription = in.readString();
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    public long getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(courseName);
        dest.writeString(courseDescription);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                '}';
    }
}
