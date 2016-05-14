SELECT rnum
FROM Airlines NATURAL JOIN ROUTES
WHERE Name = 'Air Canada';

Select rnum, acode
From OutgoingRoutes
Where destination = 'Toronto' 
UNION
Select rnum, acode
From IncomingRoutes
Where source = 'Toronto' ;

Select rnum, acode, "status"?
From Departures Natural Join Arrivals
Where 24*ABS(INPUTTIME - arrT) BETWEEN -1 AND 1 OR
Where 24*ABS(INPUTTIME - depT) BETWEEN -1 AND 1;


SELECT *
FROM DEPARTURES
WHERE DEPARTURES.DEPT = TO_DATE('2016/11/11', 'yyyy/mm/dd');

UNION
SELECT *
FROM ARRIVALS
WHERE ARRIVALS.ARRT = '2016/11/11';

Select *
From Passengers;PID
Where depID = INPUT OR arrID = INPUT;

INSERT INTO PASSENGERS VALUES (
'Test',
'Test',
'Test',
DATE_TO('2011/11/11', 'yyyy/mm/dd'),
'Test',
'Test',
'Test',
);

Select rnum
From Airlines Natural Join Routes
Where name = INPUTAIRLINE;

Select rnum, acode
From OutgoingRoutes
Where destination = INPUTCITY
UNION
Select rnum, acode
From IncomingRoutes
Where source = INPUTCITY;

Select rnum, acode
From Departures Natural Join Arrivals
Where 24*ABS(INPUTTIME - arrT) BETWEEN -1 AND 1 OR
Where 24*ABS(INPUTTIME - depT) BETWEEN -1 AND 1;

Select *
From Passengers
Where depID = INPUT OR arrID = INPUT;

SELECT bid FROM BAGGAGE WHERE pid = 'dfdgsfdg';

SELECT GATE
FROM GATES, ARRIVALS
WHERE arrID = 'input' AND ISFREE = 'Y';

UNION

SELECT GATE
FROM GATES, DEPARTURES
WHERE depID = 'input' AND ISFREE = 'Y';




SELECT rnum, acode 
FROM DEPARTURES Natural Join Arrivals; 
WHERE 24*ABS(2016/11/11 11))-arrT)BETWEEN -1 AND 1;



INSERT INTO PASSENGERS VALUES (
'pid2',
'name2',
'gov_id2',
'2015/02/11',
'test2',
'test2',
'test2'
);
