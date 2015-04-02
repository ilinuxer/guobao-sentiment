-- phpMyAdmin SQL Dump
-- version 4.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2015-03-27 11:29:57
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
-- 表的结构 `user_info_googleplus`
--

CREATE TABLE IF NOT EXISTS `user_info_googleplus` (
`tid` int(10) unsigned NOT NULL COMMENT '自增ID',
  `id` char(50) NOT NULL COMMENT '执行人的个人资源ID',
  `display_name` char(50) NOT NULL COMMENT '昵称',
  `name` char(100) NOT NULL COMMENT '姓名',
  `url` char(255) NOT NULL COMMENT '指向执行人的Google个人资料的链接，即主页地址',
  `image_url` char(255) NOT NULL COMMENT '头像地址',
  `lasttime` datetime NOT NULL COMMENT '最新更新时间'
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='Google+用户基本信息，原始数据类型' AUTO_INCREMENT=3 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user_info_googleplus`
--
ALTER TABLE `user_info_googleplus`
 ADD PRIMARY KEY (`tid`), ADD UNIQUE KEY `id` (`id`), ADD KEY `display_name` (`display_name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user_info_googleplus`
--
ALTER TABLE `user_info_googleplus`
MODIFY `tid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
