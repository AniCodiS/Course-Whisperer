-- Insert initial data for the "passed_subject" table
INSERT INTO "passed_subject" (created_at, modified_at, username, subject, subject_code, grade, grade_score)
VALUES
-- For user: johndoe
('2024-04-16 13:10:00', '2024-04-16 13:10:00', 'johndoe', 'Mathematics', 'MATH101', 'A', 95),
('2024-04-16 13:15:00', '2024-04-16 13:15:00', 'johndoe', 'Physics', 'PHYS101', 'B', 85),
('2024-04-16 13:20:00', '2024-04-16 13:20:00', 'johndoe', 'English Literature', 'ENG101', 'A', 98),
('2024-04-16 13:25:00', '2024-04-16 13:25:00', 'johndoe', 'Computer Science', 'CS101', 'A', 92),
('2024-04-16 13:30:00', '2024-04-16 13:30:00', 'johndoe', 'Psychology', 'PSYCH101', 'B', 88),
('2024-04-16 13:35:00', '2024-04-16 13:35:00', 'johndoe', 'History', 'HIST101', 'B', 85),
-- For user: alicesmith
('2024-04-16 13:40:00', '2024-04-16 13:40:00', 'alicesmith', 'Mathematics', 'MATH101', 'A', 97),
('2024-04-16 13:45:00', '2024-04-16 13:45:00', 'alicesmith', 'Physics', 'PHYS101', 'A', 91),
('2024-04-16 13:50:00', '2024-04-16 13:50:00', 'alicesmith', 'English Literature', 'ENG101', 'B', 88),
('2024-04-16 13:55:00', '2024-04-16 13:55:00', 'alicesmith', 'Computer Science', 'CS101', 'A', 95),
('2024-04-16 14:00:00', '2024-04-16 14:00:00', 'alicesmith', 'Psychology', 'PSYCH101', 'A', 94),
('2024-04-16 14:05:00', '2024-04-16 14:05:00', 'alicesmith', 'History', 'HIST101', 'A', 90),
-- For user: bobjones
('2024-04-16 14:10:00', '2024-04-16 14:10:00', 'bobjones', 'Mathematics', 'MATH101', 'B', 82),
('2024-04-16 14:15:00', '2024-04-16 14:15:00', 'bobjones', 'Physics', 'PHYS101', 'A', 93),
('2024-04-16 14:20:00', '2024-04-16 14:20:00', 'bobjones', 'English Literature', 'ENG101', 'A', 90),
('2024-04-16 14:25:00', '2024-04-16 14:25:00', 'bobjones', 'Computer Science', 'CS101', 'B', 87),
('2024-04-16 14:30:00', '2024-04-16 14:30:00', 'bobjones', 'Psychology', 'PSYCH101', 'A', 94),
('2024-04-16 14:35:00', '2024-04-16 14:35:00', 'bobjones', 'History', 'HIST101', 'B', 85);
-- Add more data for other users as needed...
