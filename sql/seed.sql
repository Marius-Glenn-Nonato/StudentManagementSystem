-- ==========================
-- Roles
-- ==========================
INSERT INTO sms.roles (role_name) VALUES
                                      ('ADMIN'),
                                      ('REGISTRAR'),
                                      ('STUDENT');

-- ==========================
-- Students
-- ==========================
INSERT INTO sms.students
    (student_number, first_name, last_name, email, date_of_birth)
    VALUES ('S001', 'Marius', 'Nonato', 'marius.nonato@example.com', '2000-05-15'),
           ('S002', 'Ton', 'Cruz', 'ton.cruz@example.com', '2001-08-22'),
           ('S003', 'Yamin', 'Lee', 'yamin.lee@example.com', '2000-12-10');


-- ==========================
-- Users
-- ==========================
-- Admin
INSERT INTO sms.users (username, password_hash, role_id) VALUES
    ('admin', 'hashed_password_here', 1);

-- Registrar
INSERT INTO sms.users (username, password_hash, role_id) VALUES
    ('registrar', 'hashed_password_here', 2);

-- Student Users
INSERT INTO sms.users (username, password_hash, role_id, student_id) VALUES
                                                                         ('marius123', 'hashed_password_here', 3, 1),
                                                                         ('ton456', 'hashed_password_here', 3, 2),
                                                              ('yamin789', 'hashed_password_here', 3, 3);


-- ==========================
-- Courses
-- ==========================
INSERT INTO sms.courses (course_code, course_name, credits) VALUES
                                                                ('CS101', 'Introduction to Computer Science', 3),
                                                                ('MATH201', 'Calculus II', 4),
                                                                ('ENG150', 'English Literature', 3);


-- ==========================
-- Enrollments
-- ==========================
INSERT INTO sms.enrollments (student_id, course_id, grade) VALUES
                                                               (1, 1, 95.5),   -- Marius in CS101
                                                               (1, 2, 88.0),   -- Marius in Calculus II
                                                               (2, 1, 90.0),   -- Ton in CS101
                                                               (2, 3, 85.0),   -- Ton in English Literature
                                                               (3, 2, 92.5);   -- Yamin in Calculus II


-- ==========================
-- Audit Logs
-- ==========================
INSERT INTO sms.audit_logs (user_id, action, entity_type, entity_id) VALUES
                                                                         (1, 'CREATE_STUDENT', 'students', 1),
                                                                         (1, 'CREATE_STUDENT', 'students', 2),
                                                                         (2, 'ENROLL_STUDENT', 'enrollments', 1),
                                                                         (3, 'UPDATE_GRADE', 'enrollments', 1);