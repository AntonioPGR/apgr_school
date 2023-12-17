ALTER TABLE lesson RENAME TO Lessons;

CREATE TABLE Tasks (
  id int NOT NULL AUTO_INCREMENT,
  title varchar(100) NOT NULL,
  due_date datetime NOT NULL,
  description text,
  lesson int NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (lesson) REFERENCES Lessons(id)
);

CREATE TABLE students_tasks(
  id INT NOT NULL AUTO_INCREMENT,
  task INT NOT NULL,
  student INT NOT NULL,
  is_done TINYINT NOT NULL DEFAULT 0,
  response_location TEXT,
  PRIMARY KEY (id),
  FOREIGN KEY (task) REFERENCES Tasks(id),
  FOREIGN KEY (student) REFERENCES Users(id)
);

