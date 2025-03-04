show databases;

use ebrainsoft_study;

show tables;

# tb_board
# DROP table board;
# DROP table tb_board;
CREATE TABLE tb_board (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,       -- 게시글 고유 ID
                       title VARCHAR(255) NOT NULL,             -- 게시글 제목
                       content TEXT,                            -- 게시글 내용
                       writer VARCHAR(100),                     -- 작성자 이름
                       viewCnt INT DEFAULT 0,                   -- 조회수
                       password VARCHAR(10),                    -- 비밀번호
                       useYn VARCHAR(1) DEFAULT 'Y',            -- 삭제여부(Y,N)
                       attachYn VARCHAR(1) DEFAULT 'N',         -- 첨부파일여부(Y,N)
                       replyYn VARCHAR(1) DEFAULT 'N',          -- 댓글(Y,N)
                       categoryCode VARCHAR(3) DEFAULT '101',   -- 카테고리
                       createdAt DATETIME DEFAULT CURRENT_TIMESTAMP, -- 생성 시각
                       updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP  -- 수정 시각
);
COMMIT;

SELECT * FROM tb_board ORDER BY id DESC;

SELECT *,DATE_FORMAT(createdAt, '%Y-%m-%d') AS createdDt  FROM tb_board WHERE useYn != 'N';
SELECT *
     ,DATE_FORMAT(createdAt, '%Y-%m-%d') AS createdDt
     ,DATE_FORMAT(updatedAt, '%Y-%m-%d') AS updatedDt
FROM tb_board
WHERE useYn != 'N';
SELECT id, title, content, writer, viewCnt, createdAt, updatedAt
FROM tb_board
WHERE useYn = 'Y';

SELECT b.*, c.name AS categoryName
FROM tb_board b
         LEFT JOIN tb_category c ON b.categoryCode = c.id
WHERE b.useYn = 'Y'
  AND b.createdAt BETWEEN CONCAT('2024-02-27 00:00:00') AND CONCAT(#{toDt}, ' 23:59:59')
;
SELECT b.*, c.name AS categoryName
FROM tb_board b
         LEFT JOIN tb_category c ON b.categoryCode = c.id
WHERE b.useYn = 'Y'
  AND b.createdAt BETWEEN '2024-02-27 00:00:00' AND '2025-02-27 23:59:59'
#     AND b.createdAt BETWEEN '2024-02-27' AND '2025-02-27'
  AND b.categoryCode = '100'
    AND (b.title LIKE CONCAT('%', '', '%') OR b.content LIKE CONCAT('%', '', '%') OR b.writer LIKE CONCAT('%', '', '%')) LIMIT 0,  10;

UPDATE tb_board SET viewCnt = viewCnt+1 WHERE id =1;

# tb_board 샘플
INSERT INTO tb_board (categoryCode, title, content, writer, createdAt, updatedAt)
VALUES
    ('101', 'Hello World', '첫 번째 게시글입니다.', 'admin', SUBDATE(NOW(), 1), SUBDATE(NOW(), 1)),
    ('102', 'Java Tutorial', 'Java에 대한 튜토리얼 자료입니다.', 'user1', SUBDATE(NOW(), 1), SUBDATE(NOW(), 1)),
    ('103', 'Spring Boot 가이드', '초보자를 위한 Spring Boot 가이드.', 'user2', SUBDATE(NOW(), 1), SUBDATE(NOW(), 1)),
    ('104', 'JDBC란 무엇인가?', 'JDBC의 개념과 사용법을 알아봅니다.', 'user3', SUBDATE(NOW(), 1), SUBDATE(NOW(), 1)),
    ('105', 'Database Index의 중요성', '인덱스를 사용해야 하는 이유와 최적화 방법.', 'admin', SUBDATE(NOW(), 1), SUBDATE(NOW(), 1)),
    ('106', 'HTTP와 HTTPS의 차이', '웹 보안에서 중요한 HTTP와 HTTPS의 차이를 다룹니다.', 'user4', SUBDATE(NOW(), 1), SUBDATE(NOW(), 1)),
    ('107', 'REST API 설계 원칙', 'REST API 설계를 잘 하는 방법', 'user5', SUBDATE(NOW(), 1), SUBDATE(NOW(), 1)),
    ('108', 'Docker 시작하기', 'Docker의 기본 사용법과 설치 방법.', 'user6', SUBDATE(NOW(), 1), SUBDATE(NOW(), 1)),
    ('109', 'Kubernetes 개요', 'Kubernetes의 개념과 기본 구성요소.', 'user7', SUBDATE(NOW(), 1), SUBDATE(NOW(), 1)),
    ('110', 'Git 기본 명령어', 'Git의 add, commit, push 명령어 활용법.', 'user8', SUBDATE(NOW(), 1), SUBDATE(NOW(), 1)),
    ('111', '비동기와 동기 프로그래밍', '코딩에서의 동시성 처리 기법 비교.', 'user9', SUBDATE(NOW(), 1), SUBDATE(NOW(), 1)),
    ('112', '80자가 넘어가는 긴 제목 80자가 넘어가는 긴 제목 80자가 넘어가는 긴 제목 80자가 넘어가는 긴 제목 80자가 넘어가는 긴 제목 80자가 넘어가는 긴 제목 80자가 넘어가는 긴 제목 80자가 넘어가는 긴 제목 80자가 넘어가는 긴 제목 80자가 넘어가는 긴 제목', '코딩에서의 동시성 처리 기법 비교.', 'user9', SUBDATE(NOW(), 1), SUBDATE(NOW(), 1));

INSERT INTO tb_board
(title, content, writer, viewCnt, password, useYn, attachYn, replyYn, categoryCode, createdAt, updatedAt)
VALUES
    ('Title 1', 'Content for post 1', 'Writer1', 15, 'pass1', 'Y', 'N', 'Y', '101', '2022-11-04 10:23:45', '2022-11-04 10:23:45'),
    ('Title 2', 'Content for post 2', 'Writer2', 5, 'pass2', 'Y', 'N', 'N', '102', '2023-02-15 14:55:20', '2023-02-15 14:55:20'),
    ('Title 3', 'Content for post 3', 'Writer3', 0, 'pass3', 'Y', 'Y', 'N', '103', '2023-09-23 09:05:13', '2023-09-23 09:05:13'),
    ('Title 4', 'Content for post 4', 'Writer4', 30, 'pass4', 'Y', 'N', 'N', '101', '2023-07-12 21:10:33', '2023-07-12 21:10:33'),
    ('Title 5', 'Content for post 5', 'Writer5', 25, 'pass5', 'Y', 'Y', 'Y', '102', '2023-04-19 12:45:32', '2023-04-19 12:45:32'),
    ('Title 6', 'Content for post 6', 'Writer6', 10, 'pass6', 'Y', 'Y', 'N', '103', '2023-01-09 06:34:12', '2023-01-09 06:34:12'),
    ('Title 7', 'Content for post 7', 'Writer7', 5, 'pass7', 'Y', 'N', 'N', '101', '2023-06-25 14:15:44', '2023-06-25 14:15:44'),
    ('Title 8', 'Content for post 8', 'Writer8', 0, 'pass8', 'Y', 'N', 'Y', '102', '2023-03-17 17:59:01', '2023-03-17 17:59:01'),
    ('Title 9', 'Content for post 9', 'Writer9', 20, 'pass9', 'Y', 'Y', 'Y', '101', '2023-08-14 08:26:57', '2023-08-14 08:26:57'),
    ('Title 10', 'Content for post 10', 'Writer10', 2, 'pass10', 'Y', 'N', 'N', '103', '2023-05-21 11:13:02', '2023-05-21 11:13:02'),
    ('Title 11', 'Content for post 11', 'Writer11', 7, 'pass11', 'Y', 'Y', 'N', '102', '2023-01-23 10:05:16', '2023-01-23 10:05:16'),
    ('Title 12', 'Content for post 12', 'Writer12', 8, 'pass12', 'Y', 'N', 'N', '101', '2022-12-30 15:48:25', '2022-12-30 15:48:25'),
    ('Title 13', 'Content for post 13', 'Writer13', 12, 'pass13', 'Y', 'N', 'Y', '103', '2023-01-18 23:40:37', '2023-01-18 23:40:37'),
    ('Title 14', 'Content for post 14', 'Writer14', 4, 'pass14', 'Y', 'Y', 'N', '102', '2023-03-06 13:07:12', '2023-03-06 13:07:12'),
    ('Title 15', 'Content for post 15', 'Writer15', 18, 'pass15', 'Y', 'N', 'Y', '101', '2023-06-11 18:57:37', '2023-06-11 18:57:37'),
    ('Title 16', 'Content for post 16', 'Writer16', 9, 'pass16', 'Y', 'N', 'N', '102', '2023-10-10 20:25:50', '2023-10-10 20:25:50'),
    ('Title 17', 'Content for post 17', 'Writer17', 3, 'pass17', 'Y', 'N', 'Y', '101', '2023-09-09 05:01:38', '2023-09-09 05:01:38'),
    ('Title 18', 'Content for post 18', 'Writer18', 1, 'pass18', 'Y', 'N', 'N', '103', '2023-05-23 22:12:45', '2023-05-23 22:12:45'),
    ('Title 19', 'Content for post 19', 'Writer19', 20, 'pass19', 'Y', 'Y', 'Y', '102', '2023-07-01 19:03:22', '2023-07-01 19:03:22'),
    ('Title 20', 'Content for post 20', 'Writer20', 11, 'pass20', 'Y', 'N', 'Y', '103', '2023-02-11 03:44:56', '2023-02-11 03:44:56');

# tb_board - 수정
UPDATE tb_board
SET
    title = '또 수정된 제목11',
    content = '수정된 컨텐츠11',
    writer = '수정된 테스트11',
    updatedAt = NOW()
WHERE
    id = 1;

## category table
# DROP table category;
# DROP table tb_category;
CREATE TABLE tb_category (
                       id INT AUTO_INCREMENT PRIMARY KEY,       -- 카테고리 고유 ID
                       name TEXT,                               -- 카테고리 이름
                       content TEXT,                            -- 카테고리 내용
                       useYn VARCHAR(1) DEFAULT 'Y',            -- 삭제여부(Y,N)
                       description TEXT,                        -- 설명
                       createdAt DATETIME DEFAULT CURRENT_TIMESTAMP, -- 생성 시각
                       updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP  -- 수정 시각
);
COMMIT;
ALTER TABLE tb_category AUTO_INCREMENT = 100;

INSERT INTO tb_category (name, content, useYn, description, createdAt, updatedAt)
VALUES
    ('기술 블로그', '최신 IT 기술 트렌드를 공유합니다.', 'Y', 'IT 기술 관련 글을 모아둔 카테고리입니다.', NOW(), NOW()),
    ('영화 리뷰', '최신 영화 리뷰 및 평론.', 'Y', '영화를 좋아하는 사람들이 모여 리뷰를 작성하는 카테고리입니다.', NOW(), NOW()),
    ('요리 레시피', '다양한 요리 레시피를 소개합니다.', 'Y', '집에서 할 수 있는 요리 레시피를 제공하는 공간입니다.', NOW(), NOW()),
    ('여행 후기', '다녀온 여행지 후기를 공유합니다.', 'Y', '국내 및 해외 여행지의 다양한 정보를 나누는 카테고리입니다.', NOW(), NOW()),
    ('러닝 팁', '효율적으로 공부하는 방법.', 'Y', '공부 방법, 학습자료를 공유하는 공간입니다.', NOW(), NOW()),
    ('포토 갤러리', '사진을 업로드하고 공유합니다.', 'Y', '회원들이 찍은 사진을 자유롭게 공유하는 곳입니다.', NOW(), NOW()),
    ('건강 생활', '건강한 생활을 위한 정보 제공.', 'Y', '운동, 영양, 생활 습관 개선에 대한 유익한 정보를 제공합니다.', NOW(), NOW()),
    ('책 추천', '읽을만한 책을 추천합니다.', 'Y', '인생에 영감을 주는 다양한 책을 추천하는 카테고리입니다.', NOW(), NOW()),
    ('음악 이야기', '좋은 음악을 추천하거나 소개합니다.', 'Y', '음악에 대한 취향과 이야기를 공유하는 공간입니다.', NOW(), NOW()),
    ('비디오 게임', '최신 게임 리뷰와 공략.', 'Y', '재미있는 게임을 소개하고 공략법을 설명합니다.', NOW(), NOW()),
    ('DIY 프로젝트', '스스로 만드는 다양한 아이템.', 'Y', '인테리어, 가구, 공예 등 DIY 아이디어를 나눕니다.', NOW(), NOW()),
    ('비즈니스 팁', '비즈니스 성공을 위한 전략과 팁.', 'Y', '사업자들이 참고할만한 팁과 정보를 제공합니다.', NOW(), NOW()),
    ('코딩 튜토리얼', '프로그래밍 언어와 프레임워크 학습.', 'Y', 'Java, Python, Spring Boot와 같은 다양한 주제를 다룹니다.', NOW(), NOW()),
    ('웹 개발', 'HTML, CSS, JavaScript로 웹사이트 만들기.', 'Y', '웹 프론트엔드 및 백엔드 개발 자료를 공유합니다.', NOW(), NOW()),
    ('투자 정보', '주식, 부동산, 금융 관련 팁.', 'Y', '투자 관련 정보를 나누는 유용한 게시판입니다.', NOW(), NOW()),
    ('환경 보호', '지구를 위한 환경 보호 활동.', 'Y', '친환경적 실천과 지속 가능한 삶의 방법을 소개합니다.', NOW(), NOW()),
    ('패션 트렌드', '최신 의류 및 액세서리 소개.', 'Y', '계절별로 어울리는 옷과 스타일을 추천합니다.', NOW(), NOW()),
    ('자동차 정보', '최신 자동차 및 리뷰.', 'Y', '새로운 자동차 모델과 유지보수 팁을 제공합니다.', NOW(), NOW()),
    ('영화제 소식', '국내외 영화제 및 이벤트 정보.', 'Y', '영화제 초청작과 이벤트 정보를 공유하는 곳입니다.', NOW(), NOW()),
    ('취업 준비', '이력서 작성법 및 면접 팁.', 'Y', '취업을 준비하는 구직자들을 위한 가이드를 제공합니다.', NOW(), NOW());


SELECT * FROM tb_category;
ALTER TABLE tb_category ADD description TEXT;

UPDATE tb_category
SET
    useYN = 'Y'
WHERE
    id != '0';

# DROP table reply_board;
# DROP table tb_reply_board;
CREATE TABLE tb_reply_board (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY, -- 댓글 고유 ID
                         board_id BIGINT,                      -- 게시판 ID
                         content TEXT,                      -- 댓글 내용
                         writer VARCHAR(100),               -- 작성자 이름
                         is_hide VARCHAR(2) DEFAULT 0,      -- 삭제여부(0.show, 1.hide)
                         createdAt DATETIME DEFAULT CURRENT_TIMESTAMP, -- 생성 시각
                         updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP -- 수정 시각
);

INSERT INTO tb_reply_board (board_id, content, writer, createdAt, updatedAt)
VALUES( 1,'댓글1-1', '', NOW(), NOW());
INSERT INTO tb_reply_board (board_id, content, writer, createdAt, updatedAt)
VALUES( 2,'댓글2-1', '', NOW(), NOW());

UPDATE tb_reply_board
SET
    content = '수정된 댓글111',
    updatedAt = NOW()
WHERE
    id = 1;

SELECT * FROM tb_reply_board;


CREATE TABLE board_binary_attach (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,   -- 첨부파일 ID
                              boardId BIGINT,                         -- 게시판 번호
                              binaryData LONGTEXT,                 -- 파일 바이너리
                              fileName TEXT                        -- 파일 이름
);

CREATE TABLE board_attach (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,   -- 첨부파일 ID
                        boardId BIGINT,                         -- 게시판 번호
                        originalFileName TEXT,                  -- 파일 이름
                        storedFileName TEXT,                    -- 파일 이름
                        useYn CHAR(1) DEFAULT 'Y'               -- 사용 여부 (Y: 사용, N: 미사용)

);

COMMIT;
SELECT * FROM board_attach;
INSERT INTO board_attach (boardId, originalFileName, storedFileName)
VALUES();

DROP table board_attach;

commit;
