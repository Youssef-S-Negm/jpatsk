-- Authors
INSERT INTO T_AUTHOR (NAME, EMAIL, BIRTH_DATE) VALUES
('Alice', 'alice@example.com', '1985-04-12'),
('Bob', 'bob@example.com', '1990-07-25'),
('Clara', 'clara@example.com', '1988-11-03');

-- Courses
INSERT INTO T_COURSE (NAME, DESCRIPTION, CREDIT) VALUES
('Java Basics', 'Introduction to Java programming.', 3),
('Database Design', 'Learn relational DB design.', 4),
('Web Development', 'Build modern web applications.', 5),
('Algorithms', 'In-depth algorithm analysis.', 4);

-- Author-Course Relationships (many-to-many)
INSERT INTO T_AUTHOR_COURSE (AUTHOR_ID, COURSE_ID) VALUES
(1, 1),  -- Alice -> Java Basics
(2, 2),  -- Bob -> Database Design
(1, 3),  -- Alice -> Web Development
(3, 4),  -- Clara -> Algorithms
(2, 4);  -- Bob -> Algorithms (co-authored)

-- Assessments
INSERT INTO T_ASSESSMENT (CONTENT, COURSE_ID) VALUES
('Java quiz 1: OOP concepts', 1),
('DB quiz 1: ER diagrams', 2),
('Web quiz 1: HTML & CSS', 3),
('Algo quiz 1: Sorting algorithms', 4);

-- Ratings
INSERT INTO T_RATING (NUMBER, COURSE_ID) VALUES
(4.5, 1),
(3.8, 1),
(4.2, 2),
(5.0, 3),
(4.1, 4),
(3.5, 4);
