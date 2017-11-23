DROP DATABASE IF EXISTS GameData;
CREATE DATABASE GameData;

USE GameData;

CREATE TABLE Users(
	`userID` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `username` VARCHAR(20) NOT NULL,
    `password` VARCHAR(33) NOT NULL,
    `money` INT(6) NOT NULL, 
    `wins` INT(20) NOT NULL DEFAULT '0',
    `losses` INT(20) NOT NULL DEFAULT '0',
    `invisibility` INT(20) NOT NULL DEFAULT '0',
    `weakPotion` INT(20) NOT NULL DEFAULT '0',
    `strongPotion` INT(20) NOT NULL DEFAULT '0'
);

INSERT INTO Users(username, `password`, `money`)
	VALUE ('guest','', '0');

CREATE TABLE Settings(
	`userID` INT(11) NOT NULL,
	`currentSprite` INT(10) NOT NULL DEFAULT '1',
    `nickname` VARCHAR(10) NOT NULL,
	FOREIGN KEY fk1(userID) REFERENCES Users(userID)
);

INSERT INTO Settings(userID, nickname)
VALUE ('1', 'guest');


