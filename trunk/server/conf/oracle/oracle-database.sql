-- SET ECHO ON

BEGIN
	FOR R1 IN (SELECT TABLE_NAME FROM USER_TABLES) LOOP
		BEGIN
			EXECUTE IMMEDIATE 'DROP TABLE ' || R1.TABLE_NAME;
			EXCEPTION WHEN OTHERS THEN NULL;
		END;
	END LOOP;
END;
/

DROP TABLE SCHEMA_INFO

CREATE TABLE SCHEMA_INFO
	(VERSION VARCHAR(40))

DROP SEQUENCE EVENT_SEQUENCE

CREATE SEQUENCE EVENT_SEQUENCE START WITH 1 INCREMENT BY 1

DROP TABLE EVENT

CREATE TABLE EVENT
	(ID INTEGER NOT NULL PRIMARY KEY,
	DATE_CREATED TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	EVENT CLOB NOT NULL,
	EVENT_LEVEL VARCHAR(40) NOT NULL,
	DESCRIPTION CLOB,
	ATTRIBUTES CLOB)

DROP TABLE CHANNEL

CREATE TABLE CHANNEL
	(ID VARCHAR(255) NOT NULL PRIMARY KEY,
	NAME VARCHAR(40) NOT NULL,
	DESCRIPTION CLOB,
	IS_ENABLED CHAR(1),
	VERSION VARCHAR(40),
	REVISION INTEGER,
	LAST_MODIFIED TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	SOURCE_CONNECTOR CLOB,
	DESTINATION_CONNECTORS CLOB,
	PROPERTIES CLOB,
	PREPROCESSING_SCRIPT CLOB,
	POSTPROCESSING_SCRIPT CLOB,
	DEPLOY_SCRIPT CLOB,
	SHUTDOWN_SCRIPT CLOB)
	
DROP TABLE CHANNEL_STATISTICS

CREATE TABLE CHANNEL_STATISTICS
	(SERVER_ID VARCHAR(255) NOT NULL,
	CHANNEL_ID VARCHAR(255) NOT NULL REFERENCES CHANNEL(ID) ON DELETE CASCADE,
	RECEIVED INTEGER,
	FILTERED INTEGER,
	SENT INTEGER,
	ERROR INTEGER,
	QUEUED INTEGER,
	ALERTED INTEGER,
	PRIMARY KEY(SERVER_ID, CHANNEL_ID))

DROP SEQUENCE MESSAGE_SEQUENCE

CREATE SEQUENCE MESSAGE_SEQUENCE START WITH 1 INCREMENT BY 1

DROP TABLE ATTACHMENT

CREATE TABLE ATTACHMENT
    (ID VARCHAR(255) NOT NULL PRIMARY KEY,
     MESSAGE_ID VARCHAR(255) NOT NULL,
     ATTACHMENT_DATA BLOB,
     ATTACHMENT_SIZE INTEGER,
     ATTACHMENT_TYPE VARCHAR(40))

CREATE INDEX ATTACHMENT_INDEX1 ON ATTACHMENT(MESSAGE_ID)

CREATE INDEX ATTACHMENT_INDEX2 ON ATTACHMENT(ID) 

DROP TABLE MESSAGE

CREATE TABLE MESSAGE
	(SEQUENCE_ID INTEGER NOT NULL PRIMARY KEY,
	ID VARCHAR(255) NOT NULL,
	SERVER_ID VARCHAR(255) NOT NULL,
	CHANNEL_ID VARCHAR(255) NOT NULL REFERENCES CHANNEL(ID) ON DELETE CASCADE,
	SOURCE VARCHAR(255),
	TYPE VARCHAR(255),
	DATE_CREATED TIMESTAMP NOT NULL,
	VERSION VARCHAR(40),
	IS_ENCRYPTED CHAR(1) NOT NULL,
	STATUS VARCHAR(40),
	RAW_DATA CLOB,
	RAW_DATA_PROTOCOL VARCHAR(40),
	TRANSFORMED_DATA CLOB,
	TRANSFORMED_DATA_PROTOCOL VARCHAR(40),
	ENCODED_DATA CLOB,
	ENCODED_DATA_PROTOCOL VARCHAR(40),
	CONNECTOR_MAP CLOB,
	CHANNEL_MAP CLOB,
	RESPONSE_MAP CLOB,
	CONNECTOR_NAME VARCHAR(255),
	ERRORS CLOB,
	CORRELATION_ID VARCHAR(255),
	ATTACHMENT SMALLINT,	
	UNIQUE (ID))

CREATE INDEX MESSAGE_INDEX1 ON MESSAGE(CHANNEL_ID, DATE_CREATED)

CREATE INDEX MESSAGE_INDEX2 ON MESSAGE(CHANNEL_ID, DATE_CREATED, CONNECTOR_NAME)

CREATE INDEX MESSAGE_INDEX3 ON MESSAGE(CHANNEL_ID, DATE_CREATED, RAW_DATA_PROTOCOL)

CREATE INDEX MESSAGE_INDEX4 ON MESSAGE(CHANNEL_ID, DATE_CREATED, SOURCE)

CREATE INDEX MESSAGE_INDEX5 ON MESSAGE(CHANNEL_ID, DATE_CREATED, STATUS)

CREATE INDEX MESSAGE_INDEX6 ON MESSAGE(CHANNEL_ID, DATE_CREATED, TYPE)

CREATE INDEX MESSAGE_INDEX7 ON MESSAGE(CORRELATION_ID, CONNECTOR_NAME)

CREATE INDEX MESSAGE_INDEX8 ON MESSAGE(ATTACHMENT)

DROP TABLE SCRIPT

CREATE TABLE SCRIPT
	(GROUP_ID VARCHAR(255) NOT NULL,
	ID VARCHAR(255) NOT NULL,
	SCRIPT CLOB,
	PRIMARY KEY(GROUP_ID, ID))

DROP TABLE TEMPLATE

CREATE TABLE TEMPLATE
	(GROUP_ID VARCHAR(255) NOT NULL,
	ID VARCHAR(255) NOT NULL,
	TEMPLATE CLOB,
	PRIMARY KEY(GROUP_ID, ID))

DROP SEQUENCE PERSON_SEQUENCE	

CREATE SEQUENCE PERSON_SEQUENCE START WITH 1 INCREMENT BY 1

DROP TABLE PERSON

CREATE TABLE PERSON
	(ID INTEGER NOT NULL PRIMARY KEY,
	USERNAME VARCHAR(40) NOT NULL,
	PASSWORD VARCHAR(40) NOT NULL,
	SALT VARCHAR(40) NOT NULL,
	FIRSTNAME VARCHAR(40),
	LASTNAME VARCHAR(40),
	ORGANIZATION VARCHAR(255),
	EMAIL VARCHAR(255),
	PHONENUMBER VARCHAR(40),
	DESCRIPTION VARCHAR(255),
	LAST_LOGIN TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	LOGGED_IN CHAR(1) NOT NULL)

DROP TABLE ALERT

CREATE TABLE ALERT
	(ID VARCHAR(255) NOT NULL PRIMARY KEY,
	NAME VARCHAR(40) NOT NULL,
	IS_ENABLED CHAR(1) NOT NULL,
	EXPRESSION CLOB,
	TEMPLATE CLOB,
	SUBJECT VARCHAR(998))
	
DROP TABLE CODE_TEMPLATE

CREATE TABLE CODE_TEMPLATE
	(ID VARCHAR(255) NOT NULL PRIMARY KEY,
	NAME VARCHAR(40) NOT NULL,
	CODE_SCOPE VARCHAR(40) NOT NULL,
	CODE_TYPE VARCHAR(40) NOT NULL,
	TOOLTIP VARCHAR(255) NOT NULL,
	CODE CLOB)
	
DROP TABLE CHANNEL_ALERT

CREATE TABLE CHANNEL_ALERT
	(CHANNEL_ID VARCHAR(255) NOT NULL,
	ALERT_ID VARCHAR(255) NOT NULL REFERENCES ALERT(ID) ON DELETE CASCADE)

DROP TABLE ALERT_EMAIL

CREATE TABLE ALERT_EMAIL
	(ALERT_ID VARCHAR(255) NOT NULL REFERENCES ALERT(ID) ON DELETE CASCADE,
	EMAIL VARCHAR(255) NOT NULL)

DROP SEQUENCE CONFIGURATION_SEQUENCE

CREATE SEQUENCE CONFIGURATION_SEQUENCE START WITH 1 INCREMENT BY 1

DROP TABLE CONFIGURATION

CREATE TABLE CONFIGURATION
	(CATEGORY VARCHAR(255) NOT NULL,
	NAME VARCHAR(255) NOT NULL,
	VALUE CLOB NOT NULL)
	
DROP TABLE ENCRYPTION_KEY

CREATE TABLE ENCRYPTION_KEY
	(DATA CLOB NOT NULL)

INSERT INTO PERSON (ID, USERNAME, PASSWORD, SALT, LOGGED_IN) VALUES (PERSON_SEQUENCE.NEXTVAL, 'admin', 'NdgB6ojoGb/uFa5amMEyBNG16mE=', 'Np+FZYzu4M0=', '0')

INSERT INTO SCHEMA_INFO (VERSION) VALUES ('7')

INSERT INTO CONFIGURATION (CATEGORY, NAME, VALUE) VALUES ('core', 'update.url', 'http://updates.mirthcorp.com')

INSERT INTO CONFIGURATION (CATEGORY, NAME, VALUE) VALUES ('core', 'update.enabled', '1')

INSERT INTO CONFIGURATION (CATEGORY, NAME, VALUE) VALUES ('core', 'stats.enabled', '1')

INSERT INTO CONFIGURATION (CATEGORY, NAME, VALUE) VALUES ('core', 'firstlogin', '1')

INSERT INTO CONFIGURATION (CATEGORY, NAME, VALUE) VALUES ('core', 'server.resetglobalvariables', '1')

INSERT INTO CONFIGURATION (CATEGORY, NAME, VALUE) VALUES ('core', 'smtp.auth', '0')

INSERT INTO CONFIGURATION (CATEGORY, NAME, VALUE) VALUES ('core', 'smtp.secure', '0')

COMMIT