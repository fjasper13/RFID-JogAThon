-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 28, 2018 at 06:45 PM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jogathon`
--

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `kategori_ID` int(11) NOT NULL,
  `kategori_Name` varchar(9) NOT NULL,
  `donasi_tetap` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`kategori_ID`, `kategori_Name`, `donasi_tetap`) VALUES
(1, 'Guru', 500000),
(2, 'Staff', 0),
(3, 'Mahasiswa', 0);

-- --------------------------------------------------------

--
-- Table structure for table `record`
--

CREATE TABLE `record` (
  `record_ID` int(3) NOT NULL,
  `runner_ID` int(11) NOT NULL,
  `record_Awal` varchar(20) NOT NULL,
  `record_Akhir` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `registration`
--

CREATE TABLE `registration` (
  `registration_ID` int(3) NOT NULL,
  `tag_ID` int(30) NOT NULL,
  `no_Peserta` int(11) NOT NULL,
  `nama` varchar(24) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `kategori_ID` int(11) NOT NULL,
  `donasi_Lap` int(11) NOT NULL,
  `donatur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `registration`
--

INSERT INTO `registration` (`registration_ID`, `tag_ID`, `no_Peserta`, `nama`, `gender`, `kategori_ID`, `donasi_Lap`, `donatur`) VALUES
(1, 27588114, 1031, 'Dr. Bessie Bernhard II', 'Male', 1, 69293, 86),
(2, 48933054, 1002, 'Mylene Cummerata', 'Male', 2, 55359, 54),
(3, 32040125, 1008, 'Genesis Mosciski IV', 'Female', 1, 82493, 31),
(4, 87637184, 1011, 'Lamar Oberbrunner', 'Male', 1, 25115, 54),
(5, 65857316, 1037, 'Mrs. Brooke Price', 'Male', 1, 66328, 11),
(6, 37713218, 1028, 'Miss Kitty Gorczany', 'Male', 2, 41754, 66),
(7, 13019266, 1048, 'Genevieve Bruen', 'Female', 3, 33961, 60),
(8, 95866724, 1029, 'Roxanne Huel', 'Female', 1, 13928, 70),
(9, 54495186, 1035, 'Prof. Harry Steuber', 'Male', 3, 63038, 90),
(10, 35519885, 1033, 'Prof. Leora Brakus', 'Female', 1, 15606, 86),
(11, 96654742, 1036, 'Breanne Johns', 'Male', 1, 73314, 94),
(12, 84339977, 1024, 'Miss Gudrun Dach Sr.', 'Male', 1, 42369, 61),
(13, 34982339, 1012, 'Johnpaul Miller', 'Male', 3, 42886, 28),
(14, 19156665, 1016, 'Obie Schowalter DDS', 'Female', 1, 31202, 14),
(15, 70619749, 1032, 'Darian Kirlin', 'Male', 3, 18194, 20),
(16, 83178252, 1046, 'Prof. Dayna Abshire', 'Female', 2, 77319, 43),
(17, 59049574, 1034, 'Moses Hilll', 'Male', 2, 55720, 31),
(18, 86492157, 1022, 'Leola Roberts', 'Male', 1, 99693, 83),
(19, 58325787, 1007, 'Alfreda Considine DVM', 'Male', 1, 40662, 12),
(20, 48477511, 1018, 'Jayne Anderson', 'Male', 2, 86212, 48),
(21, 24340488, 1043, 'Vida Dicki', 'Female', 2, 77962, 40),
(22, 86155472, 1042, 'Virginie Reinger I', 'Female', 3, 9059, 74),
(23, 58124693, 1044, 'Miss Candice Huels', 'Female', 3, 87697, 44),
(24, 70910812, 1017, 'Viva Lakin', 'Male', 3, 4813, 26),
(25, 89124677, 1047, 'Adah Hilpert', 'Male', 2, 1810, 39),
(26, 71428609, 1005, 'Kathleen Muller', 'Male', 2, 82497, 53),
(27, 83245984, 1003, 'Emory Zboncak', 'Male', 1, 58671, 53),
(28, 77619532, 1013, 'Ms. Jany Yost', 'Female', 1, 5181, 100),
(29, 61801316, 1019, 'Ivah Streich Jr.', 'Female', 3, 41146, 88),
(30, 98244528, 1049, 'Casper Okuneva', 'Female', 3, 54678, 28),
(31, 38932746, 1009, 'Woodrow Halvorson II', 'Female', 2, 50842, 63),
(32, 42584494, 1023, 'Woodrow Kohler', 'Male', 1, 41278, 94),
(33, 41738088, 1020, 'Mr. Oswaldo Kling MD', 'Female', 1, 21387, 79),
(34, 29174153, 1041, 'Maurice Kemmer II', 'Female', 3, 85449, 47),
(35, 34590274, 1014, 'Mrs. Rebeka Sanford Sr.', 'Male', 1, 81953, 82),
(36, 63798521, 1045, 'Mr. Cleo Haag Jr.', 'Male', 1, 23331, 37),
(37, 92940602, 1040, 'Webster O\'Keefe', 'Female', 1, 44593, 40),
(38, 64615343, 1004, 'Ivory Sanford', 'Female', 1, 33912, 57),
(39, 35346610, 1038, 'Monserrate Cruickshank I', 'Male', 1, 46151, 17),
(40, 91290526, 1006, 'Mr. Kristopher Ledner Jr', 'Male', 1, 96065, 72),
(41, 12604852, 1027, 'Dagmar Dibbert', 'Male', 2, 17102, 18),
(42, 52472316, 1050, 'Janice Frami', 'Male', 1, 75785, 76),
(43, 23750479, 1021, 'Oswald Will', 'Female', 2, 90216, 80),
(44, 94869624, 1010, 'Dasia Krajcik', 'Female', 2, 76722, 64),
(45, 16057090, 1025, 'Michele Bernier', 'Male', 2, 74800, 81),
(46, 67246363, 1026, 'Rosalia Legros', 'Male', 1, 22165, 77),
(47, 46963181, 1039, 'Prof. Rossie Bernier DVM', 'Male', 3, 20766, 12),
(48, 97064462, 1015, 'Jensen Bogisich', 'Female', 1, 19213, 41),
(49, 47153514, 1001, 'Flo Legros', 'Female', 2, 47208, 87),
(50, 71098017, 1030, 'Chanel Crooks', 'Male', 1, 13847, 18);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`kategori_ID`),
  ADD UNIQUE KEY `kategori_ID` (`kategori_ID`);

--
-- Indexes for table `record`
--
ALTER TABLE `record`
  ADD PRIMARY KEY (`record_ID`),
  ADD KEY `record_ibfk_1` (`runner_ID`);

--
-- Indexes for table `registration`
--
ALTER TABLE `registration`
  ADD PRIMARY KEY (`registration_ID`),
  ADD UNIQUE KEY `no_Peserta` (`no_Peserta`),
  ADD KEY `kategori_relation` (`kategori_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kategori`
--
ALTER TABLE `kategori`
  MODIFY `kategori_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `record`
--
ALTER TABLE `record`
  MODIFY `record_ID` int(3) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `registration`
--
ALTER TABLE `registration`
  MODIFY `registration_ID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `record`
--
ALTER TABLE `record`
  ADD CONSTRAINT `record_relation` FOREIGN KEY (`runner_ID`) REFERENCES `registration` (`no_Peserta`);

--
-- Constraints for table `registration`
--
ALTER TABLE `registration`
  ADD CONSTRAINT `kategori_relation` FOREIGN KEY (`kategori_ID`) REFERENCES `kategori` (`kategori_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
