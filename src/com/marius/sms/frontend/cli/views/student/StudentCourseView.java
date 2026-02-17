package com.marius.sms.frontend.cli.views.student;

import com.marius.sms.backend.dao.EnrollmentDAO;
import com.marius.sms.backend.dao.OfferingScheduleDAO;
import com.marius.sms.backend.dao.StudentDAO;
import com.marius.sms.backend.dto.StudentCourseDTO;
import com.marius.sms.backend.entities.*;
import com.marius.sms.backend.service.StudentService;

import java.util.List;

public class StudentCourseView {
    private final Student student;
    private EnrollmentDAO enrollmentDAO;
    private OfferingScheduleDAO offeringScheduleDAO;
    private StudentService studentService;

    public StudentCourseView(Student student) {
        this.student = student;
        enrollmentDAO = new EnrollmentDAO();
        offeringScheduleDAO = new OfferingScheduleDAO();
        studentService = new StudentService();
    }

    public void show() {
        List<StudentCourseDTO> coursesOfStudents = studentService.getCoursesOfStudent(student.getStudent_id());
        // Print out the courses and their schedules
        for (StudentCourseDTO dto : coursesOfStudents) {
            Enrollment enrollment = dto.getEnrollment();
            CourseOffering offering = enrollment.getCourse_offering();
            Course course = offering.getCourse();
            Teacher teacher = offering.getTeacher();
            Term term = offering.getTerm();

            System.out.println("------------------------------------------------");
            System.out.println("Course Code: " + course.getCourseCode());
            System.out.println("Course Description: "  + course.getCourseName());
            System.out.println("Course Section Code: " + offering.getSectionCode());
            System.out.println("Teacher: " + teacher.getFirst_name() + " " + teacher.getLast_name());

            System.out.println("Schedule:");
            for (OfferingSchedule schedule : dto.getOfferingSchedules()) {
                System.out.println("  " + schedule.getDayOfWeek() + " " +
                        schedule.getStartTime() + " - " +
                        schedule.getEndTime() + " at " + schedule.getRoom());
            }
            System.out.println("------------------------------------------------");
        }
    }

}
