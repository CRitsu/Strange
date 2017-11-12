
DROP TABLE IF EXISTS tracks;

CREATE TABLE tracks(
  user_id INT NOT NULL ,
  time TIMESTAMP NOT NULL ,
  target VARCHAR(48) NOT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE INDEX tracks_index ON tracks(time);

INSERT INTO tracks(user_id, time, target) VALUES (10000,current_timestamp,'LOGON');

# 用户id
# 时间
# 目标