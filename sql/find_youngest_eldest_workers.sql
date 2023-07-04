--Завдання №6 - знайти найстаршого та наймолодшого працівника
SELECT 'YOUNGEST' AS TYPE,name,birthday FROM worker
WHERE birthday IN(
SELECT MAX(birthday) FROM worker)

UNION

SELECT 'ELDEST',name,birthday FROM worker
WHERE birthday IN(
SELECT MIN(birthday) FROM worker);