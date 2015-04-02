-- phpMyAdmin SQL Dump
-- version 4.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2015-03-27 11:29:33
-- 服务器版本： 5.5.37-MariaDB-log
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `guobao_sentiment`
--

-- --------------------------------------------------------

--
-- 表的结构 `oauth_user_twitter`
--

CREATE TABLE IF NOT EXISTS `oauth_user_twitter` (
`tid` int(11) NOT NULL COMMENT '自增id',
  `username` char(50) NOT NULL COMMENT '用户帐号',
  `password` char(50) NOT NULL COMMENT '用户密码',
  `consumer_key` char(50) NOT NULL COMMENT '应用key',
  `consumer_secret` char(50) NOT NULL COMMENT '应用secret',
  `access_token` char(50) NOT NULL COMMENT 'access token',
  `token_secret` char(50) NOT NULL COMMENT 'token secret',
  `since_id` bigint(20) unsigned NOT NULL COMMENT '上一次更新id',
  `lasttime` datetime NOT NULL COMMENT '最新更新时间'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Twitter授权用户信息' AUTO_INCREMENT=1 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `oauth_user_twitter`
--
ALTER TABLE `oauth_user_twitter`
 ADD PRIMARY KEY (`tid`), ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `oauth_user_twitter`
--
ALTER TABLE `oauth_user_twitter`
MODIFY `tid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id';
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
