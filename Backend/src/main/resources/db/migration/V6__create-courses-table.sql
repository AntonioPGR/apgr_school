CREATE TABLE Courses (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  description LONGTEXT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE Course_Lessons (
  id INT NOT NULL AUTO_INCREMENT,
  course INT NOT NULL,
  lesson INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (course) REFERENCES Courses(id),
  FOREIGN KEY (lesson) REFERENCES Lessons(id)
);

CREATE TABLE Course_Students (
  id INT NOT NULL AUTO_INCREMENT,
  course INT NOT NULL,
  student INT NOT NULL,
  enrollment_date DATE NOT NULL,
  end_date DATE NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (course) REFERENCES Courses(id),
  FOREIGN KEY (student) REFERENCES Users(id)
);