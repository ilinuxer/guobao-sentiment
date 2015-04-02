-- phpMyAdmin SQL Dump
-- version 4.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2015-03-27 11:29:47
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
-- 表的结构 `status_info_twitter`
--

CREATE TABLE IF NOT EXISTS `status_info_twitter` (
`tid` int(11) NOT NULL COMMENT '自增ID',
  `id` bigint(20) unsigned NOT NULL COMMENT '状态唯一id',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户唯一id',
  `screen_name` char(50) NOT NULL COMMENT '昵称',
  `latitude` double NOT NULL COMMENT '纬度',
  `longitude` double NOT NULL COMMENT '经度',
  `created_at` datetime NOT NULL COMMENT '该微博创建时间',
  `text` varchar(500) NOT NULL COMMENT '该微博的文本信息',
  `retweet_count` int(10) unsigned NOT NULL COMMENT '该微博总的转发数量',
  `possibly_sensitive` tinyint(1) NOT NULL COMMENT '可能敏感信息',
  `location` char(50) NOT NULL COMMENT '所在城市信息',
  `retweeted_id` bigint(20) unsigned NOT NULL COMMENT '转发状态的id',
  `retweeted_user_id` bigint(20) unsigned NOT NULL COMMENT '转发用户的id',
  `retweeted_screen_name` char(50) NOT NULL COMMENT '转发用户的昵称',
  `lasttime` datetime NOT NULL COMMENT '最新更新时间'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Twitter用户推文信息，原始数据类型' AUTO_INCREMENT=1 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `status_info_twitter`
--
ALTER TABLE `status_info_twitter`
 ADD PRIMARY KEY (`tid`), ADD UNIQUE KEY `id` (`id`), ADD KEY `user_id` (`user_id`,`screen_name`,`retweeted_id`,`retweeted_user_id`,`retweeted_screen_name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `status_info_twitter`
--
ALTER TABLE `status_info_twitter`
MODIFY `tid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID';
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
