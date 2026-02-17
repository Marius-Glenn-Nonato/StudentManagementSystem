BEGIN;

-- 1) Roles
INSERT INTO sms.roles (role_id, role_name) VALUES
                                               (1, 'ADMIN'),
                                               (2, 'REGISTRAR'),
                                               (3, 'STUDENT'),
                                               (4, 'TEACHER');

-- 2) Programs
INSERT INTO sms.programs (program_id, program_name) VALUES
    (1, 'BS Computer Science');

-- 3) Users (admin, registrar, students, teachers)
-- password_hash are placeholders; replace with real hashed passwords when needed
INSERT INTO sms.users (user_id, username, password_hash, role_id, user_email) VALUES
                                                                                  (1, 'admin',        'hash_admin_placeholder',       1, 'admin@example.com'),
                                                                                  (2, 'registrar',    'hash_registrar_placeholder',   2, 'registrar@example.com'),

                                                                                  (3, 'marius_n',     '5b39bfccb1447d4aae30e7a4fb0f4ba37e79ea96ec54b5ba7223979a15e4d0ae',      3, 'marius.nonato@example.com'),
                                                                                  (4, 'hannah_t',     'hash_hannah_placeholder',      3, 'hannah.ragudos@example.com'),
                                                                                  (5, 'sanchie_g',    'hash_sanchie_placeholder',     3, 'sanchie.guzman@example.com'),
                                                                                  (6, 'janella_e',    'hash_janella_placeholder',     3, 'janella.estrada@example.com'),

                                                                                  (7, 'randy_d',      '6aac1e2f1838b854ba9d1abf94f018fdf70cf4aedfb8e46f513f49854df3be4e',       4, 'randy.domantay@example.com'),
                                                                                  (8, 'rona_d',       'hash_rona_placeholder',        4, 'rona.domantay@example.com'),
                                                                                  (9, 'josephine_d',  'hash_josephine_placeholder',   4, 'josephine.delacruz@example.com');

-- 4) Teachers (user_id -> teacher)
INSERT INTO sms.teachers (teacher_id, user_id, first_name, middle_name, last_name, is_active, left_at) VALUES
                                                                                                           (1, 7, 'Randy', 'N/A', 'Domantay', true, NULL),
                                                                                                           (2, 8, 'Rona',  'N/A', 'Domantay', true, NULL),
                                                                                                           (3, 9, 'Josephine', 'N/A', 'Dela Cruz', true, NULL);

-- 5) Curricula
INSERT INTO sms.curricula (curricula_id, program_id, academic_year, version, is_active) VALUES
    (1, 1, '2024-2025', 1, true);

-- 6) Courses (IDs, codes, names, units)
INSERT INTO sms.courses (course_id, course_code, course_name, course_units) VALUES
                                                                                (1, 'CS111', 'Introduction to Computing', 3),
                                                                                (2, 'CS112', 'Computer Programming 1', 3),
                                                                                (3, 'CS122', 'Computer Programming 2', 3),
                                                                                (4, 'CS123', 'Architecture and Organization', 3);

-- 7) Curriculum -> course mapping (year_level, semester)
INSERT INTO sms.curriculum_courses (curriculum_course_id, curriculum_id, course_id, year_level, semester) VALUES
                                                                                                              (1, 1, 1, 1, 1),
                                                                                                              (2, 1, 2, 1, 1),
                                                                                                              (3, 1, 3, 1, 2),
                                                                                                              (4, 1, 4, 1, 2);

-- 8) Terms (only first term active)
INSERT INTO sms.terms (term_id, term_name, start_date, end_date, is_active) VALUES
                                                                                (1, '1st Semester 2025-2026', '2025-08-01', '2025-12-15', true),
                                                                                (2, '2nd Semester 2025-2026', '2026-01-15', '2026-05-15', false);

-- 9) Course offerings (section A for simplicity, link to teachers & terms)
INSERT INTO sms.course_offerings (course_offering_id, course_id, section_code, teacher_id, term_id, capacity) VALUES
                                                                                                                  (1, 1, '9111', 1, 1, 30),  -- CS111 offered in term 1 by Randy
                                                                                                                  (2, 2, '9112', 2, 1, 30),  -- CS112 offered in term 1 by Rona
                                                                                                                  (3, 3, '9211', 1, 2, 30),  -- CS122 offered in term 2 by Randy (term 2 inactive)
                                                                                                                  (4, 4, '9212', 3, 2, 30);  -- CS123 offered in term 2 by Josephine (term 2 inactive)

-- 10) Students (sample names parsed into first/middle/last)
INSERT INTO sms.students (
    student_id, user_id, first_name, middle_name, last_name, date_of_birth, program_id, curriculum_id, year_level, is_irregular, is_active
) VALUES
      (1, 3, 'Marius Glenn', 'Medina', 'Nonato', '2004-05-10', 1, 1, 1, false, true),
      (2, 4, 'Hannah', 'Tabaquero',      'Ragudos', '2004-11-02', 1, 1, 1, false, true),
      (3, 5, 'Sanchie Earl',' Manalo',    'Guzman',  '2005-02-17', 1, 1, 1, false, true),
      (4, 6, 'Janella Maris','Apao',     'Estrada', '2004-08-21', 1, 1, 1, false, true);

-- 11) Enrollments (term 1 offerings)
INSERT INTO sms.enrollments (
    enrollment_id, student_id, course_offering_id, status, intended_year_level, intended_semester, enrolled_at
) VALUES
      (1, 1, 1, 'ACTIVE', 1, 1, '2025-08-05'),
      (2, 1, 2, 'ACTIVE', 1, 1, '2025-08-05'),
      (3, 2, 1, 'ACTIVE', 1, 1, '2025-08-05'),
      (4, 2, 2, 'ACTIVE', 1, 1, '2025-08-05'),
      (5, 3, 1, 'ACTIVE', 1, 1, '2025-08-05'),
      (6, 3, 2, 'ACTIVE', 1, 1, '2025-08-05'),
      (7, 4, 1, 'ACTIVE', 1, 1, '2025-08-05'),
      (8, 4, 2, 'ACTIVE', 1, 1, '2025-08-05');

-- 12) Student curriculum progress
INSERT INTO sms.student_curriculum_progress (
    curriculum_progress_id, student_id, curriculum_course_id, status
) VALUES
      (1, 1, 1, 'IN_PROGRESS'),
      (2, 1, 2, 'IN_PROGRESS'),
      (3, 1, 3, 'NOT_TAKEN'),
      (4, 1, 4, 'NOT_TAKEN'),
      (5, 2, 1, 'IN_PROGRESS'),
      (6, 2, 2, 'IN_PROGRESS'),
      (7, 2, 3, 'NOT_TAKEN'),
      (8, 2, 4, 'NOT_TAKEN'),
      (9, 3, 1, 'IN_PROGRESS'),
      (10, 3, 2, 'IN_PROGRESS'),
      (11, 3, 3, 'NOT_TAKEN'),
      (12, 3, 4, 'NOT_TAKEN'),
      (13, 4, 1, 'IN_PROGRESS'),
      (14, 4, 2, 'IN_PROGRESS'),
      (15, 4, 3, 'NOT_TAKEN'),
      (16, 4, 4, 'NOT_TAKEN');

-- 13) Attendances (example rows)
INSERT INTO sms.attendances (attendance_id, enrollment_id, attendance_date, status) VALUES
                                                                                        (1, 1, '2025-08-10', 'PRESENT'),
                                                                                        (2, 1, '2025-08-11', 'LATE'),
                                                                                        (3, 2, '2025-08-10', 'PRESENT');
-- 10) Offering Schedules
-- CS111 (offering_id = 1) → MWF
INSERT INTO sms.offering_schedules
(course_offering_id, day_of_week, start_time, end_time, room)
VALUES
    (1, 'MON', '08:00', '09:30', 'Room 101'),
    (1, 'WED', '08:00', '09:30', 'Room 101'),
    (1, 'FRI', '08:00', '09:30', 'Room 101');

-- CS112 (offering_id = 2) → TTh
INSERT INTO sms.offering_schedules
(course_offering_id, day_of_week, start_time, end_time, room)
VALUES
    (2, 'TUE', '10:00', '11:30', 'Room 102'),
    (2, 'THU', '10:00', '11:30', 'Room 102');

-- CS122 (offering_id = 3) → MWF (Term 2 - inactive term)
INSERT INTO sms.offering_schedules
(course_offering_id, day_of_week, start_time, end_time, room)
VALUES
    (3, 'MON', '13:00', '14:30', 'Room 201'),
    (3, 'WED', '13:00', '14:30', 'Room 201'),
    (3, 'FRI', '13:00', '14:30', 'Room 201');

-- CS123 (offering_id = 4) → Saturday only
INSERT INTO sms.offering_schedules
(course_offering_id, day_of_week, start_time, end_time, room)
VALUES
    (4, 'SAT', '09:00', '12:00', 'Lab 1');



COMMIT;
