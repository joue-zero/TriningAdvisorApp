-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 11, 2023 at 11:56 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `training`
--

-- --------------------------------------------------------

--
-- Table structure for table `assets`
--

CREATE TABLE `assets` (
  `id` int(11) NOT NULL,
  `content` varchar(255) NOT NULL,
  `course_code` varchar(130) NOT NULL,
  `role` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `assets`
--

INSERT INTO `assets` (`id`, `content`, `course_code`, `role`) VALUES
(1, 'Material #1', '2', 2),
(2, 'Quiz #1', '2', 0),
(3, 'Exam #1', '2', 1);

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `code` varchar(20) NOT NULL,
  `name` varchar(130) NOT NULL,
  `rate` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`code`, `name`, `rate`) VALUES
('1', '1', 0),
('2', '1', 4),
('FCI2020', 'DS', 0),
('FCI2021', 'Modeling', 0),
('jo', 'jooooooo', 0);

-- --------------------------------------------------------

--
-- Table structure for table `course_enroll`
--

CREATE TABLE `course_enroll` (
  `user_id` int(11) NOT NULL,
  `course_code` varchar(130) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `course_enroll`
--

INSERT INTO `course_enroll` (`user_id`, `course_code`) VALUES
(10, '2'),
(15, '2'),
(16, 'FCI2020');

-- --------------------------------------------------------

--
-- Table structure for table `course_prereq`
--

CREATE TABLE `course_prereq` (
  `code_from` varchar(130) NOT NULL,
  `code_to` varchar(130) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `course_prereq`
--

INSERT INTO `course_prereq` (`code_from`, `code_to`) VALUES
('FCI2021', 'FCI2020'),
('1', 'FCI2021'),
('2', '1');

-- --------------------------------------------------------

--
-- Table structure for table `progress`
--

CREATE TABLE `progress` (
  `user_id` int(11) NOT NULL,
  `asset_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `progress`
--

INSERT INTO `progress` (`user_id`, `asset_id`) VALUES
(10, 2),
(10, 3),
(15, 2),
(15, 3);

-- --------------------------------------------------------

--
-- Table structure for table `rate`
--

CREATE TABLE `rate` (
  `user_id` int(11) NOT NULL,
  `course_code` varchar(130) NOT NULL,
  `value` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `rate`
--

INSERT INTO `rate` (`user_id`, `course_code`, `value`) VALUES
(5, '1', 4.5),
(5, '2', 4),
(10, '2', 3.8),
(10, '2', 0),
(10, 'FCI2021', 5),
(15, '2', 4.9);

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `user_id` int(11) NOT NULL,
  `comment` varchar(255) NOT NULL,
  `course_code` varchar(130) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(130) NOT NULL,
  `email` varchar(130) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`, `role`) VALUES
(1, 'Youssef', 'y@email.com', '112', 0),
(2, 'Tony', 'tony@gmail.com', '123', 1),
(3, 'polpol', 't@t.t', '0000', 0),
(4, 'you', 'y@1', '123', 0),
(5, '1', '1', '1', 1),
(6, '3nenenenene', 'heheheh@3nenenene.3aooooo', 'hehehehehe', 0),
(7, 't', 'tt@tt.tt', 't', 0),
(10, '0', '0', '0', 0),
(14, '00', '00@00', '0', 0),
(15, '55', '55@55', '5', 0),
(16, 'tonyElia', 'tony@tony', 'ttt', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `assets`
--
ALTER TABLE `assets`
  ADD PRIMARY KEY (`id`),
  ADD KEY `course_id` (`course_code`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`code`);

--
-- Indexes for table `course_enroll`
--
ALTER TABLE `course_enroll`
  ADD KEY `fk_user` (`user_id`),
  ADD KEY `fk_course` (`course_code`);

--
-- Indexes for table `course_prereq`
--
ALTER TABLE `course_prereq`
  ADD KEY `code_from` (`code_from`),
  ADD KEY `code_to` (`code_to`);

--
-- Indexes for table `progress`
--
ALTER TABLE `progress`
  ADD KEY `asset_id` (`asset_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `rate`
--
ALTER TABLE `rate`
  ADD KEY `course_code` (`course_code`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD KEY `user_id` (`user_id`),
  ADD KEY `course_code` (`course_code`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `assets`
--
ALTER TABLE `assets`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `assets`
--
ALTER TABLE `assets`
  ADD CONSTRAINT `assets_ibfk_1` FOREIGN KEY (`course_code`) REFERENCES `course` (`code`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `course_enroll`
--
ALTER TABLE `course_enroll`
  ADD CONSTRAINT `fk_course` FOREIGN KEY (`course_code`) REFERENCES `course` (`code`),
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `course_prereq`
--
ALTER TABLE `course_prereq`
  ADD CONSTRAINT `course_prereq_ibfk_1` FOREIGN KEY (`code_from`) REFERENCES `course` (`code`),
  ADD CONSTRAINT `course_prereq_ibfk_2` FOREIGN KEY (`code_to`) REFERENCES `course` (`code`);

--
-- Constraints for table `progress`
--
ALTER TABLE `progress`
  ADD CONSTRAINT `progress_ibfk_1` FOREIGN KEY (`asset_id`) REFERENCES `assets` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `progress_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `rate`
--
ALTER TABLE `rate`
  ADD CONSTRAINT `rate_ibfk_1` FOREIGN KEY (`course_code`) REFERENCES `course` (`code`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `rate_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `review_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `review_ibfk_2` FOREIGN KEY (`course_code`) REFERENCES `course` (`code`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
