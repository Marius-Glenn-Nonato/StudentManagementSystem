package com.marius.sms.backend.service;

import com.marius.sms.backend.dao.AttendanceDAO;
import com.marius.sms.backend.dao.EnrollmentDAO;
import com.marius.sms.backend.dao.OfferingScheduleDAO;
import com.marius.sms.backend.dto.StudentAttendanceDTO;
import com.marius.sms.backend.dto.StudentCourseDTO;
import com.marius.sms.backend.entities.Enrollment;
import com.marius.sms.backend.entities.OfferingSchedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentService {



    public StudentService() {
    }


//  DTO is built in this method as it fetches data from multiple DAOs
    public List<StudentCourseDTO> getCoursesOfStudent(int studentId) {
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        OfferingScheduleDAO scheduleDAO = new OfferingScheduleDAO();

        List<StudentCourseDTO> studentCourseDTOS = new ArrayList<>();

        // 1 Fetch enrollments from DAO
        // enrollment status, final_grade, course_offering_id (1,2 or more), section_code
        // course_code, course_name, course_units, teacher_name, start_date, end_date,
        List<Enrollment> enrollments = enrollmentDAO.getEnrollmentsByStudent(studentId);

        if (enrollments.isEmpty()) return studentCourseDTOS;

        //1.1 Get the course_offering_ids that student is enrolled at as list
        List<Integer> offeringIds = enrollments.stream()
                .map(e -> e.getCourse_offering().getCourse_offering_id())
                .collect(Collectors.toList());

        // 2 Fetch schedules for all offerings
        //course_offering_id (from first query), offering_schedule_id, day_of_week,
        //start_time, end_time, room
        List<OfferingSchedule> schedules = scheduleDAO.getSchedulesByOfferingIds(offeringIds);

        // 3 Map offeringId -> List<OfferingSchedule>
        Map<Integer, List<OfferingSchedule>> scheduleMap = schedules.stream()
                .collect(Collectors.groupingBy(
                        os -> os.getCourseOffering().getCourse_offering_id()));

        // 4 Build DTOs
        for (Enrollment e : enrollments) {
            StudentCourseDTO dto = new StudentCourseDTO();
            dto.setEnrollment(e);

            // Attach schedules
            List<OfferingSchedule> osList = scheduleMap.getOrDefault(e.getCourse_offering().getCourse_offering_id(), new ArrayList<>());
            dto.setOfferingSchedules(osList);

            studentCourseDTOS.add(dto);
        }

        return studentCourseDTOS;
    }

    public List<StudentAttendanceDTO> getAttendanceOfStudent(int studentId) {
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        List<StudentAttendanceDTO> studentAttendanceDTOS = attendanceDAO.getStudentAttendance(studentId);
        return studentAttendanceDTOS;
    }
}

