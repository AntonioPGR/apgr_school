ALTER TABLE users
ADD COLUMN active TINYINT(1);

UPDATE users
SET active = 1;

ALTER TABLE users
MODIFY COLUMN active TINYINT(1) NOT NULL;
