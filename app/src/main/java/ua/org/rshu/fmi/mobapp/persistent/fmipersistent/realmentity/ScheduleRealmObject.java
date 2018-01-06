package ua.org.rshu.fmi.mobapp.persistent.fmipersistent.realmentity;


import io.realm.RealmObject;

/**
 * Created by vb on 15/11/2017.
 */

public class ScheduleRealmObject extends RealmObject {

    private long groupOrTeacherId;

    private String updateDate;

    private DayRealmObject mondayDay;

    private DayRealmObject tuesdayDay;

    private DayRealmObject wednesDay;

    private DayRealmObject thursdayDay;

    private DayRealmObject fridayDay;

    private DayRealmObject saturdayDay;

//    public ScheduleRealmObject(long groupOrTeacherId, String updateDate, DayRealmObject mondayDay, DayRealmObject tuesdayDay, DayRealmObject wednesDay, DayRealmObject thursdayDay, DayRealmObject fridayDay, DayRealmObject saturdayDay) {
//        this.groupOrTeacherId = groupOrTeacherId;
//        this.updateDate = updateDate;
//        this.mondayDay = mondayDay;
//        this.tuesdayDay = tuesdayDay;
//        this.wednesDay = wednesDay;
//        this.thursdayDay = thursdayDay;
//        this.fridayDay = fridayDay;
//        this.saturdayDay = saturdayDay;
//    }

    public long getGroupOrTeacherId() {
        return groupOrTeacherId;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public DayRealmObject getMondayDay() {
        return mondayDay;
    }

    public DayRealmObject getTuesdayDay() {
        return tuesdayDay;
    }

    public DayRealmObject getWednesDay() {
        return wednesDay;
    }

    public DayRealmObject getThursdayDay() {
        return thursdayDay;
    }

    public DayRealmObject getFridayDay() {
        return fridayDay;
    }

    public DayRealmObject getSaturdayDay() {
        return saturdayDay;
    }

    public void setGroupOrTeacherId(long groupOrTeacherId) {
        this.groupOrTeacherId = groupOrTeacherId;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public void setMondayDay(DayRealmObject mondayDay) {
        this.mondayDay = mondayDay;
    }

    public void setTuesdayDay(DayRealmObject tuesdayDay) {
        this.tuesdayDay = tuesdayDay;
    }

    public void setWednesDay(DayRealmObject wednesDay) {
        this.wednesDay = wednesDay;
    }

    public void setThursdayDay(DayRealmObject thursdayDay) {
        this.thursdayDay = thursdayDay;
    }

    public void setFridayDay(DayRealmObject fridayDay) {
        this.fridayDay = fridayDay;
    }

    public void setSaturdayDay(DayRealmObject saturdayDay) {
        this.saturdayDay = saturdayDay;
    }
}
