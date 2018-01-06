package ua.org.rshu.fmi.mobapp.persistent.fmipersistent.realmentity;


import io.realm.RealmObject;

/**
 * Created by vb on 15/11/2017.
 */

public class DayRealmObject extends RealmObject {

    private String name;

    private String date;

    private LessonRealmObject lesson1;

    private LessonRealmObject lesson2;

    private LessonRealmObject lesson3;

    private LessonRealmObject lesson4;

    private LessonRealmObject lesson5;

//    public DayRealmObject(String name, String date, LessonRealmObject lesson1, LessonRealmObject lesson2, LessonRealmObject lesson3, LessonRealmObject lesson4, LessonRealmObject lesson5) {
//        this.name = name;
//        this.date = date;
//        this.lesson1 = lesson1;
//        this.lesson2 = lesson2;
//        this.lesson3 = lesson3;
//        this.lesson4 = lesson4;
//        this.lesson5 = lesson5;
//    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public LessonRealmObject getLesson1() {
        return lesson1;
    }

    public LessonRealmObject getLesson2() {
        return lesson2;
    }

    public LessonRealmObject getLesson3() {
        return lesson3;
    }

    public LessonRealmObject getLesson4() {
        return lesson4;
    }

    public LessonRealmObject getLesson5() {
        return lesson5;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLesson1(LessonRealmObject lesson1) {
        this.lesson1 = lesson1;
    }

    public void setLesson2(LessonRealmObject lesson2) {
        this.lesson2 = lesson2;
    }

    public void setLesson3(LessonRealmObject lesson3) {
        this.lesson3 = lesson3;
    }

    public void setLesson4(LessonRealmObject lesson4) {
        this.lesson4 = lesson4;
    }

    public void setLesson5(LessonRealmObject lesson5) {
        this.lesson5 = lesson5;
    }
}
