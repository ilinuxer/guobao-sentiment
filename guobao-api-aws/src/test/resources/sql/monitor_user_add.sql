-- phpMyAdmin SQL Dump
-- version 4.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2015-03-28 10:20:32
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
-- 表的结构 `monitor_user_add`
--

CREATE TABLE IF NOT EXISTS `monitor_user_add` (
`tid` int(11) NOT NULL COMMENT '自增id',
  `uid` char(50) NOT NULL COMMENT '用户唯一id',
  `name` char(50) NOT NULL COMMENT '用户名或昵称，必须唯一',
  `sns` char(2) NOT NULL COMMENT '社交类型，tw,fb,gp',
  `lasttime` datetime NOT NULL COMMENT '插入时间'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='POST到AWS上的简单用户信息' AUTO_INCREMENT=1 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `monitor_user_add`
--
ALTER TABLE `monitor_user_add`
 ADD PRIMARY KEY (`tid`), ADD UNIQUE KEY `uid` (`uid`,`name`), ADD KEY `sns` (`sns`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `monitor_user_add`
--
ALTER TABLE `monitor_user_add`
MODIFY `tid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id';
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
