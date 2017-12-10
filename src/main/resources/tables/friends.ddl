
DROP TABLE IF EXISTS friends;

CREATE TABLE friends(
  user_id INT NOT NULL ,
  friend_id INT NOT NULL ,
  black_flg CHAR(1) DEFAULT 0,
  add_time TIMESTAMP(3) DEFAULT current_timestamp(3)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE INDEX friends_index ON friends(user_id);

INSERT INTO friends(user_id, friend_id) VALUES (10000, 10001);
INSERT INTO friends(user_id, friend_id) VALUES (10000, 10002);

# 用户id
# 好友id
# 黑旗
# 添加时间