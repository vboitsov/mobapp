package ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity;

import java.util.ArrayList;

public class CourseRequestForm {

    private String email;

    private String phoneNumber;

    private String name;

    private String surname;

    private String middleName;

    private Group group;

    private ArrayList<Course> courses;

    private String status;

    public CourseRequestForm() {
    }

    public CourseRequestForm(String email, String phoneNumber, String name, String surname, String middleName, Group group, ArrayList<Course> courses, String status) {
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CourseRequestForm{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", group=" + group +
                ", courses=" + courses +
                ", status='" + status + '\'' +
                '}';
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
