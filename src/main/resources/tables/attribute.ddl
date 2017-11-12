# 应用控制的一些参数储存

DROP TABLE IF EXISTS attribute;

CREATE TABLE attribute(
  attribute VARCHAR(18) PRIMARY KEY NOT NULL ,
  value VARCHAR(18) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO attribute(attribute, value) VALUES ('TEST_ATTRIBUTE','TEST_VALUE');

# 属性名
# 属性值