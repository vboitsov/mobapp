package ua.org.rshu.fmi.mobapp.service.fmiservices.imp;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Credit;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Day;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Exam;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Group;
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
}
