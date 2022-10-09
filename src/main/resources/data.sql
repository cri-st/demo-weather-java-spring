CREATE DATABASE IF NOT EXISTS mysql_db;

CREATE USER IF NOT EXISTS 'mysql_user'@'localhost' IDENTIFIED BY 'MpNB7Daz8_QL7RYWPF9E';

GRANT ALL PRIVILEGES ON *.* TO 'mysql_user'@'localhost' WITH GRANT OPTION;

CREATE USER IF NOT EXISTS 'mysql_user'@'%' IDENTIFIED BY 'MpNB7Daz8_QL7RYWPF9E';

GRANT ALL PRIVILEGES ON *.* TO 'mysql_user'@'%' WITH GRANT OPTION;

CREATE TABLE IF NOT EXISTS mysql_db.user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nickname VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT UNIQUE (nickname),
    CONSTRAINT UNIQUE (email)
);

INSERT IGNORE INTO mysql_db.user (nickname, role, email, password) VALUES ('admin', 'ADMIN', 'mail@cri.st', '$2a$10$AalBFBrlTJYOKKgLPvWG0elUUqSostHuycgucfiia9SiY48WF6/Jy');

FLUSH PRIVILEGES;