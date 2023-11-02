CREATE TABLE SchoolClasses (
    id int unique not null auto_increment,
    name varchar(50) not null,
    date_time DATETIME not null,
    PRIMARY KEY (id)
);

CREATE TABLE Tasks (
    id int unique not null auto_increment,
    title varchar(70) not null,
    description text not null,
    due_date date not null,
    school_class int not null,
    FOREIGN KEY (school_class) REFERENCES SchoolClasses(id)
);

CREATE TABLE Courses (
    id int unique not null auto_increment,
    name varchar(50) not null,
    description text,
    PRIMARY KEY (id)
);