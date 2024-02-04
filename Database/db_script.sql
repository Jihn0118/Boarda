CREATE DATABASE  IF NOT EXISTS `boarda` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

use boarda;

#DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
	`num`	int	PRIMARY KEY auto_increment,
	`id`	varchar(50)	UNIQUE,
	`password`	varchar(200)	NULL,
	`nickname`	varchar(20)	UNIQUE,
	`birth`	varchar(20)	NULL,
	`gender`	char(1)	NULL,
	`profile_image`	text	NULL
);

#DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
	`id`	int	PRIMARY KEY auto_increment,
	`type`	tinyint	NULL,
	`title`	varchar(45)	NULL,
	`content`	text	NULL,
	`created_at`	timestamp DEFAULT CURRENT_TIMESTAMP ,
	`status`	char(1)	NULL,
	`member_id`	int	NOT NULL
);

#DROP TABLE IF EXISTS `boardgame`;

CREATE TABLE `boardgame` (
	`id`	int PRIMARY KEY auto_increment,
	`title`	varchar(100)	NULL,
	`min_num`	int	NULL,
	`max_num`	int	NULL,
	`time`	int	NULL,
	`age`	int	NULL,
	`difficulty`	float	NULL,
	`year`	year	NULL,
	`image`	text	NULL
);

#DROP TABLE IF EXISTS `chattingroom`;

CREATE TABLE `chatting_room` (
	`room_id`	int	PRIMARY KEY auto_increment,
	`room_name`	varchar(50)	NULL,
	`updated_at`	timestamp	DEFAULT CURRENT_TIMESTAMP
);

#DROP TABLE IF EXISTS `chatting`;

CREATE TABLE `chatting` (
	`chatting_id`	int PRIMARY KEY auto_increment,
	`room_id`	int	NOT NULL,
	`member_id`	int	NOT NULL,
	`entry_time`	timestamp	DEFAULT CURRENT_TIMESTAMP
);

#DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
	`message_id`	int	PRIMARY KEY auto_increment,
	`room_id`	int	NOT NULL,
	`member_id`	int	NOT NULL,
	`content`	text	NULL,
	`created_at`	timestamp	DEFAULT CURRENT_TIMESTAMP,
	`is_read`	int	NULL
);

#DROP TABLE IF EXISTS `image`;

CREATE TABLE `image` (
	`id`	int	PRIMARY KEY auto_increment,
	`name`	varchar(50)	NULL,
	`path`	varchar(100)	NULL,
	`type`	varchar(10)	NULL,
	`flag`	char(1)	NULL,
	`article_id`	int	NOT NULL,
	`review_id`	int	NOT NULL
);

#DROP TABLE IF EXISTS `cafe`;

CREATE TABLE `cafe` (
	`id`	int	PRIMARY KEY auto_increment,
	`brand`	varchar(10)	NULL,
	`branch`	varchar(10)	NULL,
	`location`	varchar(100)	NULL,
	`contact`	varchar(20)	NULL,
	`image`	text	NULL,
	`rate`	float	NULL,
	`latitude`	varchar(50)	NULL,
	`longitude`	varchar(50)	NULL
);

#DROP TABLE IF EXISTS `follow`;

CREATE TABLE `follow` (
	`id`	int	NOT NULL,
	`follower`	int	NOT NULL,
	`following`	int	NOT NULL,
	`flag`	char(1)	NULL,
	`created_at`	timestamp	DEFAULT CURRENT_TIMESTAMP
);

#DROP TABLE IF EXISTS `moimmember`;

CREATE TABLE `moim_member` (
	`mm_id`	int	PRIMARY KEY auto_increment,
	`member_id`	int	NOT NULL,
	`moim_id`	int	NOT NULL
);

#DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
	`id`	int	PRIMARY KEY auto_increment,
	`is_removed`	tinyint	NULL,
	`content`	text	NULL,
	`created_at`	timestamp	DEFAULT CURRENT_TIMESTAMP,
	`rate`	float	NULL,
	`status`	char(1)	NULL,
	`member_id`	int	NOT NULL,
	`cafe_id`	int	NOT NULL,
	`moim_id`	int	NOT NULL
);

#DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag` (
	`tag_id`	int PRIMARY KEY auto_increment,
	`review_id`	int	NOT NULL,
	`game_id`	int	NOT NULL
);

#DROP TABLE IF EXISTS `alarm`;

CREATE TABLE `alarm` (
	`id`	int	PRIMARY KEY auto_increment,
	`content`	text	NULL,
	`created_at`	timestamp	DEFAULT CURRENT_TIMESTAMP,
	`read_at`	tinyint	NULL,
	`member_num`	int	NOT NULL
);

#DROP TABLE IF EXISTS `moim`;

CREATE TABLE `moim` (
	`id`	int	PRIMARY KEY auto_increment,
	`status`	char(1)	NULL,
	`leader_nickname`	varchar(20)	NULL,
	`datetime`	datetime	NULL,
	`location`	varchar(100)	NULL,
	`number`	int	NULL,
	`created_at`	timestamp	DEFAULT CURRENT_TIMESTAMP,
	`title`	varchar(200)	NULL,
	`content`	text	NULL,
	`current_number`	int	NULL
);

#DROP TABLE IF EXISTS `ranking`;

CREATE TABLE `ranking` (
	`id`	int	PRIMARY KEY auto_increment,
	`flag`	char(2)	NULL,
	`num`	int	NULL,
	`created_at`	timestamp	DEFAULT CURRENT_TIMESTAMP,
	`cafe_id`	int	NOT NULL,
	`game_id`	int	NOT NULL
);

ALTER TABLE `follow` ADD CONSTRAINT `FK_Member_TO_Follow_1` FOREIGN KEY (
	`following`
)
REFERENCES `member` (
	`num`
);

ALTER TABLE `follow` ADD CONSTRAINT `FK_Member_TO_Follow_2` FOREIGN KEY (
	`follower`
)
REFERENCES `member` (
	`num`
);

ALTER TABLE `chatting` ADD CONSTRAINT `FK_ChattingRoom_TO_Chatting_1` FOREIGN KEY (
	`room_id`
)
REFERENCES `chatting_room` (
	`room_id`
);

ALTER TABLE `chatting` ADD CONSTRAINT `FK_Member_TO_Chatting_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `member` (
	`num`
);

ALTER TABLE `message` ADD CONSTRAINT `FK_Chatting_TO_Message_1` FOREIGN KEY (
	`room_id`
)
REFERENCES `chatting` (
	`room_id`
);

ALTER TABLE `message` ADD CONSTRAINT `FK_Chatting_TO_Message_2` FOREIGN KEY (
	`member_id`
)
REFERENCES `chatting` (
	`member_id`
);

ALTER TABLE `moim_member` ADD CONSTRAINT `FK_Member_TO_MoimMember_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `member` (
	`num`
);

ALTER TABLE `moim_member` ADD CONSTRAINT `FK_Moim_TO_MoimMember_1` FOREIGN KEY (
	`moim_id`
)
REFERENCES `moim` (
	`id`
);
