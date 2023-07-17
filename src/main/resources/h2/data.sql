-- Room 더미 데이터 삽입
INSERT INTO Room (name, size)
VALUES ('Room 1', '소'),
       ('Room 2', '중'),
       ('Room 3', '대');

-- Member 더미 데이터 삽입
INSERT INTO Member (sum_time)
VALUES (0),
       (0),
       (0),
       (0);

-- Reservation 더미 데이터 삽입
INSERT INTO Reservation (start_time, end_time, room, member)
VALUES ('2023-07-13 09:00:00', '2023-07-13 10:00:00', 1, 1),
       ('2023-07-13 14:00:00', '2023-07-13 16:00:00', 2, 2),
       ('2023-07-14 10:00:00', '2023-07-14 12:00:00', 2, 1),
       ('2023-07-14 13:00:00', '2023-07-14 14:00:00', 3, 3);
