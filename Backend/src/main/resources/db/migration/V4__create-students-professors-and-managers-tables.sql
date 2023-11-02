CREATE TABLE Students (
    id int unique not null auto_increment,
    userId int unique not null,
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES Users(id)
);

CREATE TABLE Professors (
    id int unique not null auto_increment,
    userId int unique not null,
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES Users(id)
);

CREATE TABLE Managers (
    id int unique not null auto_increment,
    userId int unique not null,
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES Users(id)
);