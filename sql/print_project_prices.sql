--Завдання №7 - вивести вартість кожного проєкту

SELECT project_worker.project_id, SUM(worker.salary * DATEDIFF('month', project.start_date, project.finish_date)) AS total_salary
FROM project_worker 
JOIN worker  ON  project_worker.worker_id = worker.id
JOIN project  ON  project_worker.project_id = project.id
GROUP BY project_worker.project_id;
