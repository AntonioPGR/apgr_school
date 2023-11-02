CREATE TABLE StudentsEnrolmentsInCourses (
    id int unique not null auto_increment,
    studentId int not null,
    courseId int not null,
    enrolment_date DATE not null,
    end_date DATE,
    PRIMARY KEY (id),
    FOREIGN KEY (studentId) REFERENCES Students(id),
    FOREIGN KEY (courseId) REFERENCES Courses(id)
);

CREATE TABLE CourseHasClasses (
    id int unique not null auto_increment,
    classId int not null,
    courseId int not null,
    PRIMARY KEY (id),
    FOREIGN KEY (classId) REFERENCES SchoolClasses(id),
    FOREIGN KEY (courseId) REFERENCES Courses(id)
);