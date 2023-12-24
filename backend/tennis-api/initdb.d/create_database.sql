CREATE DATABASE IF NOT EXISTS tennis_db;

USE tennis_db;

CREATE TABLE IF NOT EXISTS user (
    id VARCHAR(36) NOT NULL,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS time (
    time VARCHAR(9) NOT NULL,
    PRIMARY KEY (time)
);

CREATE TABLE IF NOT EXISTS park (
    id VARCHAR(8) NOT NULL,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS notification_day (
    user_id VARCHAR(8) NOT NULL,
    day VARCHAR(3) NOT NULL,
    PRIMARY KEY (user_id, day),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (day) REFERENCES day(day)
);

CREATE TABLE IF NOT EXISTS notification_time (
    user_id VARCHAR(8) NOT NULL,
    time VARCHAR(9) NOT NULL,
    PRIMARY KEY (user_id, time),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (time) REFERENCES time(time)
);

CREATE TABLE IF NOT EXISTS notification_park (
    user_id VARCHAR(8) NOT NULL,
    park_id VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_id, park_id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (park_id) REFERENCES park(id)
);
