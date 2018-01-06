package ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity;


import java.util.ArrayList;

import ua.org.rshu.fmi.mobapp.persistent.BasicEntity;

/**
 * Created by vb on 15/11/2017.
 */

public class Day implements BasicEntity {

    private final long dayId;

    private final long groupId;

    private final long teacherId;

    private final String dayName;

    private final String dayDate;

    private final String updateTime;

    private final ArrayList<Lesson> lessons;

    public Day(long dayId, long groupId, long teacherId, String dayName, String dayDate, String updateTime, ArrayList<Lesson> lessons) {
        this.dayId = dayId;
        this.groupId = groupId;
        this.teacherId = teacherId;
        this.dayName = dayName;
        this.dayDate = dayDate;
        this.updateTime = updateTime;
        this.lessons = lessons;
    }

    public long getDayId() {
        return dayId;
    }

    public long getGroupId() {
        return groupId;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public String getDayName() {
        return dayName;
    }

    public String getDayDate() {
        return dayDate;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }
}
