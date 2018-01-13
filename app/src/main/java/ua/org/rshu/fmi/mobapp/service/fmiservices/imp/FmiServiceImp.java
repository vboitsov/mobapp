package ua.org.rshu.fmi.mobapp.service.fmiservices.imp;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.CodeAuthResponse;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Course;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.CourseRequestForm;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Credit;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Day;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.EmailAuthResponse;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Exam;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Group;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.MakeRequestResponse;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.News;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Teacher;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.repository.FmiRepository;
import ua.org.rshu.fmi.mobapp.service.fmiservices.FmiService;
import ua.org.rshu.fmi.mobapp.util.PaginationArgs;

/**
 * Created by vb on 16/11/2017.
 */

public class FmiServiceImp implements FmiService {

    private final FmiRepository fmiRepository;

    @Inject
    public FmiServiceImp(FmiRepository fmiRepository) {
        this.fmiRepository = fmiRepository;
    }

    @Override
    public Call<ArrayList<News>> getNews(PaginationArgs paginationArgs) {
        return fmiRepository.getNews(paginationArgs);
    }

    @Override
    public Call<ArrayList<Group>> getListOfGroups(PaginationArgs paginationArgs) {
        return fmiRepository.getListOfGroups(paginationArgs);
    }

    @Override
    public Call<ArrayList<Teacher>> getListOfTeachers(PaginationArgs paginationArgs) {
        return fmiRepository.getListOfTeachers(paginationArgs);
    }

    @Override
    public Call<ArrayList<Day>> getScheduleOfGroup(long groupId, PaginationArgs paginationArgs) {
        return fmiRepository.getScheduleOfGroup(groupId, paginationArgs);
    }

    @Override
    public Call<ArrayList<Day>> getScheduleOfTeacher(long teacherId, PaginationArgs paginationArgs) {
        return fmiRepository.getScheduleOfTeacher(teacherId, paginationArgs);
    }

    @Override
    public Call<ArrayList<Credit>> getGroupCredits(long groupId, PaginationArgs paginationArgs) {
        return fmiRepository.getGroupCredits(groupId, paginationArgs);
    }

    @Override
    public Call<ArrayList<Credit>> getTeacherCredits(long teacherId, PaginationArgs paginationArgs) {
        return fmiRepository.getTeacherCredits(teacherId, paginationArgs);
    }

    @Override
    public Call<ArrayList<Exam>> getGroupExams(long groupId, PaginationArgs paginationArgs) {
        return fmiRepository.getGroupExams(groupId, paginationArgs);
    }

    @Override
    public Call<ArrayList<Exam>> getTeacherExams(long teacherId, PaginationArgs paginationArgs) {
        return fmiRepository.getTeacherExams(teacherId, paginationArgs);
    }

    @Override
    public Call<EmailAuthResponse> emailAuth(String email) {
        return fmiRepository.emailAuth(email);
    }

    @Override
    public Call<CodeAuthResponse> codeAuth(int code) {
        return fmiRepository.codeAuth(code);
    }

    @Override
    public Call<CourseRequestForm> getCourseRequestForm(String token) {
        return fmiRepository.getCourseRequestForm(token);
    }

    @Override
    public Call<ArrayList<Course>> getListOfCourses(long groupId, PaginationArgs paginationArgs) {
        return fmiRepository.getListOfCourses(groupId, paginationArgs);
    }

    @Override
    public Call<MakeRequestResponse> makeRequest(String token, CourseRequestForm courseRequestForm) {
        return fmiRepository.makeRequest(token, courseRequestForm);
    }

}
