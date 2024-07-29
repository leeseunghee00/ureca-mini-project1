CREATE TABLE User (
    id          INT             AUTO_INCREMENT  PRIMARY KEY,
    username    VARCHAR(100)    NOT NULL,
    email       VARCHAR(100)    NOT NULL,
    password    VARCHAR(100)    NOT NULL,
    created_at  DATE            NOT NULL
);

CREATE TABLE Manager (
    id              VARCHAR(10)    PRIMARY KEY,
    name            VARCHAR(100)    NOT NULL,
    email           VARCHAR(100)    NOT NULL,
    password        VARCHAR(100)    NOT NULL,
    created_at      DATE            NOT NULL
);

CREATE TABLE Movie (
    id              INT             AUTO_INCREMENT  PRIMARY KEY,
    manager_id      VARCHAR(10),
    title           VARCHAR(100)    NOT NULL,
    genre           VARCHAR(100)    NOT NULL,
    director        VARCHAR(100)    NOT NULL,
    release_year    INT             NOT NULL,

    CONSTRAINT FOREIGN KEY (manager_id) REFERENCES Manager(id)
);

CREATE TABLE Review (
    id          INT             AUTO_INCREMENT PRIMARY KEY,
    user_id     INT,
    movie_id    INT,
    comment     VARCHAR(255)    NOT NULL,
    rating      INT             DEFAULT 1 CHECK (rating >= 1 AND rating <= 5),
    created_at  DATE            NOT NULL,

    CONSTRAINT FOREIGN KEY (user_id) REFERENCES User(id),
    CONSTRAINT FOREIGN KEY (movie_id) REFERENCES Movie(id)
);

INSERT INTO User (username, email, password, created_at) VALUES
    ('김민준', 'minjun.kim@example.com', 'password1', '2023-03-15'),
    ('이서연', 'seoyeon.lee@example.com', 'password2', '2023-04-12'),
    ('박지민', 'jimin.park@example.com', 'password3', '2022-11-08'),
    ('최예린', 'yerin.choi@example.com', 'password4', '2023-01-22'),
    ('정우성', 'woosung.jung@example.com', 'password5', '2023-06-30'),
    ('조민수', 'minsoo.jo@example.com', 'password6', '2023-02-14'),
    ('임지현', 'jihyun.lim@example.com', 'password7', '2022-12-05'),
    ('장하늘', 'haneul.jang@example.com', 'password8', '2023-07-01'),
    ('오재원', 'jaewon.oh@example.com', 'password9', '2023-05-18'),
    ('황수빈', 'soobin.hwang@example.com', 'password10', '2023-03-27'),
    ('유지호', 'jiho.yu@example.com', 'password11', '2023-01-08'),
    ('신가은', 'ga-eun.shin@example.com', 'password12', '2023-08-20'),
    ('배성민', 'seongmin.bae@example.com', 'password13', '2023-02-28'),
    ('안민영', 'minyoung.ahn@example.com', 'password14', '2023-05-05'),
    ('전재현', 'jaehyun.jeon@example.com', 'password15', '2023-01-10'),
    ('서하린', 'harin.seo@example.com', 'password16', '2023-06-18'),
    ('차은재', 'eunjae.cha@example.com', 'password17', '2023-04-07'),
    ('홍정우', 'jeongwoo.hong@example.com', 'password18', '2023-03-30'),
    ('김지우', 'jiwoo.kim@example.com', 'password19', '2023-07-15'),
    ('이유진', 'yujin.lee@example.com', 'password20', '2023-01-25');

INSERT INTO Manager VALUES
    ('M001', '김철수', 'cheolsu.kim@example.com', 'password1', '2023-01-01'),
    ('M002', '이영희', 'younghee.lee@example.com', 'password2', '2023-01-15'),
    ('M003', '박민수', 'minsook.park@example.com', 'password3', '2023-02-20'),
    ('M004', '최지현', 'jihyun.choi@example.com', 'password4', '2023-03-10'),
    ('M005', '정하늘', 'haneul.jung@example.com', 'password5', '2023-04-05');

INSERT INTO Movie (manager_id, title, genre, director, release_year) VALUES
    ('M001', '기생충', '드라마', '봉준호', 2019),
    ('M002', '1917', '전쟁', '샘 멘데스', 2019),
    ('M001', '부산행', '액션', '연상호', 2016),
    ('M002', 'The Shawshank Redemption', '드라마', '프랭크 다라본트', 1994),
    ('M003', '오징어 게임', '드라마', '황동혁', 2021),
    ('M001', 'The Godfather', '범죄', '프란시스 포드 코폴라', 1972),
    ('M004', '내부자들', '스릴러', '우민호', 2015),
    ('M003', 'Parasite', '드라마', '봉준호', 2019),
    ('M005', 'Inception', 'SF', '크리스토퍼 놀란', 2010),
    ('M002', '소울', '애니메이션', '피트 도크터', 2020),
    ('M001', 'Avengers: Endgame', '액션', '안소니 루소', 2019),
    ('M004', '너의 이름은', '애니메이션', '신카이 마코토', 2016),
    ('M003', 'La La Land', '뮤지컬', '데미안 셔젤', 2016),
    ('M005', 'Interstellar', 'SF', '크리스토퍼 놀란', 2014),
    ('M001', '소원', '드라마', '이해영', 2013),
    ('M002', 'Toy Story', '애니메이션', '존 래세터', 1995),
    ('M004', 'The Dark Knight', '액션', '크리스토퍼 놀란', 2008),
    ('M003', '결혼 이야기', '드라마', '노아 바움백', 2019),
    ('M005', 'Parasite', '드라마', '봉준호', 2019),
    ('M001', 'Frozen', '애니메이션', '크리스 벅', 2013),
    ('M002', 'Django Unchained', '서부', 'Quentin Tarantino', 2012),
    ('M003', 'The Matrix', 'SF', '래리 다니엘스', 1999),
    ('M004', '사바하', '미스터리', '이해영', 2019),
    ('M005', 'Black Widow', '액션', '케이트 쇼트랜드', 2021),
    ('M001', '기적', '드라마', '이종필', 2021),
    ('M002', 'Spider-Man: No Way Home', '액션', '존 왓츠', 2021),
    ('M003', '어벤져스: 인피니티 워', '액션', '안소니 루소', 2018),
    ('M004', 'The Lion King', '애니메이션', '존 파브로', 2019),
    ('M005', 'Knives Out', '미스터리', '라이언 존슨', 2019),
    ('M001', 'Taxi Driver', '드라마', '마틴 스콜세지', 1976),
    ('M002', 'Whiplash', '드라마', '데미안 셔젤', 2014),
    ('M003', 'Frozen II', '애니메이션', '크리스 벅', 2019),
    ('M004', '기생충', '드라마', '봉준호', 2019),
    ('M005', 'The Social Network', '드라마', '데이빗 핀처', 2010),
    ('M001', 'Black Panther', '액션', '라이언 쿠글러', 2018),
    ('M002', 'Inside Out', '애니메이션', '피트 도크터', 2015),
    ('M003', 'The Revenant', '서부', '알레한드로 이냐리투', 2015),
    ('M004', 'Little Forest', '드라마', '임순례', 2018),
    ('M005', 'Memento', '스릴러', '크리스토퍼 놀란', 2000);

INSERT INTO Review (user_id, movie_id, comment, rating, created_at) VALUES
    (1, 1, '정말 훌륭한 영화입니다!', 5, '2023-03-16'),
    (2, 2, '전쟁의 현실을 잘 보여줍니다.', 4, '2023-04-13'),
    (3, 3, '한국 영화의 새로운 지평을 열었어요.', 5, '2023-01-23'),
    (4, 4, '고전의 매력이 가득한 작품!', 5, '2023-01-02'),
    (5, 5, '상상력 넘치는 스토리입니다.', 4, '2023-06-29'),
    (1, 6, '캐릭터들이 정말 매력적이었어요.', 5, '2023-02-15'),
    (2, 7, '스토리가 예상 밖의 전개로 흥미로웠습니다.', 4, '2023-07-02'),
    (3, 8, '감정선이 깊이 있게 표현되었습니다.', 5, '2023-05-19'),
    (4, 9, '흥미진진한 액션이 돋보이는 영화.', 4, '2023-03-28'),
    (5, 10, '음악과 스토리가 잘 어우러진 작품.', 5, '2023-01-09'),
    (1, 11, '신선한 이야기 전개가 인상적이었습니다.', 5, '2023-08-21'),
    (2, 12, '정말 재미있게 봤습니다!', 4, '2023-02-27'),
    (3, 13, '기대 이상의 감동을 주었어요.', 5, '2023-06-11'),
    (4, 14, '사회적 메시지가 강하게 담긴 영화.', 5, '2023-04-08'),
    (5, 15, '액션과 스릴이 넘치는 영화!', 4, '2023-01-18'),
    (1, 16, '심플하면서도 깊은 의미가 느껴졌습니다.', 5, '2023-03-30'),
    (2, 17, '역시 마블은 기대를 저버리지 않네요.', 4, '2023-07-15'),
    (3, 18, '상징적인 장면들이 많았어요.', 5, '2023-02-10'),
    (4, 19, '배우들의 연기가 정말 뛰어났습니다.', 5, '2023-05-22'),
    (5, 20, '스토리가 긴장감을 유지했어요.', 4, '2023-03-14'),
    (1, 1, '다시 보고 싶은 영화입니다!', 5, '2023-01-29');