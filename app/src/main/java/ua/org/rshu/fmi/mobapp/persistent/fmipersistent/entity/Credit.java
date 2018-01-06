package ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity;


import ua.org.rshu.fmi.mobapp.persistent.BasicEntity;

/**
 * Created by vb on 16/11/2017.
 */

public class Credit implements BasicEntity {

    private final String creditName;

    private final String groupName;

    private final long groupId;

    private final String teacherName;

    private final long teacherId;

    public Credit(String creditName, String groupName, long groupId, String teacherName, long teacherId) {
        this.creditName = creditName;
        this.groupName = groupName;
        this.groupId = groupId;
        this.teacherName = teacherName;
        this.teacherId = teacherId;
    }

    public String getCreditName() {
        return creditName;
    }

    public String getGroupName() {
        return groupName;
    }

    public long getGroupId() {
        return groupId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public long getTeacherId() {
        return teacherId;
    }
}
