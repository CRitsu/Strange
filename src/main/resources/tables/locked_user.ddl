
DROP TABLE IF EXISTS locked_user;

CREATE TABLE locked_user(
  user_id INT PRIMARY KEY NOT NULL ,
  unlock_time DATETIME NOT NULL,
  lock_flg CHAR(1) DEFAULT 1,
  times int DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO locked_user(user_id, unlock_time) VALUES (90000, '9999-12-31 23:59:59');

# 用户id
# 解锁时间
# 锁定旗
# 次数