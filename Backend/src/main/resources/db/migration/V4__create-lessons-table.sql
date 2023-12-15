CREATE TABLE Lesson (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  datetime datetime NOT NULL,
  duration_in_minutes INT NOT NULL,
  professor int NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (professor) REFERENCES Users(id)
);