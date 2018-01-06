package ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity;


import ua.org.rshu.fmi.mobapp.persistent.BasicEntity;

/**
 * Created by vb on 16/11/2017.
 */

public class Teacher implements BasicEntity {

    private final long teacherId;

    private final String teacherName;

    private final String teacherOccupation;

    private final String teacherPic;

    public Teacher(long teacherId, String teacherName, String teacherOccupation, String teacherPic) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherOccupation = teacherOccupation;
        this.teacherPic = teacherPic;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getTeacherOccupation() {
        return teacherOccupation;
    }

    public String getTeacherPic() {
        return teacherPic;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", teacherOccupation='" + teacherOccupation + '\'' +
                ", teacherPic='" + teacherPic + '\'' +
                '}';
    }
}
