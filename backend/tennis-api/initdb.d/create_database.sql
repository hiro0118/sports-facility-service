CREATE DATABASE IF NOT EXISTS tennis_db;

USE tennis_db;

CREATE TABLE IF NOT EXISTS user (
    id VARCHAR(36) NOT NULL,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);
INSERT INTO user (id, name) VALUES ('00000001', 'John Doe');

CREATE TABLE IF NOT EXISTS time (
    time VARCHAR(9) NOT NULL,
    PRIMARY KEY (time)
);
INSERT INTO time (time) VALUES ('0900-1100');
INSERT INTO time (time) VALUES ('1100-1300');
INSERT INTO time (time) VALUES ('1300-1500');
INSERT INTO time (time) VALUES ('1500-1700');
INSERT INTO time (time) VALUES ('1700-1900');
INSERT INTO time (time) VALUES ('1900-2100');

CREATE TABLE IF NOT EXISTS day (
    day VARCHAR(3) NOT NULL,
    PRIMARY KEY (day)
);
INSERT INTO day (day) VALUES ('mon');
INSERT INTO day (day) VALUES ('tue');
INSERT INTO day (day) VALUES ('wed');
INSERT INTO day (day) VALUES ('thu');
INSERT INTO day (day) VALUES ('fri');
INSERT INTO day (day) VALUES ('sat');
INSERT INTO day (day) VALUES ('sun');

CREATE TABLE IF NOT EXISTS park (
    id VARCHAR(8) NOT NULL,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);
INSERT INTO park (id, name) VALUES ('1', 'parkA');
INSERT INTO park (id, name) VALUES ('2', 'parkB');

CREATE TABLE IF NOT EXISTS notification_day (
    user_id VARCHAR(8) NOT NULL,
    day VARCHAR(3) NOT NULL,
    PRIMARY KEY (user_id, day),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (day) REFERENCES day(day)
);
INSERT INTO notification_day (user_id, day) VALUES ('00000001', 'sat');
INSERT INTO notification_day (user_id, day) VALUES ('00000001', 'sun');

CREATE TABLE IF NOT EXISTS notification_time (
    user_id VARCHAR(8) NOT NULL,
    time VARCHAR(9) NOT NULL,
    PRIMARY KEY (user_id, time),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (time) REFERENCES time(time)
);
INSERT INTO notification_time (user_id, time) VALUES ('00000001', '0900-1100');
INSERT INTO notification_time (user_id, time) VALUES ('00000001', '1100-1300');
INSERT INTO notification_time (user_id, time) VALUES ('00000001', '1300-1500');
INSERT INTO notification_time (user_id, time) VALUES ('00000001', '1500-1700');
INSERT INTO notification_time (user_id, time) VALUES ('00000001', '1700-1900');

CREATE TABLE IF NOT EXISTS notification_park (
    user_id VARCHAR(8) NOT NULL,
    park_id VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_id, park_id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (park_id) REFERENCES park(id)
);
INSERT INTO notification_park (user_id, park_id) VALUES ('00000001', '1');