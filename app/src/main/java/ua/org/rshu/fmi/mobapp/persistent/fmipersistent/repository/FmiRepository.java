package ua.org.rshu.fmi.mobapp.persistent.fmipersistent.repository;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;
import retrofit2.Call;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Credit;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Day;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Exam;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Group;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.News;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Teacher;
import ua.org.rshu.fmi.mobapp.util.PaginationArgs;

/**
 * Created by vb on 16/11/2017.
 */

public interface FmiRepository {

    Call<ArrayList<News>> getNews(@NonNull PaginationArgs paginationArgs);

    Call<ArrayList<Group>> getListOfGroups(@NonNull PaginationArgs paginationArgs);

    Call<ArrayList<Teacher>> getListOfTeachers(@NonNull PaginationArgs paginationArgs);

    Call<ArrayList<Day>> getScheduleOfGroup(@NonNull long groupId, @NonNull PaginationArgs paginationArgs);

    Call<ArrayList<Day>> getScheduleOfTeacher(@NonNull long teacherId, @NonNull PaginationArgs paginationArgs);

    Call<ArrayList<Credit>> getGroupCredits(@NonNull long groupId, @NonNull PaginationArgs paginationArgs);

    Call<ArrayList<Credit>> getTeacherCredits(@NonNull long teacherId, @NonNull PaginationArgs paginationArgs);

    Call<ArrayList<Exam>> getGroupExams(@NonNull long groupId, @NonNull PaginationArgs paginationArgs);

    Call<ArrayList<Exam>> getTeacherExams(@NonNull long teacherId, @NonNull PaginationArgs paginationArgs);
}
