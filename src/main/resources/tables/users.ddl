# START

# 删除如果表存在
DROP TABLE IF EXISTS users;

# 创建表，设置自增主键从10000开始
create TABLE users (
  user_id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(48) NOT NULL ,
  password VARCHAR(128) NOT NULL ,
  avatar VARCHAR(255) DEFAULT '' COMMENT '用户头像路径',
  mail VARCHAR(128) UNIQUE ,
  tel VARCHAR(11) UNIQUE ,
  created_time TIMESTAMP(3) DEFAULT current_timestamp(3) COMMENT '创建时间',
  last_update_time TIMESTAMP NOT NULL DEFAULT '2017-11-12 10:17:00' COMMENT '更新时间'
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8;

# 对username创建索引
CREATE UNIQUE INDEX users_index ON users(username);

# 测试插入数据
INSERT INTO users (username, password, mail, tel) VALUES ('TEST_USER_STRANGE', 'TEST_USER_PASSWORD', 'aa', '11');
INSERT INTO users (username, password, mail, tel) VALUES ('TEST_USER_FRIEND', 'TEST_USER_PASSWORD_FRIEND', 'bb', '22');
INSERT INTO users (username, password, mail, tel) VALUES ('TEST_USER_FRIEND_2', 'TEST_USER_PASSWORD_FRIEND_2', 'cc', '33');

# INSERT INTO users (username, password) VALUES ('TEST_USER2', 'TEST_USER_PASSWORD');

# END
