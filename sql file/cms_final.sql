-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 04, 2017 at 03:28 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `collage_management_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `user_name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`user_name`, `password`) VALUES
('min', 'min');

-- --------------------------------------------------------

--
-- Table structure for table `student_information`
--

CREATE TABLE `student_information` (
  `student_id` int(100) NOT NULL,
  `student_name` varchar(100) NOT NULL,
  `Father_name` varchar(100) NOT NULL,
  `Mother_name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `contact` int(100) NOT NULL,
  `age` int(100) NOT NULL,
  `Address` varchar(5000) NOT NULL,
  `gender` varchar(222) NOT NULL,
  `blood` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_information`
--

INSERT INTO `student_information` (`student_id`, `student_name`, `Father_name`, `Mother_name`, `password`, `contact`, `age`, `Address`, `gender`, `blood`) VALUES
(3, 'm', 'd', 'c', 'd', 2, 3, 'd', 'Item 1', 'Item 1'),
(12, 'min', 'mosfiq', 'josna', 'p', 48, 3, 'uttara', 'Item 1', 'Item 1');

-- --------------------------------------------------------

--
-- Table structure for table `student_result`
--

CREATE TABLE `student_result` (
  `student_id` int(100) NOT NULL,
  `student_name` varchar(100) NOT NULL,
  `First_term` int(100) NOT NULL,
  `mid_term` int(177) NOT NULL,
  `Final_exam` int(100) NOT NULL,
  `Total_mark` int(111) NOT NULL,
  `subject_Grade` varchar(111) NOT NULL,
  `subject` varchar(1111) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_result`
--

INSERT INTO `student_result` (`student_id`, `student_name`, `First_term`, `mid_term`, `Final_exam`, `Total_mark`, `subject_Grade`, `subject`) VALUES
(1, 'good', 3, 4, 55, 66, 's', ''),
(2, 'student', 32, 43, 54, 66, 's', ''),
(3, 'rock', 23, 65, 78, 887, 'd', 'null'),
(4, 'rock', 23, 65, 78, 887, 'd', 'null'),
(5, 'papa', 24, 43, 72, 88, 'a', 'CSC-384'),
(6, 'papa', 24, 43, 72, 88, 'a', 'CSC-384'),
(7, 'jack', 23, 43, 34, 22, 'd', 'CSC-384'),
(8, 'papa', 24, 20, 40, 84, 'A+', 'CSC-384'),
(9, 'minhazul', 25, 30, 20, 75, 'A', 'CSC-383'),
(10, 'bfc', 33, 22, 12, 67, 'A-', 'CSC-383');

-- --------------------------------------------------------

--
-- Table structure for table `teacher_information`
--

CREATE TABLE `teacher_information` (
  `teacher_id` int(100) NOT NULL,
  `teacher_name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(177) NOT NULL,
  `age` varchar(345) NOT NULL,
  `contact` int(120) NOT NULL,
  `Room` varchar(100) NOT NULL,
  `Gender` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacher_information`
--

INSERT INTO `teacher_information` (`teacher_id`, `teacher_name`, `password`, `email`, `age`, `contact`, `Room`, `Gender`) VALUES
(1, 'min', 'm', 'min@gmail.com', '3', 98, 'Room-201', 'Male');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`user_name`);

--
-- Indexes for table `student_information`
--
ALTER TABLE `student_information`
  ADD PRIMARY KEY (`student_id`);

--
-- Indexes for table `student_result`
--
ALTER TABLE `student_result`
  ADD PRIMARY KEY (`student_id`);

--
-- Indexes for table `teacher_information`
--
ALTER TABLE `teacher_information`
  ADD PRIMARY KEY (`teacher_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `student_information`
--
ALTER TABLE `student_information`
  MODIFY `student_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `student_result`
--
ALTER TABLE `student_result`
  MODIFY `student_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `teacher_information`
--
ALTER TABLE `teacher_information`
  MODIFY `teacher_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
