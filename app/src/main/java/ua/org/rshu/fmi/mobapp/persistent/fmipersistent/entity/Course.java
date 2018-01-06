package ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity;

import ua.org.rshu.fmi.mobapp.persistent.BasicEntity;

/**
 * Created by vb on 30/11/2017.
 */

public class Course implements BasicEntity {

    private final long id;

    private final String courseName;

    private final String courseDescription;

    public Course(long id, String courseName, String courseDescription) {
        this.id = id;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
    }

    public long getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }
}
