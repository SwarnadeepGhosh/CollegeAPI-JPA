INSERT INTO collegeapi.TEACHER VALUES (nextval('collegeapi.teacher_seq'), 'John','Doe');
INSERT INTO collegeapi.TEACHER VALUES (nextval('collegeapi.teacher_seq'), 'Swarnadeep','Ghosh');
INSERT INTO collegeapi.TEACHER VALUES (nextval('collegeapi.teacher_seq'), 'Akash','Singh');

INSERT INTO collegeapi.course_material (course_material_id, url) VALUES(nextval('collegeapi.course_material_seq'), 'https://www.w3schools.com/java/');
INSERT INTO collegeapi.course_material (course_material_id, url) VALUES(nextval('collegeapi.course_material_seq'), 'https://www.w3schools.com/python/');
INSERT INTO collegeapi.course_material (course_material_id, url) VALUES(nextval('collegeapi.course_material_seq'), 'https://www.javatpoint.com/spring-boot-tutorial');
INSERT INTO collegeapi.course_material (course_material_id, url) VALUES(nextval('collegeapi.course_material_seq'), 'https://developer.mozilla.org/en-US/docs/Learn/Server-side/Django');
INSERT INTO collegeapi.course_material (course_material_id, url) VALUES(nextval('collegeapi.course_material_seq'), 'https://trailhead.salesforce.com/');
INSERT INTO collegeapi.course_material (course_material_id, url) VALUES(nextval('collegeapi.course_material_seq'), 'https://spring.io/blog/2015/07/14/microservices-with-spring/');


INSERT INTO collegeapi.STUDENT (student_id, email_address, first_name, last_name, guardian_name, guardian_email, guardian_mobile)
VALUES(nextval('collegeapi.student_seq'), 'sujit1@test.com', 'Sujit 2', 'Paul', 'Saikat 2 Paul', 'saikat2@test.com', '9012345678');

INSERT INTO collegeapi.STUDENT (student_id, email_address, first_name, last_name, guardian_name, guardian_email, guardian_mobile)
VALUES(nextval('collegeapi.student_seq'), 'atul1@test.com', 'Atul 2', 'Manna', 'Adharsh 2 Manna', 'adharsh2@test.com', '9012345677');

INSERT INTO collegeapi.STUDENT (student_id, email_address, first_name, last_name, guardian_name, guardian_email, guardian_mobile)
VALUES(nextval('collegeapi.student_seq'), 'amit1@test.com', 'Amit 2', 'Kinagi', 'Vaibhav 2 Kinagi', 'vaibhav2@test.com', '9012345678');

INSERT INTO collegeapi.course (course_id, credit, title, course_material_id, teacher_id) VALUES(nextval('collegeapi.course_seq'), 80, 'JAVA', 1, 1);
INSERT INTO collegeapi.course (course_id, credit, title, course_material_id, teacher_id) VALUES(nextval('collegeapi.course_seq'), 90, 'Python', 2, 2);
INSERT INTO collegeapi.course (course_id, credit, title, course_material_id, teacher_id) VALUES(nextval('collegeapi.course_seq'), 70, 'Spring Boot', 3, 1);
INSERT INTO collegeapi.course (course_id, credit, title, course_material_id, teacher_id) VALUES(nextval('collegeapi.course_seq'), 87, 'Django', 4, 2);
INSERT INTO collegeapi.course (course_id, credit, title, course_material_id, teacher_id) VALUES(nextval('collegeapi.course_seq'), 84, 'Salesforce', 5, 3);
INSERT INTO collegeapi.course (course_id, credit, title, course_material_id, teacher_id) VALUES(nextval('collegeapi.course_seq'), 74, 'Microservices', 6, 1);

INSERT INTO collegeapi.student_course_map (course_id, student_id, rowid) VALUES(1, 1, unique_rowid());
INSERT INTO collegeapi.student_course_map (course_id, student_id, rowid) VALUES(1, 2, unique_rowid());
INSERT INTO collegeapi.student_course_map (course_id, student_id, rowid) VALUES(3, 1, unique_rowid());

--
--INSERT INTO ROLE VALUES (role_seq.nextval, 'CONSUMER');
--INSERT INTO USER VALUES (user_seq.nextval, 'pass_word', 'jack');
--SELECT nextval('collegeapi.teacher_seq');
