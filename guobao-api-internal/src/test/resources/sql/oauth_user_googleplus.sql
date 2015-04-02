-- phpMyAdmin SQL Dump
-- version 4.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2015-03-27 11:29:25
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
-- 表的结构 `oauth_user_googleplus`
--

CREATE TABLE IF NOT EXISTS `oauth_user_googleplus` (
`tid` int(11) NOT NULL COMMENT '自增id',
  `username` char(50) NOT NULL COMMENT '用户名',
  `password` char(50) NOT NULL COMMENT '用户密码',
  `app_name` char(50) NOT NULL COMMENT '应用名',
  `client_id` char(50) NOT NULL COMMENT '客户端id',
  `client_secret` char(50) NOT NULL COMMENT '客户端密码',
  `lasttime` datetime NOT NULL COMMENT '最新更新时间'
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='GooglePlus授权用户信息' AUTO_INCREMENT=3 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `oauth_user_googleplus`
--
ALTER TABLE `oauth_user_googleplus`
 ADD PRIMARY KEY (`tid`), ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `oauth_user_googleplus`
--
ALTER TABLE `oauth_user_googleplus`
MODIFY `tid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
