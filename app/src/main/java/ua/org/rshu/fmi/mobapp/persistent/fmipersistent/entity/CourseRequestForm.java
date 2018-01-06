package ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity;

import java.util.ArrayList;

public class CourseRequestForm {

    private final String email;

    private final String name;

    private final String surname;

    private final String middleName;

    private final Group group;

    private final ArrayList<Course> courses;

    private final String status;

    public CourseRequestForm(String email, String name, String surname, String middleName, Group group, ArrayList<Course> courses, String status) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.group = group;
        this.courses = courses;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Group getGroup() {
        return group;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public String getStatus() {
        return status;
    }
}
