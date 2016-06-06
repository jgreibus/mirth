CREATE TABLE SCHEMA_INFO
	(VERSION VARCHAR(40));

CREATE TABLE EVENT
	(ID INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL PRIMARY KEY,
	DATE_CREATED TIMESTAMP DEFAULT NULL,
	NAME CLOB NOT NULL,
	EVENT_LEVEL VARCHAR(40) NOT NULL,
	OUTCOME VARCHAR(40),
	ATTRIBUTES CLOB,
	USER_ID INTEGER NOT NULL,
	IP_ADDRESS VARCHAR(40),
	SERVER_ID CHARACTER VARYING(36));
	
CREATE TABLE CHANNEL
	(ID VARCHAR(36) NOT NULL PRIMARY KEY,
	NAME VARCHAR(40) NOT NULL,
	REVISION INTEGER,
	CHANNEL CLOB);

CREATE TABLE SCRIPT
	(GROUP_ID VARCHAR(40) NOT NULL,
	ID VARCHAR(40) NOT NULL,
	SCRIPT CLOB);
	
ALTER TABLE SCRIPT ADD PRIMARY KEY (GROUP_ID, ID);
	
CREATE TABLE PERSON
	(ID INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL PRIMARY KEY,
	USERNAME VARCHAR(40) NOT NULL,
	FIRSTNAME VARCHAR(40),
	LASTNAME VARCHAR(40),
	ORGANIZATION VARCHAR(255),
	INDUSTRY VARCHAR(255),
	EMAIL VARCHAR(255),
	PHONENUMBER VARCHAR(40),
	DESCRIPTION VARCHAR(255),
	LAST_LOGIN TIMESTAMP DEFAULT NULL,
	GRACE_PERIOD_START TIMESTAMP DEFAULT NULL,
	LOGGED_IN SMALLINT NOT NULL);
	
CREATE TABLE PERSON_PREFERENCE
	(PERSON_ID INTEGER NOT NULL,
	NAME VARCHAR(255) NOT NULL,
	VALUE CLOB,
	CONSTRAINT PERSON_ID_PERSON_PREF_FK FOREIGN KEY(PERSON_ID) REFERENCES PERSON(ID) ON DELETE CASCADE);
	
CREATE INDEX PERSON_PREFERENCE_INDEX1 ON PERSON_PREFERENCE(PERSON_ID);

CREATE TABLE PERSON_PASSWORD
	(PERSON_ID INTEGER NOT NULL,
	PASSWORD VARCHAR(255) NOT NULL,
	PASSWORD_DATE TIMESTAMP DEFAULT NULL,
	CONSTRAINT PERSON_ID_PP_FK FOREIGN KEY(PERSON_ID) REFERENCES PERSON(ID) ON DELETE CASCADE);

CREATE TABLE ALERT (
	ID VARCHAR(36) NOT NULL PRIMARY KEY,
	NAME VARCHAR(255) NOT NULL UNIQUE,
	ALERT CLOB NOT NULL
);

CREATE TABLE CODE_TEMPLATE_LIBRARY
	(ID VARCHAR(255) NOT NULL PRIMARY KEY,
	NAME VARCHAR(255) NOT NULL UNIQUE,
	REVISION INTEGER,
	LIBRARY CLOB);

CREATE TABLE CODE_TEMPLATE
	(ID VARCHAR(255) NOT NULL PRIMARY KEY,
	NAME VARCHAR(255) NOT NULL,
	REVISION INTEGER,
	CODE_TEMPLATE CLOB);
	
CREATE TABLE CONFIGURATION
	(CATEGORY VARCHAR(255) NOT NULL,
	NAME VARCHAR(255) NOT NULL,
	VALUE CLOB);
	
CREATE TABLE CHANNEL_GROUP
	(ID VARCHAR(255) NOT NULL PRIMARY KEY,
	NAME VARCHAR(255) NOT NULL UNIQUE,
	REVISION INTEGER,
	CHANNEL_GROUP CLOB);
	
INSERT INTO PERSON (USERNAME, LOGGED_IN) VALUES('admin', 0);

INSERT INTO PERSON_PASSWORD (PERSON_ID, PASSWORD) VALUES(1, 'YzKZIAnbQ5m+3llggrZvNtf5fg69yX7pAplfYg0Dngn/fESH93OktQ==');

INSERT INTO SCHEMA_INFO (VERSION) VALUES ('3.4.1');

INSERT INTO CONFIGURATION (CATEGORY, NAME, VALUE) VALUES ('core', 'stats.enabled', '1');

INSERT INTO CONFIGURATION (CATEGORY, NAME, VALUE) VALUES ('core', 'server.resetglobalvariables', '1');

INSERT INTO CONFIGURATION (CATEGORY, NAME, VALUE) VALUES ('core', 'smtp.timeout', '5000');

INSERT INTO CONFIGURATION (CATEGORY, NAME, VALUE) VALUES ('core', 'smtp.auth', '0');

INSERT INTO CONFIGURATION (CATEGORY, NAME, VALUE) VALUES ('core', 'smtp.secure', '0');

INSERT INTO CONFIGURATION (CATEGORY, NAME, VALUE) VALUES ('core', 'server.queuebuffersize', '1000');