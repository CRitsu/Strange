
DROP TABLE IF EXISTS messages;

CREATE TABLE messages (
  message_id INT PRIMARY KEY AUTO_INCREMENT,
  from_id INT NOT NULL ,
  to_id INT NOT NULL ,
  message_body VARCHAR(999),
  create_time TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8;

INSERT messages(from_id, to_id, message_body, create_time) VALUES (10000,10001,'TEST_MESSAGE',current_timestamp);

