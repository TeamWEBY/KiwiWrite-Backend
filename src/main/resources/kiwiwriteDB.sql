-- 23.08.14 초안 1차 ver. (추후 언제든지 수정, 변경 가능) updated by 강진주

-- 로컬 DB에서 충분한 테스트 후 RDS에 적용할 예정
-- 기본 테이블 생성

-- 1. users table
CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(20) NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       passwd VARCHAR(20) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 2. words table
CREATE TABLE words (
                       word_id INT AUTO_INCREMENT PRIMARY KEY,
                       word VARCHAR(50) NOT NULL,
                       month INT NOT NULL,
                       day INT NOT NULL
);

-- 3. notes table
CREATE TABLE notes (
                       note_id INT AUTO_INCREMENT PRIMARY KEY,
                       user_id INT,
                       word_id INT,
                       title VARCHAR(40) NOT NULL,
                       content VARCHAR(4000) NOT NULL,
                       visible BOOLEAN NOT NULL,
                       FOREIGN KEY (user_id) REFERENCES users (user_id),
                       FOREIGN KEY (word_id) REFERENCES words (word_id),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 4. comments table
CREATE TABLE comments (
                          com_id INT AUTO_INCREMENT PRIMARY KEY,
                          note_id INT NOT NULL,
                          user_id INT NOT NULL,
                          content VARCHAR(500) NOT NULL,
                          FOREIGN KEY (note_id) REFERENCES notes (note_id),
                          FOREIGN KEY (user_id) REFERENCES users (user_id),
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 5. likes table
CREATE TABLE likes (
                       note_id INT PRIMARY KEY,
                       like_cnt INT,
                       FOREIGN KEY (note_id) REFERENCES notes (note_id)
);

-- 6. collections table
CREATE TABLE collections (
                             user_id INT,
                             word_id INT,
                             word_cnt INT,
                             FOREIGN KEY (user_id) REFERENCES users (user_id),
                             FOREIGN KEY (word_id) REFERENCES words (word_id),
                             PRIMARY KEY (user_id, word_id)
);

-- 7. neighbors table
CREATE TABLE neighbors (
                           user_id INT,
                           ngb_id INT NOT NULL,
                           FOREIGN KEY (user_id) REFERENCES users (user_id),
                           FOREIGN KEY (ngb_id) REFERENCES users (user_id),
                           PRIMARY KEY (user_id, ngb_id)
);


-- view or 추가 테이블