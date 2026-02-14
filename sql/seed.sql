-- =========================================================
-- Seed Data for SMS Schema
-- =========================================================

-- ==========================
-- Roles
-- ==========================
INSERT INTO sms.roles (role_name) VALUES
                                      ('ADMIN'),
                                      ('REGISTRAR'),
                                      ('STUDENT'),
                                      ('TEACHER');

-- ==========================
-- Users
-- ==========================
-- Admin
INSERT INTO sms.users (username, email, password_hash, role_id) VALUES
    ('admin', 'admin@example.com', 'hashed_password_here', 1);

-- Registrar
INSERT INTO sms.users (username, email, password_hash, role_id) VALUES
    ('registrar', 'registrar@example.com', 'hashed_password_here', 2);

-- Student Users
INSERT INTO sms.users (username, email, password_hash, role_id) VALUES
                                                                    ('marius123', 'marius.nonato@example.com', 'hashed_password_here', 3),
                                                                    ('ton456', 'ton.cruz@example.com', 'hashed_password_here', 3),
                                                                    ('yamin789', 'yamin.lee@example.com', 'hashed_password_here', 3);

-- Teacher Users
INSERT INTO sms.users (username, email, password_hash, role_id) VALUES
                                                                    ('alicej', 'alice.johnson@example.com', 'hashed_password_here', 4),
                                                                    ('bobsmith', 'bob.smith@example.com', 'hashed_password_here', 4);

-- ==========================
-- Students
-- ==========================
INSERT INTO sms.students (user_id, first_name, last_name, date_of_birth)
VALUES
    (3, 'Marius', 'Nonato',  '2000-05-15'),
    (4, 'Ton', 'Cruz', '2001-08-22'),
    (5, 'Yamin', 'Lee', '2000-12-10');

-- ==========================
-- Teachers
-- ==========================
INSERT INTO sms.teachers (user_id, first_name, last_name, hire_date)
VALUES
    (6, 'Alice', 'Johnson', '2015-08-01'),
    (7, 'Bob', 'Smith', '2018-03-15');

-- ==========================
-- Courses
-- ==========================
INSERT INTO sms.courses (course_code, course_name, credits, teacher_id) VALUES
                                                                            ('CS101', 'Introduction to Computer Science', 3, 1), -- Alice
                                                                            ('MATH201', 'Calculus II', 4, 2),                     -- Bob
                                                                            ('ENG150', 'English Literature', 3, NULL);           -- No teacher assigned yet

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
