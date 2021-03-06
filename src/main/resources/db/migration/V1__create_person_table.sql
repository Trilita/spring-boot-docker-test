DROP TABLE IF EXISTS PERSON;
DROP TABLE IF EXISTS JOB;

CREATE TABLE PERSON(
  ID SERIAL PRIMARY KEY,
  NAME TEXT NOT NULL
);

ALTER TABLE PERSON ADD COLUMN ENTRY_DATE DATE DEFAULT CURRENT_DATE;
ALTER TABLE PERSON
 ADD COLUMN JOB INTEGER NOT NULL,
 ADD FOREIGN KEY (JOB) REFERENCES JOB (ID);

GRANT SELECT, INSERT ON PERSON TO ${user};