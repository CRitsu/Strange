
DROP TABLE IF EXISTS comments;

CREATE TABLE comments(
  article_id INT NOT NULL ,
  user_id INT NOT NULL ,
  comment_id INT AUTO_INCREMENT PRIMARY KEY ,
  comment_body TEXT NOT NULL ,
  post_time TIMESTAMP(3) DEFAULT current_timestamp(3) ,
  like_count INT DEFAULT 0,
  delete_flg CHAR(1) DEFAULT 0
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8;

INSERT INTO comments(article_id, user_id, comment_body)
VALUES (1000000, 10000, 'TEST_COMMENT');


# delete_flg 删除flag，标记不应该显示的对象


# 文章id
# 用户id
# 评论id
# 评论内容
# 评论时间
# 赞
# 软删除