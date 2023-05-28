CREATE DATABASE IF NOT EXISTS tennis_db

USE tennis_db;

CREATE TABLE IF NOT EXISTS notification_config (
    user_id VARCHAR(36) NOT NULL,
    PRIMARY KEY (user_id)
);