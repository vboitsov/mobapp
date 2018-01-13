package ua.org.rshu.fmi.mobapp.persistent.fmipersistent.retrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
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

/**
 * Created by vb on 16/11/2017.
 */

public interface FmiApi {

    @GET("news")
    Call<ArrayList<News>> getNews(@Query("offset") int offset, @Query("limit") int limit);

    @GET("groups")
    Call<ArrayList<Group>> getListOfGroups(@Query("offset") int offset, @Query("limit") int limit);

    @GET("teachers")
    Call<ArrayList<Teacher>> getListOfTeachers(@Query("offset") int offset, @Query("limit") int limit);

    @GET("group_schedule")
    Call<ArrayList<Day>> getScheduleOfGroup(@Query("group_id") long groupId, @Query("offset") int offset, @Query("limit") int limit);

    @GET("teacher_schedule")
    Call<ArrayList<Day>> getScheduleOfTeacher(@Query("teacher_id") long teacherId, @Query("offset") int offset, @Query("limit") int limit);

    @GET("group_credits")
    Call<ArrayList<Credit>> getGroupCredits(@Query("group_id") long groupId, @Query("offset") int offset, @Query("limit") int limit);

    @GET("teacher_credits")
    Call<ArrayList<Credit>> getTeacherCredits(@Query("teacher_id") long teacherId, @Query("offset") int offset, @Query("limit") int limit);

    @GET("group_exams")
    Call<ArrayList<Exam>> getGroupExams(@Query("group_id") long groupId, @Query("offset") int offset, @Query("limit") int limit);

    @GET("teacher_exams")
    Call<ArrayList<Exam>> getTeacherExams(@Query("teacher_id") long teacherId, @Query("offset") int offset, @Query("limit") int limit);



    //COURSES
    @GET("mail_auth")
    Call<EmailAuthResponse> emailAuth(@Query("email") String email);

    @GET("code_auth")
    Call<CodeAuthResponse> codeAuth(@Query("code") int code);

    @GET("course_status")
    Call<CourseRequestForm> getCourseRequestForm(@Query("token") String token);

    @GET("course_list")
    Call<ArrayList<Course>> getListOfCourses(@Query("group_id") long groupId, @Query("offset") int offset, @Query("limit") int limit);

    @POST("make_request")
    Call<MakeRequestResponse> makeRequest(@Query("token") String token, @Body CourseRequestForm courseRequestForm);

}
