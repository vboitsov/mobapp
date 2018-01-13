package ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity;


import ua.org.rshu.fmi.mobapp.persistent.BasicEntity;

/**
 * Created by vb on 15/11/2017.
 */

public class Lesson implements BasicEntity {

    private final int lessonOrder;

    private final boolean halfGrouped;

    private final String firstSubjectName;

    private final String firstSubjectGroupOrTeacherName;

    private final String firstSubjectAudience;

    private final String secondSubjectName;

    private final String secondSubjectGroupOrTeacherName;

    private final String secondSubjectAudience;

    public Lesson(int lessonOrder, boolean halfGrouped, String firstSubjectName, String firstSubjectGroupOrTeacherName, String firstSubjectAudience, String secondSubjectName, String secondSubjectGroupOrTeacherName, String secondSubjectAudience) {
        this.lessonOrder = lessonOrder;
        this.halfGrouped = halfGrouped;
        this.firstSubjectName = firstSubjectName;
        this.firstSubjectGroupOrTeacherName = firstSubjectGroupOrTeacherName;
        this.firstSubjectAudience = firstSubjectAudience;
        this.secondSubjectName = secondSubjectName;
        this.secondSubjectGroupOrTeacherName = secondSubjectGroupOrTeacherName;
        this.secondSubjectAudience = secondSubjectAudience;
    }

    public int getLessonOrder() {
        return lessonOrder;
    }

    public boolean isHalfGrouped() {
        return halfGrouped;
    }

    public String getFirstSubjectName() {
        return firstSubjectName;
    }

    public String getFirstSubjectGroupOrTeacherName() {
        return firstSubjectGroupOrTeacherName;
    }

    public String getFirstSubjectAudience() {
        return firstSubjectAudience;
    }

    public String getSecondSubjectName() {
        return secondSubjectName;
    }

    public String getSecondSubjectGroupOrTeacherName() {
        return secondSubjectGroupOrTeacherName;
    }

    public String getSecondSubjectAudience() {
        return secondSubjectAudience;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "lessonOrder=" + lessonOrder +
                ", halfGrouped=" + halfGrouped +
                ", firstSubjectName='" + firstSubjectName + '\'' +
                ", firstSubjectGroupOrTeacherName='" + firstSubjectGroupOrTeacherName + '\'' +
                ", firstSubjectAudience='" + firstSubjectAudience + '\'' +
                ", secondSubjectName='" + secondSubjectName + '\'' +
                ", secondSubjectGroupOrTeacherName='" + secondSubjectGroupOrTeacherName + '\'' +
                ", secondSubjectAudience='" + secondSubjectAudience + '\'' +
                '}';
    }
}
