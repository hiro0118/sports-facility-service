CREATE DATABASE IF NOT EXISTS tennis_db;

USE tennis_db;

CREATE TABLE IF NOT EXISTS user (
    id VARCHAR(8) NOT NULL,
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
    postal_code INT(7) NOT NULL,
    address VARCHAR(100) NOT NULL,
    num_of_courts INT(2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS notification_config (
    user_id VARCHAR(8) NOT NULL,
    enabled BOOLEAN NOT NULL,
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES user(id)
)

CREATE TABLE IF NOT EXISTS notification_day (
    user_id VARCHAR(8) NOT NULL,
    day VARCHAR(3) NOT NULL,
    PRIMARY KEY (user_id, day),
    FOREIGN KEY (user_id) REFERENCES user(id)
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

CREATE TABLE IF NOT EXISTS notification_date_exclusion (
    user_id VARCHAR(8) NOT NULL,
    year VARCHAR(4) NOT NULL,
    month VARCHAR(2) NOT NULL,
    date VARCHAR(2) NOT NULL,
    PRIMARY KEY (user_id, year, month, date),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE IF NOT EXISTS raffle_status (
    date VARCHAR(8) NOT NULL,
    time VARCHAR(50) NOT NULL,
    park_id VARCHAR(50) NOT NULL,
    num_of_applications INT(2) NOT NULL,
    PRIMARY KEY (date, time, park_id),
    FOREIGN KEY (time) REFERENCES time(time),
    FOREIGN KEY (park_id) REFERENCES park(id)
);
