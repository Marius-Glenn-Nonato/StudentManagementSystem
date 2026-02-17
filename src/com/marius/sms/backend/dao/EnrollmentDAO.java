package com.marius.sms.backend.dao;

import com.marius.sms.backend.entities.*;
import com.marius.sms.util.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EnrollmentDAO {
    private static final Logger LOGGER = Logger.getLogger(EnrollmentDAO.class.getName());

    private static final String TABLE_NAME = "sms.enrollments";

    private static final String SQL_GET_ALL_ENROLLMENTS_OF_STUDENT = "SELECT e.enrollment_id, e.student_id, e.grade, e.course_id  FROM "+TABLE_NAME +" e WHERE student_id=?";
    private static final String SQL_INSERT_ENROLLMENT_QUERY = "INSERT INTO "+TABLE_NAME+" (student_id, grade, course_id) VALUES (?,?,?)";

    public List<Enrollment> getAllEnrollmentsOfStudent(Integer student_id) {
        List<Enrollment> enrollments = new ArrayList<>();
        try(
                Connection connection = DatabaseUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_ENROLLMENTS_OF_STUDENT)
        ){
            preparedStatement.setInt(1, student_id);
            try(ResultSet resultSet = preparedStatement.executeQuery();){
                enrollments = processResultSet(resultSet);
            }
        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("EnrollmentDAO.getAllEnrollmentsOfStudent", e, LOGGER);
        }
        return enrollments;
    }
    public Enrollment create(Enrollment entity){
        return null;
    }


    public List<Enrollment> getEnrollmentsByStudent(int studentId) {
        List<Enrollment> enrollments = new ArrayList<>();
        String query = """
            SELECT 
                e.enrollment_id,
                e.status AS enrollment_status,
                e.final_grade,
                co.course_offering_id,
                co.section_code,
                c.course_id,
                c.course_code,
                c.course_name,
                c.course_units,
                t.teacher_id,
                t.first_name,
                t.last_name,
                tr.term_id,
                tr.term_name,
                tr.start_date,
                tr.end_date
            FROM sms.enrollments e
            JOIN sms.course_offerings co ON e.course_offering_id = co.course_offering_id 
            JOIN sms.courses c ON co.course_id = c.course_id
            JOIN sms.teachers t ON co.teacher_id = t.teacher_id
            JOIN sms.terms tr ON co.term_id = tr.term_id
            WHERE e.student_id = ?
            ORDER BY tr.start_date DESC, co.section_code
        """;

        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            enrollments = processResultSetEnrollments(rs);

        } catch (SQLException ex) {
            DatabaseUtils.handleSQLException("EnrollmentDAO.getAllEnrollmentsByStudent", ex, LOGGER);
        }

        return enrollments;
    }
    private List<Enrollment> processResultSet(ResultSet rs) throws SQLException {
       return null;
    }

    /** Private helper to process ResultSet into Enrollment entities */
    private List<Enrollment> processResultSetEnrollments(ResultSet rs) throws SQLException {
        List<Enrollment> enrollments = new ArrayList<>();
        while (rs.next()) {
            Enrollment e = new Enrollment();

            // Enrollment
            e.setEnrollmentId(rs.getInt("enrollment_id"));
            e.setStatus(rs.getString("enrollment_status"));
            e.setFinalGrade(rs.getDouble("final_grade"));

            // CourseOffering
            CourseOffering co = new CourseOffering();
            co.setCourse_offering_id(rs.getInt("course_offering_id"));
            co.setSectionCode(rs.getString("section_code"));

            // Course
            Course course = new Course();
            course.setCourseId(rs.getInt("course_id"));
            course.setCourseCode(rs.getString("course_code"));
            course.setCourseName(rs.getString("course_name"));
            course.setCourseUnits(rs.getInt("course_units"));
            co.setCourse(course);

            // Teacher
            Teacher teacher = new Teacher();
            teacher.setTeacher_id(rs.getInt("teacher_id"));
            teacher.setFirst_name(rs.getString("first_name"));
            teacher.setLast_name(rs.getString("last_name"));
            co.setTeacher(teacher);

            // Term
            Term term = new Term();
            term.setTermId(rs.getInt("term_id"));
            term.setTermName(rs.getString("term_name"));
            term.setStartDate(rs.getDate("start_date").toLocalDate());
            term.setEndDate(rs.getDate("end_date").toLocalDate());
            co.setTerm(term);

            e.setCourse_offering(co);
            enrollments.add(e);
        }
        return enrollments;
    }
}
