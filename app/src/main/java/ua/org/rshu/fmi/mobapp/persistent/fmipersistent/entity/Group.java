package ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity;

import android.os.Parcel;
import android.os.Parcelable;

import ua.org.rshu.fmi.mobapp.persistent.BasicEntity;

/**
 * Created by vb on 19/11/2017.
 */

public class Group implements BasicEntity, Parcelable {

    private final long groupId;

    private final String groupName;

    public Group(long groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    protected Group(Parcel in) {
        groupId = in.readLong();
        groupName = in.readString();
    }

    public static final Creator<Group> CREATOR = new Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel in) {
            return new Group(in);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };

    public long getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(groupId);
        dest.writeString(groupName);
    }
}
