package ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity;


import ua.org.rshu.fmi.mobapp.persistent.BasicEntity;

/**
 * Created by vb on 15/11/2017.
 */

public class Lesson implements BasicEntity {

    private final int lesson_order;

    private final boolean isHalfGrouped;

    private final String first_subject_name;

    private final String first_subject_group_or_teacher_name;

    private final String first_subject_audience;

    private final String second_subject_name;

    private final String second_subject_group_or_teacher_name;

    private final String second_subject_audience;

    public Lesson(int lesson_order, boolean isHalfGrouped, String first_subject_name, String first_subject_group_or_teacher_name, String first_subject_audience, String second_subject_name, String second_subject_group_or_teacher_name, String second_subject_audience) {
        this.lesson_order = lesson_order;
        this.isHalfGrouped = isHalfGrouped;
        this.first_subject_name = first_subject_name;
        this.first_subject_group_or_teacher_name = first_subject_group_or_teacher_name;
        this.first_subject_audience = first_subject_audience;
        this.second_subject_name = second_subject_name;
        this.second_subject_group_or_teacher_name = second_subject_group_or_teacher_name;
        this.second_subject_audience = second_subject_audience;
    }

    public int getLesson_order() {
        return lesson_order;
    }

    public boolean isHalfGrouped() {
        return isHalfGrouped;
    }

    public String getFirst_subject_name() {
        return first_subject_name;
    }

    public String getFirst_subject_group_or_teacher_name() {
        return first_subject_group_or_teacher_name;
    }

    public String getFirst_subject_audience() {
        return first_subject_audience;
    }

    public String getSecond_subject_name() {
        return second_subject_name;
    }

    public String getSecond_subject_group_or_teacher_name() {
        return second_subject_group_or_teacher_name;
    }

    public String getSecond_subject_audience() {
        return second_subject_audience;
    }
}
