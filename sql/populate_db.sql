--Завдання №2 - наповнити БД
INSERT INTO worker (name,birthday,level,salary) VALUES
('John Doe','1990-05-15','Senior',75000),
('Emily Smith', '1988-11-30', 'Middle', 60000),
('Michael Johnson', '1992-07-02', 'Junior', 40000),
('Jessica Brown', '1991-09-18', 'Middle', 55000),
('William Davis', '1993-04-10', 'Trainee', 25000),
('Olivia Wilson', '1990-03-25', 'Senior', 80000),
('Ethan Thompson', '1989-06-08', 'Middle', 55000),
('Sophia Anderson', '1992-12-14', 'Junior', 38000),
('Alexander Martinez', '1991-08-21', 'Junior', 42000),
('Ava Taylor', '1994-02-05', 'Senior', 90000);

INSERT INTO client (name) VALUES
('Daniel Johnson'),
('Isabella Garcia'),
('James Miller'),
('Mia Davis'),
('Benjamin Wilson');

INSERT INTO project (client_id,start_date,finish_date) VALUES
(4, '2022-01-15', '2022-05-05'),
(4, '2022-03-10', '2022-07-20'),
(1, '2022-06-01', '2022-09-30'),
(5, '2022-08-15', '2023-02-28'),
(2, '2022-10-10', '2023-01-05'),
(1, '2023-02-01', '2023-04-30'),
(3, '2023-05-15', '2023-08-10'),
(4, '2023-07-20', '2023-12-31'),
(5, '2023-09-05', '2024-01-20'),
(2, '2024-03-01', '2024-05-31');

INSERT INTO project_worker(project_id,worker_id) VALUES
(1,1),
(1,5),
(1,10),
(2,3),
(3,6),
(3,1),
(4,1),
(5,10),
(5,5),
(6,9),
(6,2),
(7,7),
(8,7),
(9,5),
(9,10),
(10,4);

