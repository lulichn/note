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

INSERT INTO usr(user_id, first_name, last_name, password, role_name) VALUES ('taro-ya,ada', 'Taro', 'Yamada', 'demo', 'USER');
INSERT INTO usr(user_id, first_name, last_name, password, role_name) VALUES ('a', 'A', 'A', 'demo', 'USER');
INSERT INTO usr(user_id, first_name, last_name, password, role_name) VALUES ('b', 'B', 'B', 'demo', 'USER');
INSERT INTO usr(user_id, first_name, last_name, password, role_name) VALUES ('c', 'C', 'C', 'demo', 'ADMIN');