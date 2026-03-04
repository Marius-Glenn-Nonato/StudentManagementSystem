package com.marius.sms.backend.dao;

import com.marius.sms.backend.entities.Course;
import com.marius.sms.backend.entities.CurriculumCourse;
import com.marius.sms.backend.entities.CurriculumProgress;
import com.marius.sms.util.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CurriculumProgressDAO {
    private static final Logger LOGGER = Logger.getLogger(CurriculumProgressDAO.class.getName());

    public List<CurriculumProgress> getStudentCurriculumProgress(Integer studentId){
        List<CurriculumProgress> curriculumProgressList = new ArrayList<>();
        String SQL_GET_CURRICULUM_PROGRESS_BY_USER_ID = """
                SELECT
                    c.course_code,
                    c.course_name,
                    c.course_units,
                    cc.year_level,
                    cc.semester,
                    scp.status,
                	scp.final_grade
                FROM sms.students s
                JOIN sms.curricula cur
                    ON s.curriculum_id = cur.curricula_id
                JOIN sms.curriculum_courses cc
                    ON cur.curricula_id = cc.curriculum_id
                JOIN sms.courses c
                    ON cc.course_id = c.course_id
                LEFT JOIN sms.student_curriculum_progress scp
                    ON scp.curriculum_course_id = cc.curriculum_course_id
                    AND scp.student_id = s.student_id
                WHERE s.student_id = ?
                ORDER BY cc.year_level, cc.semester, c.course_code;
                """;

        try(
                Connection connection = DatabaseUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_CURRICULUM_PROGRESS_BY_USER_ID);
                ){
            preparedStatement.setInt(1,studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            curriculumProgressList = processResultSet(resultSet);
        }catch (SQLException e){
            DatabaseUtils.handleSQLException("CurriculumProgressDAO.getStudentCurriculumProgress", e, LOGGER);
        }
        return curriculumProgressList;
    }


    private List<CurriculumProgress> processResultSet(ResultSet resultSet) throws SQLException {
        List<CurriculumProgress> curriculumProgressList = new ArrayList<>();
        while(resultSet.next()){
            //Course
            Course course = new Course();
            course.setCourseCode(resultSet.getString("course_code"));
            course.setCourseName(resultSet.getString("course_name"));
            course.setCourseUnits(resultSet.getInt("course_units"));

            //CurriculumCourse
            CurriculumCourse curriculumCourse = new CurriculumCourse();
            curriculumCourse.setCourse(course);
            curriculumCourse.setYearLevel(resultSet.getInt("year_level"));
            curriculumCourse.setSemester(resultSet.getInt("semester"));

            //CurriculumProgress
            CurriculumProgress curriculumProgress = new CurriculumProgress();
            curriculumProgress.setCurriculum_course(curriculumCourse);
            curriculumProgress.setStatus(resultSet.getString("status"));
            curriculumProgress.setFinalGrade(resultSet.getDouble("final_grade"));

            curriculumProgressList.add(curriculumProgress);
        }
        return curriculumProgressList;
    }
}
