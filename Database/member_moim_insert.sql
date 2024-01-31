# 멤버 데이터 삽입 
INSERT INTO `member` (`id`, `password`, `nickname`, `birth`, `gender`, `profile_image`)
VALUES ("a@a.com", "pwd", "닉네임1", "980118", 'M', "czxcvxczv.png"),
("b@b.com", "pwd", "닉네임2", "980123", 'W', "gpf.png"),
("c@c.com", "pwd", "닉네임3", "000112", 'M', "이미지3.png"),
("d@d.com", "pwd", "닉네임4", "990118", 'M', "czxcvxczv.png"),
("do@do.com", "pwd", "김도훈", "980122", 'M', "이미지_김도훈.png"),
("jin@ho.com", "pwd", "진호", "980118", 'M', "이미지_김진호.png"),
("su@su.com", "pwd", "안수진", "040818", 'M', "이미지_안수진.png"),
("ho0@hoho.com", "pwd", "유호영", "050918", 'M', "이미지_유호영.png"),
("ji@ji.com", "pwd", "이지은", "980118", 'W', "이미지_이지은.png"),
("so@so.com", "pwd", "박소영", "050718", 'M', "이미지_박소영.png"),
("lantern50@naver.com", "pwd", "김진호", "980118", 'M', "image.png"),
("2lantern50@naver.com", "pwd", "2김진호", "980118", 'M', "2image.png"),
("3lantern50@naver.com", "pwd", "3김진호", "980118", 'M', "3image.png");

# 보드게임 데이터 삽입
INSERT INTO `boardgame` (`title`, `min_num`, `max_num`, `time`, `age`, `difficulty`, `year`, `image`) VALUES 
('카탄의 개척자들', 3, 4, 120, 10, 2.5, '1995', 'catan.jpg'),
('티켓 투 라이드', 2, 5, 60, 8, 1.9, '2004', 'ticket_to_ride.jpg'),
('카르카손네', 2, 5, 45, 7, 1.9, '2000', 'carcassonne.jpg'),
('팬데믹', 2, 4, 45, 8, 2.4, '2008', 'pandemic.jpg'),
('아그리콜라', 1, 5, 150, 12, 3.6, '2007', 'agricola.jpg'),
('7 원더스', 2, 7, 30, 10, 2.3, '2010', '7_wonders.jpg'),
('도미니언', 2, 4, 30, 13, 2.4, '2008', 'dominion.jpg'),
('트와일라잇 스트러글', 2, 2, 180, 13, 3.5, '2005', 'twilight_struggle.jpg'),
('푸에르토 리코', 2, 5, 150, 12, 3.3, '2002', 'puerto_rico.jpg'),
('코드네임', 2, 8, 15, 14, 1.3, '2015', 'codenames.jpg'),
('테라포밍 마스', 1, 5, 120, 12, 3.2, '2016', 'terraforming_mars.jpg'),
('글룸하번', 1, 4, 120, 14, 3.8, '2017', 'gloomhaven.jpg'),
('아줄', 2, 4, 45, 8, 1.8, '2017', 'azul.jpg'),
('사이드', 1, 5, 115, 14, 3.4, '2016', 'scythe.jpg'),
('파워 그리드', 2, 6, 120, 12, 3.3, '2004', 'power_grid.jpg'),
('도쿄의 왕', 2, 6, 30, 8, 1.5, '2011', 'king_of_tokyo.jpg'),
('부르고뉴의 성', 2, 4, 90, 12, 3.0, '2011', 'castles_of_burgundy.jpg'),
('스플렌더', 2, 4, 30, 10, 2.3, '2014', 'splendor.jpg');

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