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
WHERE e.student_id=1
AND t.is_active=true -- only attendance in the current term
AND a.status IN ('ABSENT', 'LATE')
ORDER BY c.course_code, a.attendance_date

