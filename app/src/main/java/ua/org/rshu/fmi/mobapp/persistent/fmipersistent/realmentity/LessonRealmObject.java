package ua.org.rshu.fmi.mobapp.persistent.fmipersistent.realmentity;


import io.realm.RealmObject;

/**
 * Created by vb on 15/11/2017.
 */

public class LessonRealmObject extends RealmObject {

    private String lessonName1;

    private String teacherOrGroupName1;

    private String lessonName2;

    private String teacherName2;

//    public LessonRealmObject(String lessonName1, String teacherOrGroupName1, String lessonName2, String teacherName2) {
//        this.lessonName1 = lessonName1;
//        this.teacherOrGroupName1 = teacherOrGroupName1;
//        this.lessonName2 = lessonName2;
//        this.teacherName2 = teacherName2;
//    }

    public String getLessonName1() {
        return lessonName1;
    }

    public void setLessonName1(String lessonName1) {
        this.lessonName1 = lessonName1;
    }

    public String getTeacherOrGroupName1() {
        return teacherOrGroupName1;
    }

    public void setTeacherOrGroupName1(String teacherOrGroupName1) {
        this.teacherOrGroupName1 = teacherOrGroupName1;
    }

    public String getLessonName2() {
        return lessonName2;
    }

    public void setLessonName2(String lessonName2) {
        this.lessonName2 = lessonName2;
    }

    public String getTeacherName2() {
        return teacherName2;
    }

    public void setTeacherName2(String teacherName2) {
        this.teacherName2 = teacherName2;
    }
}
