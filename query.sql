show databases;

use ebrainsoft_study;

show tables;

DROP table board;
CREATE TABLE board (
                       id INT AUTO_INCREMENT PRIMARY KEY, -- 게시글 고유 ID
                       title VARCHAR(255) NOT NULL,       -- 게시글 제목
                       content TEXT,                      -- 게시글 내용
                       writer VARCHAR(100),               -- 작성자 이름
                       viewCnt INT DEFAULT 0,             -- 조회수
                       password VARCHAR(10),              -- 비밀번호
                       useYn VARCHAR(1) DEFAULT 'Y',      -- 삭제여부(Y,N)
                       attachYn VARCHAR(1) DEFAULT 'N',   -- 첨부파일여부(Y,N)
                       replyYn VARCHAR(1) DEFAULT 'N',    -- 댓글(Y,N)
                       created_at DATETIME DEFAULT CURRENT_TIMESTAMP, -- 생성 시각
                       updated_at DATETIME DEFAULT CURRENT_TIMESTAMP  -- 수정 시각
);
COMMIT;

SELECT * FROM board;
SELECT * FROM board WHERE useYn != 1 AND id=5;
SELECT id, title, content, writer, viewCnt, created_at, updated_at
FROM board
WHERE useYn = 'Y';

SELECT * FROM board WHERE useYn != 1 AND created_at BETWEEN '2024-02-16' AND '2025-02-28' AND (title LIKE CONCAT('%', 'Ja', '%') OR content LIKE CONCAT('%', 'Ja', '%') OR writer LIKE CONCAT('%', 'Ja', '%')) LIMIT 0,  10;

UPDATE board SET viewCnt = viewCnt + 1 WHERE id =1;

INSERT INTO board (title, content, writer, created_at, updated_at)
VALUES
    ('Hello World', '첫 번째 게시글입니다.', 'admin', SUBDATE(NOW(), 1), SUBDATE(NOW(), 1)),
    ('Java Tutorial', 'Java에 대한 튜토리얼 자료입니다.', 'user1', SUBDATE(NOW(), 1), SUBDATE(NOW(), 1)),
    ('Spring Boot 가이드', '초보자를 위한 Spring Boot 가이드.', 'user2', SUBDATE(NOW(), 1), SUBDATE(NOW(), 1)),
    ('JDBC란 무엇인가?', 'JDBC의 개념과 사용법을 알아봅니다.', 'user3', SUBDATE(NOW(), 1), SUBDATE(NOW(), 1)),
    ('Database Index의 중요성', '인덱스를 사용해야 하는 이유와 최적화 방법.', 'admin', SUBDATE(NOW(), 1), SUBDATE(NOW(), 1)),
    ('HTTP와 HTTPS의 차이', '웹 보안에서 중요한 HTTP와 HTTPS의 차이를 다룹니다.', 'user4', SUBDATE(NOW(), 1), SUBDATE(NOW(), 1)),
    ('REST API 설계 원칙', 'REST API 설계를 잘 하는 방법', 'user5', SUBDATE(NOW(), 1), SUBDATE(NOW(), 1)),
    ('Docker 시작하기', 'Docker의 기본 사용법과 설치 방법.', 'user6', SUBDATE(NOW(), 1), SUBDATE(NOW(), 1)),
    ('Kubernetes 개요', 'Kubernetes의 개념과 기본 구성요소.', 'user7', SUBDATE(NOW(), 1), SUBDATE(NOW(), 1)),
    ('Git 기본 명령어', 'Git의 add, commit, push 명령어 활용법.', 'user8', SUBDATE(NOW(), 1), SUBDATE(NOW(), 1)),
    ('비동기와 동기 프로그래밍', '코딩에서의 동시성 처리 기법 비교.', 'user9', SUBDATE(NOW(), 1), SUBDATE(NOW(), 1)),
    ('80자가 넘어가는 긴 제목 80자가 넘어가는 긴 제목 80자가 넘어가는 긴 제목 80자가 넘어가는 긴 제목 80자가 넘어가는 긴 제목 80자가 넘어가는 긴 제목 80자가 넘어가는 긴 제목 80자가 넘어가는 긴 제목 80자가 넘어가는 긴 제목 80자가 넘어가는 긴 제목', '코딩에서의 동시성 처리 기법 비교.', 'user9', SUBDATE(NOW(), 1), SUBDATE(NOW(), 1));

UPDATE board
SET
    title = '또 수정된 제목11',
    content = '수정된 컨텐츠11',
    writer = '수정된 테스트11',
    updated_at = NOW()
WHERE
    id = 1;

SELECT *
FROM board
WHERE useYn != 1
  AND (title LIKE CONCAT('%', 'ht', '%')
    OR content LIKE CONCAT('%', 'ht', '%')
    OR writer LIKE CONCAT('%', 'ht', '%'));


DROP table reply_board;
CREATE TABLE reply_board (
                             id INT AUTO_INCREMENT PRIMARY KEY, -- 댓글 고유 ID
                             board_id INT,                      -- 게시판 ID
                             content TEXT,                      -- 댓글 내용
                             writer VARCHAR(100),               -- 작성자 이름
                             is_hide VARCHAR(2) DEFAULT 0,      -- 삭제여부(0.show, 1.hide)
                             created_at DATETIME DEFAULT CURRENT_TIMESTAMP, -- 생성 시각
                             updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 수정 시각
);

INSERT INTO reply_board (board_id, content, writer, created_at, updated_at)
VALUES( 1,'댓글1-1', '', NOW(), NOW());
INSERT INTO reply_board (board_id, content, writer, created_at, updated_at)
VALUES( 2,'댓글2-1', '', NOW(), NOW());

UPDATE reply_board
SET
    content = '수정된 댓글111',
    updated_at = NOW()
WHERE
    id = 1;

SELECT * FROM reply_board;


CREATE TABLE attach (
                        id INT AUTO_INCREMENT PRIMARY KEY, -- 첨부파일 ID
                        board_id INT,                      -- 게시판 번호
                        name TEXT,                         -- 파일 이름
                        size TEXT,                         -- 파일 크기
                        url TEXT                           -- 파일 경로
);
DROP table attach;

commit;
