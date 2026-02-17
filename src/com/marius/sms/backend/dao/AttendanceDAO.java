package com.marius.sms.backend.dao;

import com.marius.sms.backend.dto.StudentAttendanceDTO;
import com.marius.sms.backend.entities.Attendance;
import com.marius.sms.backend.entities.Course;
import com.marius.sms.backend.entities.CourseOffering;
import com.marius.sms.util.DatabaseUtils;
import com.marius.sms.util.DateUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AttendanceDAO {
    private static final Logger LOGGER = Logger.getLogger(AttendanceDAO.class.getName());


    public List<StudentAttendanceDTO> getStudentAttendance(Integer studentId){
        List<StudentAttendanceDTO> studentAttendanceDTOS = new ArrayList<>();
        String GET_STUDENT_ATTENDANCE_QUERY = """
                    SELECT
                    c.course_code, -- from courses
                    c.course_name, -- from courses
                    co.section_code, -- from course_offerings
                    a.attendance_date, --from attendances
                    a.status --from attendances 
                    FROM sms.enrollments AS e
                    JOIN sms.course_offerings AS co ON e.course_offering_id = co.course_offering_id
                    JOIN sms.courses AS c ON co.course_id = c.course_id
                    JOIN sms.attendances AS a ON a.enrollment_id = e.enrollment_id
                    JOIN sms.terms AS t ON co.term_id = t.term_id
                    WHERE e.student_id=?
                    AND t.is_active=true -- only attendance in the current term
                    AND a.status IN ('ABSENT', 'LATE', 'PRESENT')
                    ORDER BY c.course_code, a.attendance_date
                    """;

        try(
                Connection connection  = DatabaseUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_ATTENDANCE_QUERY);
                ){
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            studentAttendanceDTOS = processResultSetAttendanceDTO(resultSet);
        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("AttendanceDAO.getStudentAttendance()", e,LOGGER);
        }
        return studentAttendanceDTOS;
    }

    private List<StudentAttendanceDTO> processResultSetAttendanceDTO(ResultSet resultSet) throws SQLException {
        List<StudentAttendanceDTO> studentAttendanceDTOS = new ArrayList<>();
        while(resultSet.next()){
            //Course
            Course course = new Course();
            course.setCourseCode(resultSet.getString("course_code"));
            course.setCourseName(resultSet.getString("course_name"));

            //CourseOffering
            CourseOffering courseOffering = new CourseOffering();
            courseOffering.setSectionCode(resultSet.getString("section_code"));
            courseOffering.setCourse(course);

            //Attendance
            Attendance attendance = new Attendance();
            attendance.setAttendanceDate(DateUtils.toLocalDate(resultSet.getDate("attendance_date")));
            attendance.setStatus(resultSet.getString("status"));

            //StudentAttendanceDTO
            StudentAttendanceDTO studentAttendanceDTO = new StudentAttendanceDTO();
            studentAttendanceDTO.setCourseOffering(courseOffering);
            studentAttendanceDTO.setAttendance(attendance);
            studentAttendanceDTOS.add(studentAttendanceDTO);
        }
        return studentAttendanceDTOS;

    }

}
