DROP TABLE IF EXISTS `local_try_log`;
create table local_try_log(
  tx_no VARCHAR(20) PRIMARY key,
  create_time Date
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `local_confirm_log`;
create table local_confirm_log(
  tx_no VARCHAR(20) PRIMARY key,
  create_time Date
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `local_cancel_log`;
create table local_cancel_log(
  tx_no VARCHAR(20) PRIMARY key,
  create_time Date
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;