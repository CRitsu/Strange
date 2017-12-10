
DROP TABLE IF EXISTS authority;

CREATE TABLE authority(
  user_id INT NOT NULL ,
  auth_name VARCHAR(48)  ,
  auth_value VARCHAR(48) NOT NULL DEFAULT '',
  PRIMARY KEY (user_id, auth_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# CREATE INDEX authority_index ON authority(auth_name);

INSERT INTO authority(user_id, auth_name, auth_value) VALUES (10000,'TEST_AUTH_NAME','TEST_AUTH_VALUE');
INSERT INTO authority(user_id, auth_name, auth_value) VALUES (10000,'TEST_AUTH_NAME1','1TEST_AUTH_VALUE');
INSERT INTO authority(user_id, auth_name, auth_value) VALUES (10000,'TEST_AUTH_NAME2','2TEST_AUTH_VALUE');
INSERT INTO authority(user_id, auth_name, auth_value) VALUES (10000,'TEST_AUTH_NAME3','3TEST_AUTH_VALUE');
INSERT INTO authority(user_id, auth_name, auth_value) VALUES (10000,'TEST_AUTH_NAME4','4TEST_AUTH_VALUE');
# 用户id
# 权限名
# 权限值