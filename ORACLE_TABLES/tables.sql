DROP TABLE AIRLINES;
DROP TABLE ARRIVALS;
DROP TABLE BAGGAGE;
DROP TABLE DEPARTURES;
DROP TABLE INCOMINGROUTES;
DROP TABLE OUTGOINGROUTES;
DROP TABLE PASSENGERS;
DROP TABLE ROUTES;
DROP TABLE GATES;


CREATE TABLE AIRLINES (
name VARCHAR(50),
website VARCHAR(50),
acode VARCHAR(50),
PRIMARY KEY (acode)
);


CREATE TABLE ROUTES(
rnum INT,
planeModel VARCHAR(50),
acode VARCHAR (50),
FOREIGN KEY (acode) REFERENCES AIRLINES (acode),
PRIMARY KEY (rnum, acode)
);

CREATE TABLE OUTGOINGROUTES(
acode VARCHAR (50),
rnum INT,
destination VARCHAR (50),
outT DATE,
FOREIGN KEY (acode, rnum) REFERENCES ROUTES (acode, rnum) ON DELETE CASCADE,
PRIMARY KEY (acode, rnum)
);

CREATE TABLE INCOMINGROUTES(
acode VARCHAR (50),
rnum INT,
source VARCHAR (50),
incT DATE,
FOREIGN KEY (acode, rnum) REFERENCES ROUTES (acode, rnum) ON DELETE CASCADE,
PRIMARY KEY (acode, rnum)
);

CREATE TABLE GATES (
GATE VARCHAR (30),
ISFREE CHAR (1),
PRIMARY KEY (GATE)
);

CREATE TABLE DEPARTURES (
acode VARCHAR (50),
rnum INT,
depID VARCHAR (50),
gate VARCHAR (30),
depT DATE,
FOREIGN KEY (acode, rnum) REFERENCES OUTGOINGROUTES (acode, rnum) ON DELETE CASCADE,
FOREIGN KEY (gate) REFERENCES GATES (gate),
PRIMARY KEY (depID)
);

CREATE TABLE ARRIVALS (
acode VARCHAR (50),
rnum INT,
arrID VARCHAR (50),
gate VARCHAR (30),
arrT DATE,
FOREIGN KEY (acode, rnum) REFERENCES INCOMINGROUTES (acode, rnum) ON DELETE CASCADE,
FOREIGN KEY (gate) REFERENCES GATES (gate),
PRIMARY KEY (arrID)
);

CREATE TABLE PASSENGERS (
pid VARCHAR(50),
name VARCHAR(50),
gov_issued_id VARCHAR (50),
dob DATE,
POB VARCHAR(50),
depID VARCHAR(50),
arrID VARCHAR(50),

PRIMARY KEY (pid),
FOREIGN KEY (depID) REFERENCES DEPARTURES,
FOREIGN KEY (arrID) REFERENCES ARRIVALS
);

CREATE TABLE BAGGAGE (
bID VARCHAR(50),
weight INT,
pID VARCHAR (50),
FOREIGN KEY (pID) REFERENCES PASSENGERS (pID),
PRIMARY KEY (bID)
);


---2 i)Constraint 1

CREATE OR REPLACE VIEW PassJoinArrDep AS
  SELECT PASSENGERS.PID, ARRIVALS.ARRID, DEPARTURES.DEPID, ARRIVALS.ARRT, DEPARTURES.DEPT
  FROM PASSENGERS JOIN ARRIVALS 
  ON PASSENGERS.arrID = ARRIVALS.arrID
  JOIN DEPARTURES ON
  PASSENGERS.depID = DEPARTURES.depID;



CREATE VIEW PassengersV AS 
  SELECT pID,name,gov_issued_id,dob,pob,arrID,depID --i.e. all attributes are made available
  FROM Passengers X
  WHERE NOT EXISTS (
    Select *
    FROM PassJoinArrDep Y
    WHERE 
    arrT > depT AND X.pid = Y.Pid
    --arrival time is greater than departure time for the tuple from X
  )
WITH CHECK OPTION;

--2 ii) constraint 2

CREATE OR REPLACE VIEW DepartureV AS 
  
  SELECT depID,depT,gate,acode,rnum   --i.e. all attributes are made available
  FROM Departures X
  WHERE NOT EXISTS 
  (SELECT *
  FROM DEPARTURES
  WHERE Gate = x.Gate and x.depid <> depid and (24*ABS(depT-x.depT)) <=1
  )
  
  UNION
   
  (SELECT *
   FROM Arrivals
   WHERE Gate =X.gate and ((24*ABS(arrT-x.depT))) <=1
  )
WITH CHECK OPTION;



Create VIEW DepartureV AS
SELECT *
From  Departures X 
Where not exists(
(Select *
 	FROM Departures 
	WHERE gate= X.gate and X.depId <> depId and 24*ABS(depT-X.depT) <= 1)
 	Union 
  	(SELECT* 
  	FROM Arrivals 
 	WHERE gate =X.gate and 24*ABS(arrT-X.depT) <= 1)
  )
WITH CHECK OPTION;

Create VIEW ArrivalV AS
SELECT *
From  Arrivals X
Where not exists(
(Select *
 	FROM Departures 
Where gate= X.gate  and 24*ABS(depT-X.arrT) <= 1)
 	Union 
  	(SELECT* 
  	FROM Arrivals 
 	WHERE gate =X.gate and X.arrId <> arrId and 24*ABS(arrT-X.arrT) <= 1)
  )
WITH CHECK OPTION;



INSERT INTO AIRLINES VALUES (
'Air Canada',
'aircanada.ca',
'A123'
);

INSERT INTO AIRLINES VALUES (
'West Jet',
'westjet.ca',
'B123'
);

INSERT INTO AIRLINES VALUES (
'American Airlines',
'aa.com',
'C123'
);

INSERT INTO ROUTES VALUES(
111,
'Jet',
'A123'
);

INSERT INTO ROUTES VALUES(
222,
'Jet',
'B123'
);

INSERT INTO ROUTES VALUES(
333,
'Jet',
'C123'
);

INSERT INTO OUTGOINGROUTES VALUES(
'A123',
111,
'Toronto',
TO_DATE ('2016-03-01 21:00', 'YYYY-MM-DD hh24:mi')
);

INSERT INTO INCOMINGROUTES VALUES(
'B123',
222,
'Vancouver',
TO_DATE ('2016-03-01 21:00', 'YYYY-MM-DD hh24:mi')
);

INSERT INTO INCOMINGROUTES VALUES(
'C123',
333,
'Victoria',
TO_DATE ('2016-03-01 21:00', 'YYYY-MM-DD hh24:mi')
);

INSERT INTO GATES VALUES (
'A55',
'Y'
);

INSERT INTO GATES VALUES (
'B55',
'Y'
);

INSERT INTO GATES VALUES (
'B65',
'N'
);

INSERT INTO DEPARTURES VALUES (
'A123',
111,
'aaa',
'A55',
TO_DATE('2016-03-01 21:00', 'YYYY-MM-DD hh24:mi')
);


INSERT INTO ARRIVALS VALUES (
'C123',
333,
'ccc',
'B65',
TO_DATE ('2016-03-01 21:00', 'YYYY-MM-DD hh24:mi')
);

INSERT INTO PASSENGERS VALUES (
'1',
'Vivi Trong',
'123456',
TO_DATE('1990-04-12', 'YYYY-MM-DD'),
'Nanaimo',
'aaa',
'ccc'
);


INSERT INTO PASSENGERS VALUES (
'2',
'John Doe',
'123456',
TO_DATE('1980-04-12', 'YYYY-MM-DD'),
'Edmonton',
'aaa',
'ccc'
);

INSERT INTO PASSENGERS VALUES (
'3',
'Ahmed Patel',
'123456',
TO_DATE('1985-03-12', 'YYYY-MM-DD'),
'Toronto',
'aaa',
'ccc'
);

INSERT INTO BAGGAGE VALUES (
'123',
55,
'1'
);

INSERT INTO BAGGAGE VALUES (
'345',
65,
'2'
);

INSERT INTO BAGGAGE VALUES (
'567',
45,
'2'
);



















