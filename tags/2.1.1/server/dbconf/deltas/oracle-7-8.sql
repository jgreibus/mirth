ALTER TABLE CONFIGURATION ADD VALUE_TEMP CLOB

UPDATE CONFIGURATION SET VALUE_TEMP = VALUE

ALTER TABLE CONFIGURATION DROP COLUMN VALUE

ALTER TABLE CONFIGURATION RENAME COLUMN VALUE_TEMP TO VALUE

INSERT INTO CONFIGURATION (CATEGORY, NAME, VALUE) VALUES ('core', 'server.maxqueuesize', '0')

DROP TABLE EVENT

CREATE TABLE EVENT
	(ID INTEGER NOT NULL PRIMARY KEY,
	DATE_CREATED TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	NAME CLOB NOT NULL,
	EVENT_LEVEL VARCHAR(40) NOT NULL,
	OUTCOME VARCHAR(40) NOT NULL,
	ATTRIBUTES CLOB,
	USER_ID INTEGER NOT NULL,
	IP_ADDRESS VARCHAR(40))

ALTER TABLE SCRIPT MODIFY GROUP_ID VARCHAR(40)

ALTER TABLE SCRIPT MODIFY ID VARCHAR(40)

ALTER TABLE CODE_TEMPLATE MODIFY TOOLTIP VARCHAR(255) NULL
