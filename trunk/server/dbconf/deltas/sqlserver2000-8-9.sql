ALTER TABLE PERSON ALTER COLUMN PASSWORD VARCHAR(256) NOT NULL

UPDATE PERSON SET PASSWORD = 'SALT_' + SALT + PASSWORD

ALTER TABLE PERSON DROP COLUMN SALT

CREATE TABLE PERSON_PASSWORD
	(PERSON_ID INTEGER NOT NULL,
	PASSWORD VARCHAR(256) NOT NULL,
	PASSWORD_DATE DATETIME DEFAULT GETDATE())

INSERT INTO PERSON_PASSWORD (PERSON_ID, PASSWORD) SELECT ID, PASSWORD FROM PERSON

ALTER TABLE PERSON DROP COLUMN PASSWORD

ALTER TABLE PERSON_PASSWORD ADD GRACE_PERIOD_START DATETIME DEFAULT NULL