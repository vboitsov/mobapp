package ua.org.rshu.fmi.mobapp.persistent.fmipersistent.repository.impl;

import java.util.ArrayList;

import retrofit2.Call;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Credit;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Day;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Exam;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Group;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.News;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Teacher;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.repository.FmiRepository;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.retrofit.ApiContainer;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.retrofit.FmiApi;
import ua.org.rshu.fmi.mobapp.util.PaginationArgs;

/**
 * Created by vb on 16/11/2017.
 */

public class FmiRepositoryImpl implements FmiRepository {

    private FmiApi fmiApi = ApiContainer.getFmiServiceInstance();

    @Override
    public Call<ArrayList<News>> getNews(PaginationArgs paginationArgs) {
        return fmiApi.getNews(paginationArgs.offset, paginationArgs.limit);
    }

    @Override
    public Call<ArrayList<Group>> getListOfGroups(PaginationArgs paginationArgs) {
        return fmiApi.getListOfGroups(paginationArgs.offset, paginationArgs.limit);
    }

    @Override
    public Call<ArrayList<Teacher>> getListOfTeachers(PaginationArgs paginationArgs) {
        return fmiApi.getListOfTeachers(paginationArgs.offset, paginationArgs.limit);
    }

    @Override
    public Call<ArrayList<Day>> getScheduleOfGroup(long groupId, PaginationArgs paginationArgs) {
        return fmiApi.getScheduleOfGroup(groupId, paginationArgs.offset, paginationArgs.limit);
    }

    @Override
    public Call<ArrayList<Day>> getScheduleOfTeacher(long teacherId, PaginationArgs paginationArgs) {
        return fmiApi.getScheduleOfTeacher(teacherId, paginationArgs.offset, paginationArgs.limit);
    }

    @Override
    public Call<ArrayList<Credit>> getGroupCredits(long groupId, PaginationArgs paginationArgs) {
        return fmiApi.getGroupCredits(groupId, paginationArgs.offset, paginationArgs.limit);
    }

    @Override
    public Call<ArrayList<Credit>> getTeacherCredits(long teacherId, PaginationArgs paginationArgs) {
        return fmiApi.getTeacherCredits(teacherId, paginationArgs.offset, paginationArgs.limit);
    }

    @Override
    public Call<ArrayList<Exam>> getGroupExams(long groupId, PaginationArgs paginationArgs) {
        return fmiApi.getGroupExams(groupId, paginationArgs.offset, paginationArgs.limit);
    }

    @Override
    public Call<ArrayList<Exam>> getTeacherExams(long teacherId, PaginationArgs paginationArgs) {
        return fmiApi.getTeacherExams(teacherId, paginationArgs.offset, paginationArgs.limit);
    }
}
