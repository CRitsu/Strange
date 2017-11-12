
DROP TABLE IF EXISTS locked_user;

CREATE TABLE locked_user(
  user_id INT PRIMARY KEY NOT NULL ,
  unlock_time DATETIME NOT NULL,
  lock_flg CHAR(1) DEFAULT 1,
  times int DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO locked_user(user_id, unlock_time) VALUES (10000, current_timestamp);

# 用户id
# 解锁时间
# 锁定旗
# 次数