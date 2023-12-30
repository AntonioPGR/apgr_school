-- Drop existing tables
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Lessons;
DROP TABLE IF EXISTS Tasks;
DROP TABLE IF EXISTS Students_Tasks;
DROP TABLE IF EXISTS Courses;
DROP TABLE IF EXISTS Course_Lessons;
DROP TABLE IF EXISTS Course_Students;
SET FOREIGN_KEY_CHECKS=1;

-- Create new tables
CREATE TABLE Users (
    id          VARCHAR(36)  NOT NULL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    birth_date  DATE         NOT NULL,
    email       VARCHAR(70)  NOT NULL UNIQUE,
    cellphone   VARCHAR(25)  NOT NULL UNIQUE,
    password    TINYTEXT     NOT NULL,
    gender      CHAR(1)      NOT NULL,
    photo_path  VARCHAR(255) DEFAULT NULL,
    active      TINYINT(1)   NOT NULL DEFAULT 1,
    permissions TEXT         NOT NULL
);

CREATE TABLE Lessons (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    datetime DATETIME NOT NULL,
    duration_in_minutes INT NOT NULL,
    professor VARCHAR(36) NOT NULL,
    FOREIGN KEY (professor) REFERENCES Users(id)
);

CREATE TABLE Tasks (
    id VARCHAR(36) PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    due_date DATETIME NOT NULL,
    description TEXT,
    lesson VARCHAR(36) NOT NULL,
    FOREIGN KEY (lesson) REFERENCES Lessons(id)
);

CREATE TABLE Students_Tasks(
    id VARCHAR(36) PRIMARY KEY,
    task VARCHAR(36) NOT NULL,
    student VARCHAR(36) NOT NULL,
    is_done TINYINT NOT NULL DEFAULT 0,
    response_location TEXT,
    FOREIGN KEY (task) REFERENCES Tasks(id),
    FOREIGN KEY (student) REFERENCES Users(id)
);

CREATE TABLE Courses (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description LONGTEXT NULL
);

CREATE TABLE Course_Lessons (
    id VARCHAR(36) PRIMARY KEY,
    course VARCHAR(36) NOT NULL,
    lesson VARCHAR(36) NOT NULL,
    FOREIGN KEY (course) REFERENCES Courses(id),
    FOREIGN KEY (lesson) REFERENCES Lessons(id)
);

CREATE TABLE Course_Students (
    id VARCHAR(36) PRIMARY KEY,
    course VARCHAR(36) NOT NULL,
    student VARCHAR(36) NOT NULL,
    enrollment_date DATE NOT NULL,
    end_date DATE NULL,
    FOREIGN KEY (course) REFERENCES Courses(id),
    FOREIGN KEY (student) REFERENCES Users(id)
);
