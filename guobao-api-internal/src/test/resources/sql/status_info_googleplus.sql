-- phpMyAdmin SQL Dump
-- version 4.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2015-03-29 06:00:34
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
-- 表的结构 `status_info_googleplus`
--

CREATE TABLE IF NOT EXISTS `status_info_googleplus` (
`tid` int(10) unsigned NOT NULL COMMENT '自增id',
  `id` char(50) NOT NULL COMMENT '此动态的ID',
  `title` char(255) NOT NULL COMMENT '此动态的标题',
  `published` datetime NOT NULL COMMENT '此动态最初发布的时间',
  `updated` datetime NOT NULL COMMENT '此动态最后一次更新的时间',
  `url` char(255) NOT NULL COMMENT '指向此动态的链接',
  `actor_id` char(50) NOT NULL COMMENT '执行人的个人资源ID',
  `actor_display_name` char(50) NOT NULL COMMENT '执行人昵称',
  `object_id` char(50) NOT NULL COMMENT '对象的 ID。转发动态时，它就是转发的动态的ID',
  `object_actor_id` char(50) NOT NULL COMMENT '原始执行人的ID',
  `object_actor_display_name` char(50) NOT NULL COMMENT '原始执行人昵称（适合用于显示）',
  `object_original_content` mediumtext NOT NULL COMMENT '作者提供的内容（文字），此储存内容不带任何 HTML 格式',
  `object_url` char(255) NOT NULL COMMENT '指向链接资源的网址',
  `object_replies_totalitems` int(10) unsigned NOT NULL COMMENT '此动态的评论总数',
  `object_plusoners_totalitems` int(10) unsigned NOT NULL COMMENT '对此动态执行 +1 操作的人员总数',
  `object_resharers_totalitems` int(10) unsigned NOT NULL COMMENT '转发此动态的人员总数',
  `object_attachments_content` mediumtext NOT NULL COMMENT '附件文本内容',
  `annotation` mediumtext NOT NULL COMMENT '动态分享者额外添加的内容（仅在转发动态时可用）',
  `latitude` double NOT NULL COMMENT '纬度',
  `longitude` double NOT NULL COMMENT '经度',
  `place_name` char(50) NOT NULL COMMENT '此动态发生地点的名称',
  `lasttime` datetime NOT NULL COMMENT '最新更新时间'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Google+用户状态信息，原始数据类型' AUTO_INCREMENT=1 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `status_info_googleplus`
--
ALTER TABLE `status_info_googleplus`
 ADD PRIMARY KEY (`tid`), ADD UNIQUE KEY `id` (`id`), ADD KEY `actor_id` (`actor_id`,`actor_display_name`,`object_id`,`object_actor_id`,`object_actor_display_name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `status_info_googleplus`
--
ALTER TABLE `status_info_googleplus`
MODIFY `tid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id';
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
