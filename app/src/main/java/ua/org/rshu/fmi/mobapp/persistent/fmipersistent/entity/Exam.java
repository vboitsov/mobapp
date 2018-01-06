package ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity;


import ua.org.rshu.fmi.mobapp.persistent.BasicEntity;

/**
 * Created by vb on 16/11/2017.
 */

public class Exam implements BasicEntity {

    private final String examName;

    private final String examDate;

    private final String examTime;

    private final String examAudience;

    private final String groupName;

    private final long groupId;

    private final String teacherName;

    private final long teacherId;

    public Exam(String examName, String examDate, String examTime, String examAudience, String groupName, long groupId, String teacherName, long teacherId) {
        this.examName = examName;
        this.examDate = examDate;
        this.examTime = examTime;
        this.examAudience = examAudience;
        this.groupName = groupName;
        this.groupId = groupId;
        this.teacherName = teacherName;
        this.teacherId = teacherId;
    }

    public String getExamName() {
        return examName;
    }

    public String getExamDate() {
        return examDate;
    }

    public String getExamTime() {
        return examTime;
    }

    public String getExamAudience() {
        return examAudience;
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
