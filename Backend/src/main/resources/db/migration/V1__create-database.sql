CREATE TABLE Users(
    id INT UNIQUE NOT NULL auto_increment,
    name VARCHAR(50) NOT NULL,
    date_of_birth DATE NOT NULL,
    email VARCHAR(70) NOT NULL,
    cellphone VARCHAR(15) NOT NULL,
    password TINYTEXT NOT NULL,
    gender CHAR(1) NOT NULL,
    photo_path VARCHAR(50) default null,
    PRIMARY KEY (id)
)