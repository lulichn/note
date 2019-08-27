INSERT INTO meeting_room (room_name) VALUES ('新木場');
INSERT INTO meeting_room (room_name) VALUES ('辰巳');
INSERT INTO meeting_room (room_name) VALUES ('豊洲');
INSERT INTO meeting_room (room_name) VALUES ('月島');
INSERT INTO meeting_room (room_name) VALUES ('新富町');
INSERT INTO meeting_room (room_name) VALUES ('銀座一丁目');
INSERT INTO meeting_room (room_name) VALUES ('有楽町');

INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, 1);
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE + 1, 1);
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE - 1, 1);
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, 7);
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE + 1, 7);
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE - 1, 7);

INSERT INTO usr(user_id, first_name, last_name, role_name, password) VALUES ('taro-yamada', 'Taro', 'Yamada', 'USER', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK');
INSERT INTO usr(user_id, first_name, last_name, role_name, password) VALUES ('aaaa', 'A', 'A', 'USER', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK');
INSERT INTO usr(user_id, first_name, last_name, role_name, password) VALUES ('bbbb', 'B', 'B', 'USER', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK');
INSERT INTO usr(user_id, first_name, last_name, role_name, password) VALUES ('cccc', 'C', 'C', 'ADMIN', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK');