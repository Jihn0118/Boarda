# 멤버 데이터 삽입 
INSERT INTO `member` (`id`, `password`, `nickname`, `birth`, `gender`, `profile_image`)
VALUES ("a@a.com", "pwd", "닉네임1", "980118", 'M', "czxcvxczv.png"),
("b@b.com", "pwd", "닉네임2", "980123", 'W', "gpf.png"),
("c@c.com", "pwd", "닉네임3", "000112", 'M', "이미지3.png"),
("d@d.com", "pwd", "닉네임4", "990118", 'M', "czxcvxczv.png"),
("do@do.com", "pwd", "김도훈", "980122", 'M', "이미지_김도훈.png"),
("jin@ho.com", "pwd", "김진호", "980118", 'M', "이미지_김진호.png"),
("su@su.com", "pwd", "안수진", "040818", 'M', "이미지_안수진.png"),
("ho0@hoho.com", "pwd", "유호영", "050918", 'M', "이미지_유호영.png"),
("ji@ji.com", "pwd", "이지은", "980118", 'W', "이미지_이지은.png"),
("so@so.com", "pwd", "박소영", "050718", 'M', "이미지_박소영.png");

# 모임 데이터 삽입 
INSERT INTO `moim` (`status`, `datetime`, `location`, `number`, `title`, `content`)
VALUES ('P', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 1 DAY), "서울시 강남구", 4,  "강남 모임!", "모아요"),
('F', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 1 DAY), "서울시 강남구", 8,  "강남 모임 실패!", "모아요"),
('S', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 1 DAY), "서울시 강남구", 3,  "강남 모임 완료요!", "모아요"),
('P', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 1 DAY), "서울시 마포구", 4,  "마포 모임!", "모아요"),
('F', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 1 DAY), "서울시 마포구", 8,  "마포 모임 실패!", "모아요"),
('P', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 1 DAY), "서울시 마포구", 3,  "마포 모임중!", "모아요"),
('S', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 1 DAY), "서울시 강서구", 4, "강서 모임 완료!", "모아요"),
('P', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 1 DAY), "서울시 강서구", 4, "강서 모임!", "모아요");

# 모임 멤버 데이터 삽입
INSERT INTO `moim_member`(member_id, moim_id)
values (1, 1), 
(2, 1),
(3, 1),
(4, 3),
(5, 3),
(1, 3),
(2, 2),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(2, 7),
(5, 7),
(7, 7),
(1, 8);