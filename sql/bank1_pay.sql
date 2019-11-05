DROP TABLE IF EXISTS `account_pay`;

CREATE TABLE `account_pay` (
  `id` int COLLATE utf8_bin NOT NULL,
  `account_no` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '账号',
  `pay_amount` double DEFAULT NULL COMMENT '充值余额',
  `result` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '充值结果:success，fail',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;