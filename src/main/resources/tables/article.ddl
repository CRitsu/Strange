
DROP TABLE IF EXISTS article;

CREATE TABLE article(
  user_id INT NOT NULL ,
  article_id BIGINT AUTO_INCREMENT PRIMARY KEY ,
  created_time TIMESTAMP(3) DEFAULT current_timestamp(3),
  like_count INT DEFAULT 0,
  article_body TEXT NOT NULL ,
  image VARCHAR(255) DEFAULT '',
  delete_flg CHAR(1) DEFAULT 0
) ENGINE=InnoDB AUTO_INCREMENT=1000000 DEFAULT CHARSET=utf8;

INSERT INTO article (user_id, article_body)
VALUES (10000, 'TEST_ARTICLE_CONTENT');
INSERT INTO article (user_id, article_body)
VALUES (10001, 'TEST_ARTICLE_CONTENT_11');
INSERT INTO article (user_id, article_body)
VALUES (10002, 'TEST_ARTICLE_CONTENT_22');

# 用户id
# 文章id
# 创建时间
# 赞
# 文章内容
# 配图
# 软删除

