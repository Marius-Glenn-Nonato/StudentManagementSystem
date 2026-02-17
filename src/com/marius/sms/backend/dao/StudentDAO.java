package com.marius.sms.backend.dao;

import com.marius.sms.backend.dto.StudentCourseDTO;
import com.marius.sms.backend.entities.*;
import com.marius.sms.util.DatabaseUtils;
import com.marius.sms.util.DateUtils;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.function.BiFunction;
import java.util.logging.Logger;

public class StudentDAO implements DAO<Student, Integer> {
    private static final Logger LOGGER = Logger.getLogger(StudentDAO.class.getName());
    private static final String TABLE_NAME = "sms.students";

    //Get all Students using student_id
    private static final String SQL_GET_STUDENT_BY_STUDENT_ID_QUERY = "SELECT s.student_id, s.first_name, s.middle_name,s.last_name,s.date_of_birth, s.program_id, s.curriculum_id, s.year_level, s.is_irregular, s.is_active, s.graduated_at, s.user_id, u.username, u.user_email, u.password_hash, u.role_id, u.user_created_at FROM "+TABLE_NAME+" s JOIN sms.users u ON s.user_id = u.user_id WHERE s.student_id = ?";
    //Get all Students using user_email
    private static final String SQL_GET_STUDENT_BY_EMAIL_QUERY = "SELECT s.student_id, s.first_name, s.middle_name,s.last_name,s.date_of_birth, s.program_id, s.curriculum_id, s.year_level, s.is_irregular, s.is_active, s.graduated_at, s.user_id, u.username, u.user_email, u.password_hash, u.role_id, u.user_created_at FROM " + TABLE_NAME + " s JOIN sms.users u ON s.user_id = u.user_id WHERE u.user_email = ?";

    private static final String SQL_UPDATE_STUDENT_QUERY = "UPDATE "+TABLE_NAME+" SET first_name=?, last_name=?, user_email=?, date_of_birth=? WHERE student_id=?";
    private static final String SQL_UPDATE_STUDENT_EMAIL_QUERY = "UPDATE "+TABLE_NAME+" SET user_email=? WHERE student_id=?";
    private static final String SQL_UPDATE_STUDENT_FIRST_NAME_QUERY = "UPDATE "+TABLE_NAME+" SET first_name=? WHERE student_id=?";
    private static final String SQL_UPDATE_STUDENT_LAST_NAME_QUERY = "UPDATE "+TABLE_NAME+" SET last_name=? WHERE student_id=?";
    private static final String SQL_UPDATE_STUDENT_DATE_OF_BIRTH_QUERY = "UPDATE "+TABLE_NAME+" SET date_of_birth=? WHERE student_id=?";

    private static final String SQL_DELETE_STUDENT_QUERY = "DELETE FROM "+TABLE_NAME+" WHERE student_id=?";


    @Override
    public List<Student> getAll() {
        //Get all Students
        String SQL_GET_ALL_STUDENTS_QUERY = "SELECT s.student_id, s.first_name,s.middle_name,s.last_name,s.date_of_birth, s.program_id, s.curriculum_id, s.year_level, s.is_irregular, s.is_active, s.graduated_at, s.user_id, u.username, u.user_email, u.password_hash, u.role_id, u.user_created_at FROM "+TABLE_NAME+" s JOIN sms.users u ON s.user_id = u.user_id";
        List<Student> students = new ArrayList<>();
        try(
                Connection connection = DatabaseUtils.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_STUDENTS_QUERY);
                ){
            students = processResultSet(resultSet);
        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("StudentDAO.getAll()",e,LOGGER);
        }
        DatabaseUtils.printSQLConnectionClose("StudentDAO.getAll()",StudentDAO.class);
        return students;
    }

    //----------------------INSERT-----------------------------
    @Override
    public Student create(Student entity) {
//        String SQL_INSERT_STUDENT_QUERY = "INSERT INTO "+TABLE_NAME+" (user_id, first_name, middle_name, last_name, date_of_birth, program_id, curriculum_id, year_level, is_irregular, is_active, graduated_at) VALUES (?,?,?,?,?)";
//
//        Connection connection = null;
//        try{
//            connection = DatabaseUtils.getConnection();
//            connection.setAutoCommit(false);
//            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_STUDENT_QUERY, Statement.RETURN_GENERATED_KEYS)){
//                preparedStatement.setInt(1, entity.getUser_id());
//                preparedStatement.setString(2, entity.getFirst_name());
//                preparedStatement.setString(3, entity.getMiddle_name());
//                preparedStatement.setString(4, entity.getLast_name());
//                preparedStatement.setDate(5, DateUtils.toSqlDate(entity.getDate_of_birth()));
//                preparedStatement.setInt(6, entity.getProgram_id());
//                preparedStatement.setInt(7, entity.getCurriculum_id());
//                preparedStatement.setInt(8, entity.getYear_level());
//                preparedStatement.setBoolean(9, entity.isIs_irregular());
//                preparedStatement.setBoolean(10, entity.isIs_active());
//                preparedStatement.setDate(11, DateUtils.toSqlDate(entity.getGraduated_at()));
//                int affectedRows = preparedStatement.executeUpdate();
//                if (affectedRows == 0) {
//                    throw new SQLException("Failed to insert new student.");
//                }
//                //Get generated key
//                try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
//                    if(generatedKeys.next()) {
//                        entity.setStudent_id(generatedKeys.getInt(1));
//                    }
//                }
//                connection.commit();
//                DatabaseUtils.printNewEntityCreated(entity, entity.getStudent_id());
//                return entity;
//            }
//        }catch (SQLException e){
//            if(connection != null){
//                try{
//                    connection.rollback();
//                } catch (SQLException ex) {
//                    DatabaseUtils.handleSQLException("StudentDAO.create().rollback",ex,LOGGER);
//                }
//            }
//            DatabaseUtils.handleSQLException("StudentDAO.create().null",e,LOGGER);
//            return null;
//        }finally {
//            if(connection != null){
//                try{
//                    DatabaseUtils.printSQLConnectionClose("StudentDAO.create()",StudentDAO.class);
//                    connection.setAutoCommit(true);
//                    connection.close();
//                } catch (SQLException ez) {
//                    DatabaseUtils.handleSQLException("StudentDAO.create().finally.close()",ez,LOGGER);
//                }
//            }
//        }
        return null;
    }


    //----------------------SELECT-----------------------------
    //STUDENT GET BY STUDENT_ID
    @Override
    public Optional<Student> getOne(Integer studentId) {
//        try(
//                Connection connection = DatabaseUtils.getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_STUDENT_BY_STUDENT_ID_QUERY);
//                ) {
//            preparedStatement.setInt(1, studentId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            List<Student> students = processResultSet(resultSet);
//            if(!students.isEmpty()) {
//                System.out.println("Student found with id " + studentId);
//                DatabaseUtils.printSQLConnectionClose("getOne()",StudentDAO.class);
//                return Optional.of(students.get(0));
//            }
//        } catch (SQLException e) {
//            DatabaseUtils.handleSQLException("StudentDAO.getOne() (BY ID)",e,LOGGER);
//        }
//        DatabaseUtils.printSQLConnectionClose("StudentDAO.getOne().null",StudentDAO.class);
//        return Optional.empty();
        return null;
    }

    /**
     * An important method that can get the students info if the user gets the user info first (Inheritance)
     * @param userId
     * @return
     */
    public Optional<Student> getStudentByUserId(Integer userId) {
        //Get all Students using user_id (for inheritance)
        String SQL_GET_STUDENT_BY_USER_ID_QUERY =
                "SELECT s.student_id, s.first_name, s.middle_name,s.last_name,s.date_of_birth, s.program_id, s.curriculum_id, s.year_level, s.is_irregular, s.is_active, s.graduated_at, s.user_id, " +
                        "u.username, u.user_email, u.password_hash, u.role_id, u.user_created_at " +
                        "FROM "+TABLE_NAME+" s JOIN sms.users u ON s.user_id = u.user_id WHERE s.user_id = ?";
        try(
                Connection connection = DatabaseUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_STUDENT_BY_USER_ID_QUERY);
                ){
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Student> students = processResultSet(resultSet);
            if(!students.isEmpty()) {
                System.out.println("Student found with user id " + userId);
                DatabaseUtils.printSQLConnectionClose("StudentDAO.getStudentByUserId()",StudentDAO.class);
                return Optional.of(students.get(0));
            }
        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("StudentDAO.getStudentByUserId()",e,LOGGER);
        }
        DatabaseUtils.printSQLConnectionClose("StudentDAO.getStudentByUserId().null",StudentDAO.class);
        return Optional.empty();
    }

    /**
     * I need
     * enrollmentId from Enrollments to connect to CourseOffering
     * courseCode from Course
     * courseName from Course
     * sectionCode from CourseOffering
     * teacherName from CourseOffering
     * Term
     * dayOfWeek from OfferingSchedule where
     * startTime from OfferingSchedule
     * endTime from OfferingSchedule
     * room from OfferingSchedule
     */


    /**
     * This is defecated, done in OfferingScheduleDAO and EnrollmentDAO
     */
//    public List<StudentCourseDTO> getCoursesOfStudent(Integer studentId) {
//        List<StudentCourseDTO> courses = new ArrayList<>();
//        Map<Integer, StudentCourseDTO> offeringMap = new HashMap<>();
//
//        String courseQuery = """
//        SELECT
//            e.status AS enrollment_status,
//            e.final_grade,
//            co.course_offering_id,
//            co.section_code,
//            c.course_code,
//            c.course_name,
//            c.course_units,
//            t.first_name || ' ' || t.last_name AS teacher_name,
//            tr.start_date,
//            tr.end_date
//        FROM sms.enrollments e
//        JOIN sms.course_offerings co ON e.course_offering_id = co.course_offering_id
//        JOIN sms.courses c ON co.course_id = c.course_id
//        JOIN sms.teachers t ON co.teacher_id = t.teacher_id
//        JOIN sms.terms tr ON co.term_id = tr.term_id
//        WHERE e.student_id = ?
//        ORDER BY tr.start_date DESC, co.section_code
//    """;
//
//        String scheduleQuery = """
//        SELECT
//            os.course_offering_id,
//            os.offering_schedule_id,
//            os.day_of_week,
//            os.start_time,
//            os.end_time,
//            os.room
//        FROM sms.offering_schedules os
//        WHERE os.course_offering_id IN (%s)
//        ORDER BY os.course_offering_id,
//                 CASE
//                   WHEN os.day_of_week = 'MON' THEN 1
//                   WHEN os.day_of_week = 'TUE' THEN 2
//                   WHEN os.day_of_week = 'WED' THEN 3
//                   WHEN os.day_of_week = 'THU' THEN 4
//                   WHEN os.day_of_week = 'FRI' THEN 5
//                   WHEN os.day_of_week = 'SAT' THEN 6
//                   WHEN os.day_of_week = 'SUN' THEN 7
//                 END
//    """;
//
//        try (Connection conn = DatabaseUtils.getConnection();
//             PreparedStatement ps = conn.prepareStatement(courseQuery)) {
//
//            ps.setInt(1, studentId);
//            ResultSet rs = ps.executeQuery();
//
//            // Process ResultSet using helper
//            processResultSetCoursesOfStudent(rs, courses, offeringMap);
//
//            if (!offeringMap.isEmpty()) {
//                // Build IN clause
//                String inClause = offeringMap.keySet().toString()
//                        .replace("[", "")
//                        .replace("]", "");
//                String finalScheduleQuery = String.format(scheduleQuery, inClause);
//
//                try (PreparedStatement ps2 = conn.prepareStatement(finalScheduleQuery)) {
//                    ResultSet rs2 = ps2.executeQuery();
//                    processResultSetSchedules(rs2, offeringMap);
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Failed to fetch student courses", e);
//        }
//
//        return courses;
//    }

    //STUDENT GET BY STUDENT user_email
    public Optional<Student> getStudentByEmail(String user_email) {
//        try(
//                Connection connection = DatabaseUtils.getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_STUDENT_BY_EMAIL_QUERY);
//        ){
//            preparedStatement.setString(1, user_email);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            List<Student> students = processResultSet(resultSet);
//            if(!students.isEmpty()) {
//                /*
//                TODO: DO DatabaseUtils.foundEntity
//                 */
//                System.out.println("Student found with student user_email " + user_email);
//                DatabaseUtils.printSQLConnectionClose("StudentDAO.getStudentByEmail()",StudentDAO.class);
//                return Optional.of(students.get(0));
//            }
//        } catch (SQLException e) {
//            DatabaseUtils.handleSQLException("StudentDAO.getStudentByEmail()",e,LOGGER);
//        }
//        DatabaseUtils.printSQLConnectionClose("StudentDAO.getStudentByEmail().null",StudentDAO.class);
//        return Optional.empty();
        return null;
    }

    //--------------------UPDATE------------------------------
    @Override
    public Student update(Student entity) {
//        Connection connection = null;
//        try {
//            connection = DatabaseUtils.getConnection();
//            connection.setAutoCommit(false);
//
//            try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_STUDENT_QUERY)) {
//                ps.setString(1, entity.getFirst_name());
//                ps.setString(2, entity.getLast_name());
//                ps.setString(3, entity.getUser_email());
//                ps.setDate(4, DateUtils.toSqlDate(entity.getDate_of_birth()));
//                ps.setInt(5, entity.getStudent_id());
//
//                int affectedRows = ps.executeUpdate();
//                if (affectedRows == 0) {
//                    throw new SQLException("StudentDAO.update(): Student not found with id " + entity.getStudent_id());
//                }
//            }
//            connection.commit();
//            System.out.println("Student updated with id " + entity.getStudent_id());
//            return entity;
//
//        } catch (SQLException e) {
//            if (connection != null) {
//                try {
//                    connection.rollback();
//                } catch (SQLException ex) {
//                    DatabaseUtils.handleSQLException("StudentDAO.update().rollback", ex, LOGGER);
//                }
//            }
//            DatabaseUtils.handleSQLException("StudentDAO.update()", e, LOGGER);
//            return null;
//        } finally {
//            if (connection != null) {
//                try {
//                    DatabaseUtils.printSQLConnectionClose("StudentDAO.update()", StudentDAO.class);
//                    connection.setAutoCommit(true);
//                    connection.close();
//                } catch (SQLException ex) {
//                    DatabaseUtils.handleSQLException("StudentDAO.update().finally.close", ex, LOGGER);
//                }
//            }
//        }
        return null;
    }
    //Update Student Email
    public Student updateEmail(Student entity) {
//        Connection connection = null;
//        try{
//            connection = DatabaseUtils.getConnection();
//            connection.setAutoCommit(false);
//
//            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_STUDENT_EMAIL_QUERY)){
//                preparedStatement.setString(1, entity.getUser_email());
//                preparedStatement.setInt(2, entity.getStudent_id());
//                int affectedRows = preparedStatement.executeUpdate();
//                if (affectedRows == 0) {
//                    throw new SQLException("StudentDAO.updateEmail() failed. Student not found with student id "+ entity.getStudent_id());
//
//                }
//            }
//            connection.commit();
//            /*
//                TODO: DO DatabaseUtils.updatedEntity()
//                 */
//            System.out.println("Student updated user_email with id " + entity.getStudent_id());
//            return entity;
//        } catch (SQLException e) {
//            if(connection != null){
//                try {
//                    connection.rollback();
//                } catch (SQLException ex) {
//                    DatabaseUtils.handleSQLException("StudentDAO.updateEmail().rollback",ex,LOGGER);
//                }
//            }
//            DatabaseUtils.handleSQLException("StudentDAO.updateEmail().null",e,LOGGER);
//            return null;
//        }finally {
//            if(connection != null){
//                try {
//                    DatabaseUtils.printSQLConnectionClose("StudentDAO.updateEmail()",StudentDAO.class);
//                    connection.setAutoCommit(true);
//                    connection.close();
//                } catch (SQLException e) {
//                    DatabaseUtils.handleSQLException("StudentDAO.updateEmail().close()",e,LOGGER);
//                }
//            }
//        }
        return null;
    }
    //Update Student First Name
    public Student updateFirstName(Student entity) {
//        Connection connection = null;
//        try{
//            connection = DatabaseUtils.getConnection();
//            connection.setAutoCommit(false);
//
//            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_STUDENT_FIRST_NAME_QUERY)){
//                preparedStatement.setString(1, entity.getFirst_name());
//                preparedStatement.setInt(2, entity.getStudent_id());
//                int affectedRows = preparedStatement.executeUpdate();
//                if (affectedRows == 0) {
//                    throw new SQLException("StudentDAO.updateFirstName() failed. Student not found with student id "+ entity.getStudent_id());
//
//                }
//            }
//            connection.commit();
//            /*
//                TODO: DO DatabaseUtils.updatedEntity()
//                 */
//            System.out.println("Student updated firstname with id " + entity.getStudent_id());
//            return entity;
//        } catch (SQLException e) {
//            if(connection != null){
//                try {
//                    connection.rollback();
//                } catch (SQLException ex) {
//                    DatabaseUtils.handleSQLException("StudentDAO.updateFirstname().rollback",ex,LOGGER);
//                }
//            }
//            DatabaseUtils.handleSQLException("StudentDAO.updateFirstName().null",e,LOGGER);
//            return null;
//        }finally {
//            if(connection != null){
//                try {
//                    DatabaseUtils.printSQLConnectionClose("StudentDAO.updateFirstName()",StudentDAO.class);
//                    connection.setAutoCommit(true);
//                    connection.close();
//                } catch (SQLException e) {
//                    DatabaseUtils.handleSQLException("StudentDAO.updateFirstName().close()",e,LOGGER);
//                }
//            }
//        }
        return null;
    }

    //Update Student Last Name
    public Student updateLastName(Student entity) {
//        Connection connection = null;
//        try{
//            connection = DatabaseUtils.getConnection();
//            connection.setAutoCommit(false);
//
//            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_STUDENT_LAST_NAME_QUERY)){
//                preparedStatement.setString(1, entity.getLast_name());
//                preparedStatement.setInt(2, entity.getStudent_id());
//                int affectedRows = preparedStatement.executeUpdate();
//                if (affectedRows == 0) {
//                    throw new SQLException("StudentDAO.updateLastName() failed. Student not found with student id "+ entity.getStudent_id());
//
//                }
//            }
//            connection.commit();
//            /*
//                TODO: DO DatabaseUtils.updatedEntity()
//                 */
//            System.out.println("Student updated lastname with id " + entity.getStudent_id());
//            return entity;
//        } catch (SQLException e) {
//            if(connection != null){
//                try {
//                    connection.rollback();
//                } catch (SQLException ex) {
//                    DatabaseUtils.handleSQLException("StudentDAO.updateLastname().rollback",ex,LOGGER);
//                }
//            }
//            DatabaseUtils.handleSQLException("StudentDAO.updateLastName().null",e,LOGGER);
//            return null;
//        }finally {
//            if(connection != null){
//                try {
//                    DatabaseUtils.printSQLConnectionClose("StudentDAO.updateLastName()",StudentDAO.class);
//                    connection.setAutoCommit(true);
//                    connection.close();
//                } catch (SQLException e) {
//                    DatabaseUtils.handleSQLException("StudentDAO.updateLastName().close()",e,LOGGER);
//                }
//            }
//        }
        return null;
    }
    //Update Student Date of Birth
    public Student updateDateOfBirth(Student entity) {
//        Connection connection = null;
//        try{
//            connection = DatabaseUtils.getConnection();
//            connection.setAutoCommit(false);
//
//            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_STUDENT_DATE_OF_BIRTH_QUERY)){
//                preparedStatement.setDate(1, DateUtils.toSqlDate(entity.getDate_of_birth()));
//                preparedStatement.setInt(2, entity.getStudent_id());
//                int affectedRows = preparedStatement.executeUpdate();
//                if (affectedRows == 0) {
//                    throw new SQLException("StudentDAO.updateDateOfBirth() failed. Student not found with student id "+ entity.getStudent_id());
//
//                }
//            }
//            connection.commit();
//            System.out.println("Student updated date of birth with id " + entity.getStudent_id());
//            return entity;
//        } catch (SQLException e) {
//            if(connection != null){
//                try {
//                    connection.rollback();
//                } catch (SQLException ex) {
//                    DatabaseUtils.handleSQLException("StudentDAO.updateDateOfBirth().rollback",ex,LOGGER);
//                }
//            }
//            DatabaseUtils.handleSQLException("StudentDAO.updateDateOfBirth().null",e,LOGGER);
//            return null;
//        }finally {
//            if(connection != null){
//                try {
//                    DatabaseUtils.printSQLConnectionClose("StudentDAO.updateDateOfBirth()",StudentDAO.class);
//                    connection.setAutoCommit(true);
//                    connection.close();
//                } catch (SQLException e) {
//                    DatabaseUtils.handleSQLException("StudentDAO.updateDateOfBirth().close()",e,LOGGER);
//                }
//            }
//        }
        return null;
    }

    //-------------------DELETE-------------------------------
    @Override
    public boolean delete(Integer studentId) {
//        Connection connection = null;
//        try {
//            connection = DatabaseUtils.getConnection();
//            connection.setAutoCommit(false);
//            try (PreparedStatement ps = connection.prepareStatement(SQL_DELETE_STUDENT_QUERY)) {
//                ps.setInt(1, studentId);
//                int affectedRows = ps.executeUpdate();
//                if (affectedRows == 0) {
//                    throw new SQLException("StudentDAO.delete(): Student not found with id " + studentId);
//                }
//            }
//            connection.commit();
//            DatabaseUtils.printSQLConnectionClose("StudentDAO.delete()", StudentDAO.class);
//            return true;
//        } catch (SQLException e) {
//            if (connection != null) {
//                try {
//                    connection.rollback();
//                } catch (SQLException ex) {
//                    DatabaseUtils.handleSQLException("StudentDAO.delete().rollback", ex, LOGGER);
//                }
//            }
//            DatabaseUtils.handleSQLException("StudentDAO.delete().null", e, LOGGER);
//            return false;
//        } finally {
//            if (connection != null) {
//                try {
//                    DatabaseUtils.printSQLConnectionClose("StudentDAO.delete().finally", StudentDAO.class);
//                    connection.setAutoCommit(true);
//                    connection.close();
//                } catch (SQLException ex) {
//                    DatabaseUtils.handleSQLException("StudentDAO.delete().finally.close", ex, LOGGER);
//                }
//            }
//        }
        return false;
    }

    // u.username, u.user_email, u.password_hash, u.role_id, u.user_created_at
    // s.student_id, s.first_name, s.middle_name,s.last_name,s.date_of_birth, s.program_id, s.curriculum_id, s.year_level, s.is_irregular, s.is_active, s.graduated_at, s.user_id,
    private List<Student> processResultSet(ResultSet resultSet) throws SQLException {
        List<Student> students = new ArrayList<>();

        // Step 1: Build a set of available columns
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        Set<String> columns = new HashSet<>();
        for (int i = 1; i <= columnCount; i++) {
            columns.add(metaData.getColumnLabel(i).toLowerCase());
        }

        // Step 2: Safe getter helper
        BiFunction<String, Class<?>, Object> getColumn = (colName, type) -> {
            try {
                if (columns.contains(colName.toLowerCase())) {
                    return resultSet.getObject(colName);
                }
            } catch (SQLException e) {
                // ignore any error, return null
            }
            return null;
        };

        while (resultSet.next()) {
            Student student = new Student();
            // User data
            student.setUser_id((Integer) getColumn.apply("user_id", Integer.class));
            student.setUsername((String) getColumn.apply("username", String.class));
            student.setUser_email((String) getColumn.apply("user_email", String.class));
            student.setPassword_hash((String) getColumn.apply("password_hash", String.class));
            student.setRole_id((Integer) getColumn.apply("role_id", Integer.class));

            // Safe timestamp conversion
            Timestamp createdTs = (Timestamp) getColumn.apply("user_created_at", Timestamp.class);
            student.setUser_created_at(createdTs != null ? DateUtils.toLocalDateTime(createdTs) : null);

            // Student data
            student.setStudent_id((Integer) getColumn.apply("student_id", Integer.class));
            student.setFirst_name((String) getColumn.apply("first_name", String.class));
            student.setMiddle_name((String) getColumn.apply("middle_name", String.class));
            student.setLast_name((String) getColumn.apply("last_name", String.class));

            Date dob = (Date) getColumn.apply("date_of_birth", Date.class);
            student.setDate_of_birth(dob != null ? DateUtils.toLocalDate(dob) : null);

            student.setProgram_id((Integer) getColumn.apply("program_id", Integer.class));
            student.setCurriculum_id((Integer) getColumn.apply("curriculum_id", Integer.class));
            student.setYear_level((Integer) getColumn.apply("year_level", Integer.class));
            student.setIs_irregular((Boolean) getColumn.apply("is_irregular", Boolean.class));
            student.setIs_active((Boolean) getColumn.apply("is_active", Boolean.class));

            Date grad = (Date) getColumn.apply("graduated_at", Date.class);
            student.setGraduated_at(grad != null ? DateUtils.toLocalDate(grad) : null);

            students.add(student);
        }
        return students;
    }

}
