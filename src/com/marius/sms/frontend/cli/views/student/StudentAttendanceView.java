package com.marius.sms.frontend.cli.views.student;

import com.marius.sms.backend.dto.StudentAttendanceDTO;
import com.marius.sms.backend.entities.*;
import com.marius.sms.backend.service.StudentService;

import java.util.List;

public class StudentAttendanceView {
    private Student student;
    private StudentService studentService;
    public StudentAttendanceView(Student student){
        this.studentService = new StudentService();
        this.student = student;

    }
    public void show() {
        List<StudentAttendanceDTO> studentAttendanceDTOS = studentService.getAttendanceOfStudent(student.getStudent_id());

        // Print header
        System.out.printf("%-12s %-12s %-30s %-15s %-10s%n",
                "Section Code", "Course Code", "Course Name", "Attendance Date", "Status");

        // Print a separator line
        System.out.println("-------------------------------------------------------------------------------");

        // Iterate through the list and print each record
        for (StudentAttendanceDTO dto : studentAttendanceDTOS) {
            CourseOffering co = dto.getCourseOffering();
            Course course = co.getCourse();
            Attendance att = dto.getAttendance();
            System.out.printf("%-12s %-12s %-30s %-15s %-10s%n",
                    co.getSectionCode(),
                    course.getCourseCode(),
                    course.getCourseName(),
                    att.getAttendanceDate(),
                    att.getStatus());
                }
        System.out.println("-------------------------------------------------------------------------------");


    }
}
