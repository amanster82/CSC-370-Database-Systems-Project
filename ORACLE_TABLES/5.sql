
--5 a)
SELECT R1.rnum, R1.acode, R2.rnum, R2.acode
FROM OUTGOINGROUTES R2, INCOMINGROUTES R1
WHERE 24*ABS(R2.outT - R1.incT) BETWEEN 1 AND 12;


--5 b)
CREATE OR REPLACE VIEW TRANSIT AS
  SELECT R1.rnum AS ARNUM, R1.acode AS AACODE, R2.rnum AS DRNUM, R2.acode AS DACODE
  FROM OUTGOINGROUTES R2, INCOMINGROUTES R1
  WHERE 24*ABS(R2.outT - R1.incT) BETWEEN 1 AND 12;
  
  
  SELECT ARRID, DEPID
  FROM TRANSIT JOIN ARRIVALS ON
  TRANSIT.ARNUM = ARRIVALS.RNUM AND TRANSIT.AACODE = ARRIVALS.ACODE
  JOIN DEPARTURES ON
  TRANSIT.ARNUM = DEPARTURES.RNUM AND TRANSIT.AACODE = DEPARTURES.ACODE;
  
  
--5 c)
SELECT *
FROM (SELECT GOV_ISSUED_ID, name, count(GOV_ISSUED_ID) as count
      FROM passengers
      GROUP BY GOV_ISSUED_ID, name
      ORDER BY count DESC)
WHERE rownum <= 3;


--5 d)
Create View arrivalDelayed As
Select *
From airlines join arrivals using(acode)
Where status = 'delayed';

Create View departureDelayed As
Select *
From airlines join departures using(acode)
Where status = 'delayed';

	Select name, arrDelayed + depDelayed
From
(Select name, count(status) as arrDelayed
From arrivalDelayed
Group By name)
join
(Select name, count(status) as depDelayed
From departureDelayed
Group By name) using(name);




      

  
  
