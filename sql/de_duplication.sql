drop table if EXISTS de_duplication;
create table de_duplication(
  tx_no varchar(155)primary key,
  account_no varchar(20),
  create_time Date

)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;