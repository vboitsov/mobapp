package ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity;

import ua.org.rshu.fmi.mobapp.persistent.BasicEntity;

/**
 * Created by vb on 19/11/2017.
 */

public class Group implements BasicEntity {

    private final long groupId;

    private final String groupName;

    public Group(long groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public long getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public String toString() {
        return "Group{" +
                "teacherId=" + groupId +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
