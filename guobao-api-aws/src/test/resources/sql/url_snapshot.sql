-- phpMyAdmin SQL Dump
-- version 4.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2015-03-29 06:15:42
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
-- 表的结构 `url_snapshot`
--

CREATE TABLE IF NOT EXISTS `url_snapshot` (
`tid` int(11) NOT NULL COMMENT '自增id',
  `identify` char(50) NOT NULL COMMENT 'url唯一md5标识',
  `url` char(255) NOT NULL COMMENT '需要快照的url',
  `flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '判断html是否为空',
  `html` mediumtext NOT NULL COMMENT '快照结果',
  `lasttime` datetime NOT NULL COMMENT '最新查询时间'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='根据Url得到的快照信息' AUTO_INCREMENT=1 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `url_snapshot`
--
ALTER TABLE `url_snapshot`
 ADD PRIMARY KEY (`tid`), ADD UNIQUE KEY `identify` (`identify`), ADD KEY `flag` (`flag`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `url_snapshot`
--
ALTER TABLE `url_snapshot`
MODIFY `tid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id';
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
