-- phpMyAdmin SQL Dump
-- version 4.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2015-03-27 11:30:03
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
-- 表的结构 `user_info_twitter`
--

CREATE TABLE IF NOT EXISTS `user_info_twitter` (
`tid` int(11) NOT NULL COMMENT '自增ID',
  `id` bigint(20) unsigned NOT NULL COMMENT '用户唯一id',
  `name` char(100) NOT NULL COMMENT '用户姓名',
  `screen_name` char(50) NOT NULL COMMENT '昵称',
  `profile_image_url` char(255) NOT NULL COMMENT '头像信息',
  `created_at` datetime NOT NULL COMMENT '用户创建时间',
  `location` char(100) NOT NULL COMMENT '用户所在城市',
  `url` char(255) NOT NULL COMMENT '用户主页地址',
  `favourites_count` int(10) unsigned NOT NULL COMMENT '收藏数',
  `utc_offset` int(11) NOT NULL COMMENT 'UTC时差',
  `listed_count` int(10) unsigned NOT NULL COMMENT '用户关注的公共列表数',
  `followers_count` int(10) unsigned NOT NULL COMMENT '粉丝数量',
  `lang` char(50) NOT NULL COMMENT '母语或者主要语言',
  `description` varchar(500) NOT NULL COMMENT '描述信息',
  `verified` tinyint(1) NOT NULL COMMENT '是否验证',
  `time_zone` char(50) NOT NULL COMMENT '时区',
  `statuses_count` int(10) unsigned NOT NULL COMMENT '发布的状态数',
  `friends_count` int(10) unsigned NOT NULL COMMENT '关注数量',
  `lasttime` datetime NOT NULL COMMENT '最新更新时间'
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='Twitter用户基本信息，原始数据类型' AUTO_INCREMENT=3 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user_info_twitter`
--
ALTER TABLE `user_info_twitter`
 ADD PRIMARY KEY (`tid`), ADD UNIQUE KEY `id` (`id`,`screen_name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user_info_twitter`
--
ALTER TABLE `user_info_twitter`
MODIFY `tid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
