package ua.org.rshu.fmi.mobapp.service.fmiservices;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;
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
import ua.org.rshu.fmi.mobapp.util.PaginationArgs;

/**
 * Created by vb on 16/11/2017.
 */

public interface FmiService {

    Call<ArrayList<News>> getNews(@NonNull PaginationArgs paginationArgs);

    Call<ArrayList<Group>> getListOfGroups(@NonNull PaginationArgs paginationArgs);

    Call<ArrayList<Teacher>> getListOfTeachers(@NonNull PaginationArgs paginationArgs);

    Call<ArrayList<Day>> getScheduleOfGroup(@NonNull long groupId, @NonNull PaginationArgs paginationArgs);

    Call<ArrayList<Day>> getScheduleOfTeacher(@NonNull long teacherId, @NonNull PaginationArgs paginationArgs);

    Call<ArrayList<Credit>> getGroupCredits(@NonNull long groupId, @NonNull PaginationArgs paginationArgs);

    Call<ArrayList<Credit>> getTeacherCredits(@NonNull long teacherId, @NonNull PaginationArgs paginationArgs);

    Call<ArrayList<Exam>> getGroupExams(@NonNull long groupId, @NonNull PaginationArgs paginationArgs);

    Call<ArrayList<Exam>> getTeacherExams(@NonNull long teacherId, @NonNull PaginationArgs paginationArgs);

    Call<EmailAuthResponse> emailAuth(@NonNull String email);

    Call<CodeAuthResponse> codeAuth(@NonNull int code);

    Call<CourseRequestForm> getCourseRequestForm(@NonNull String token);

    Call<ArrayList<Course>> getListOfCourses(@NonNull long groupId, @NonNull PaginationArgs paginationArgs);

    Call<MakeRequestResponse> makeRequest(@NonNull String token, @NonNull CourseRequestForm courseRequestForm);
}
