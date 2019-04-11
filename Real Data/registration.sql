-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 10, 2018 at 02:41 AM
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
-- Database: `karyaace_jogathon`
--

-- --------------------------------------------------------

--
-- Table structure for table `registration`
--

CREATE TABLE `registration` (
  `registration_ID` int(3) NOT NULL,
  `no_Peserta` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `kategori_ID` int(11) NOT NULL,
  `target_Lap` int(11) NOT NULL,
  `donasi_Lap` int(11) NOT NULL,
  `donatur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `registration`
--

INSERT INTO `registration` (`registration_ID`, `no_Peserta`, `nama`, `gender`, `kategori_ID`, `target_Lap`, `donasi_Lap`, `donatur`) VALUES
(1, 1001, 'Dr. Ir. Samuel Tarigan, M.B.A.', 'Male', 2, 16, 36252, 28),
(2, 1002, 'Dr. Ir. Bersih Tarigant', 'Male', 2, 10, 28712, 22),
(3, 1011, 'Dr. Roland Y. H. Silitonga, M.T.', 'Male', 2, 8, 42712, 15),
(4, 1012, 'Ir. Irawaty Soesanto', 'Female', 2, 5, 24000, 11),
(5, 1013, 'Anggoro Prasetyo Utomo', 'Male', 2, 10, 28708, 7),
(6, 1014, 'Chintya Dwi Valentin', 'Female', 2, 5, 29212, 11),
(7, 1015, 'Perawita Hinca Irawani', 'Female', 2, 4, 80000, 1),
(8, 1026, 'Ir. Inge Martina, M.T.', 'Female', 2, 5, 27000, 11),
(9, 1029, 'Irfin Afifudin ', 'Male', 2, 5, 35000, 4),
(10, 1030, 'Desti Karolina Sinuhaji', 'Female', 2, 5, 46500, 22),
(11, 1031, 'Tamsir H Sirait', 'Male', 2, 5, 23000, 4),
(12, 1032, 'Yosi Yonata', 'Male', 2, 7, 73000, 11),
(13, 1033, 'Dr. Sinung Suakanto, M.T', 'Male', 2, 0, 0, 0),
(14, 1034, 'Cut Fiarni', 'Female', 2, 0, 0, 0),
(15, 1035, 'Evasaria M Sipayung', 'Female', 2, 5, 25050, 11),
(16, 1036, 'Jusak Sali Kosasih', 'Male', 2, 5, 6018, 3),
(17, 1037, 'Marina Cendrakasih', 'Female', 2, 5, 13550, 4),
(18, 1038, 'Dr. Herry Imanta Sitepu', 'Male', 2, 10, 50000, 1),
(19, 1039, 'Venjte Jeremias Lewi E', 'Male', 2, 10, 65518, 7),
(20, 1040, 'Yoyok Yusman G', 'Male', 2, 5, 50000, 1),
(21, 1042, 'Dina Angela', 'Female', 2, 5, 70000, 2),
(22, 1043, 'Maclaurin Hutagalung', 'Male', 2, 20, 75500, 8),
(23, 1044, 'Lidya Handayani S', 'Female', 2, 3, 117466, 23),
(24, 1045, 'Dr. Ir. Ari Setiawan', 'Male', 2, 5, 2500, 2),
(25, 1047, 'Eka Kurnia Asih P', 'Female', 2, 5, 9000, 5),
(26, 1048, 'Dewi Rani Lestari N', 'Female', 2, 5, 23000, 13),
(27, 1058, 'Teguh Ersada Natail S', 'Male', 2, 5, 17000, 9),
(28, 1059, 'Leo Rama Kristiana', 'Female', 2, 5, 10000, 4),
(29, 1060, 'Cindy Himawan', 'Female', 2, 1, 35000, 7),
(30, 1070, 'Henry', 'Male', 2, 5, 14000, 4),
(31, 1074, 'Linda Gandajaya', 'Female', 2, 5, 20000, 4),
(32, 1076, 'Tetty Rencana Sianturi', 'Female', 2, 8, 70000, 7),
(33, 1081, 'Inggrid Jessica Kinanti ', 'Female', 2, 6, 17500, 11),
(34, 1082, 'Yudi Munajat', 'Male', 2, 15, 14000, 11),
(35, 1083, 'Hariol Tarigan ', 'Male', 2, 25, 8700, 7),
(36, 1084, 'Yohana Pebriola ', 'Female', 2, 7, 40500, 22),
(37, 1003, 'Cynthia Tarigan, S.T.', 'Female', 2, 0, 0, 0),
(38, 1004, 'Dra. Johanna W. Tarigant', 'Female', 2, 0, 0, 0),
(39, 1005, 'Grace Hana Tarigan', 'Female', 2, 0, 0, 0),
(40, 1086, 'Fransiska Indah Puspita ', 'Female', 2, 5, 21000, 11),
(41, 1088, 'Lasma Dewi Sartika, S.E.', 'Female', 2, 10, 42500, 24),
(42, 1099, 'Reza Dwi Lestari, S.Pd', 'Male', 2, 5, 3000, 3),
(43, 1100, 'Rizal Sihabudin, S.Ptk.', 'Male', 2, 5, 3000, 3),
(44, 1102, 'Arif Wamilya Pratama', 'Male', 2, 5, 15000, 13),
(45, 1103, 'Yayat Supriatna, S.T.', 'Male', 2, 5, 42000, 29),
(46, 1104, 'Rahmat Solihin, A.Md.', 'Male', 2, 5, 4000, 4),
(47, 1105, 'Husnul Wafa, S.Kom.', 'Male', 2, 5, 19500, 11),
(48, 1106, 'Henry Hendrawan Loen, S.', 'Male', 2, 5, 6000, 4),
(49, 1107, 'Ir. Sandria Sarim, M.M.', 'Male', 2, 4, 50000, 1),
(50, 1108, 'Marlon Egbert Wattimena', 'Male', 2, 10, 5500, 10),
(51, 1109, 'Sian Lee, S.T.', 'Female', 2, 5, 20000, 6),
(52, 1110, 'Hotbin Sitanggang', 'Male', 2, 5, 10000, 11),
(53, 1111, 'Chostaria Gultom, A.Md', 'Female', 2, 5, 39500, 31),
(54, 1112, 'Etsa Essyana Boenga', 'Female', 2, 5, 24565, 31),
(55, 1113, 'Heri Kurniawan', 'Male', 2, 5, 12000, 4),
(56, 1114, 'Tunggul Arief Nugroho', 'Male', 2, 0, 0, 0),
(57, 5001, 'Iman A.', 'Male', 3, 2, 1000, 2),
(58, 5002, 'Galang I. S.', 'Male', 3, 3, 1000, 2),
(59, 5003, 'Martin P.', 'Male', 3, 5, 1100, 3),
(60, 5004, 'Samuel Wu', 'Male', 3, 2, 700, 3),
(61, 5005, 'Gita Vella S.', 'Female', 3, 25, 24350, 27),
(62, 5006, 'Debora Bole', 'Female', 3, 30, 14200, 15),
(63, 5007, 'Merlyn', 'Female', 3, 10, 11700, 22),
(64, 5008, 'Elisabeth S.', 'Female', 3, 20, 21500, 23),
(65, 5009, 'Fellicia', 'Female', 3, 5, 17400, 19),
(66, 5010, 'Eldaa W', 'Female', 3, 5, 18500, 16),
(67, 5011, 'Monika Fitri', 'Female', 3, 10, 12600, 12),
(68, 5012, 'Katherine Claudya', 'Female', 3, 10, 2500, 5),
(69, 5013, 'Monika Meiliana', 'Female', 3, 5, 32400, 10),
(70, 5014, 'Nari', 'Female', 3, 10, 11700, 19),
(71, 5015, 'Yohanna Aurellia S.', 'Female', 3, 10, 8000, 10),
(72, 5016, 'Elika Simamora', 'Female', 3, 10, 3500, 7),
(73, 5017, 'Stella Apriani', 'Female', 3, 10, 2500, 5),
(74, 5018, 'Eleazar Levi Musabani', 'Male', 3, 15, 7000, 11),
(75, 5019, 'Yosua William', 'Male', 3, 1, 1500, 8),
(76, 5020, 'Virantika', 'Female', 3, 10, 10500, 16),
(77, 5021, 'Lily Agrina', 'Female', 3, 5, 80100, 41),
(78, 5022, 'Shandy H.', 'Female', 3, 5, 1000, 2),
(79, 5023, 'Ernawati', 'Female', 3, 5, 23500, 11),
(80, 5024, 'Wiliam Eka', 'Male', 3, 22, 1600, 4),
(81, 5025, 'Yohanes Isa', 'Male', 3, 25, 3500, 4),
(82, 5026, 'Eky Wijaya', 'Male', 3, 20, 5000, 5),
(83, 5027, 'Frida E', 'Female', 3, 10, 57100, 44),
(84, 5028, 'Evelyn S', 'Female', 3, 10, 8400, 17),
(85, 5029, 'Yarni S', 'Female', 3, 5, 61000, 44),
(86, 5030, 'Sisca O', 'Female', 3, 10, 10100, 19),
(87, 5031, 'Silvia A. P.', 'Female', 3, 5, 19000, 17),
(88, 5032, 'Claudya A.', 'Female', 3, 5, 61800, 55),
(89, 5033, 'Rangga', 'Male', 3, 5, 18500, 0),
(90, 5034, 'Albert', 'Male', 3, 10, 7600, 5),
(91, 5035, 'Teofilus Alvin Octavio', 'Male', 3, 20, 2500, 3),
(92, 5036, 'Adrey', 'Male', 3, 10, 3950, 23),
(93, 5037, 'Edenga', 'Male', 3, 0, 0, 0),
(94, 5038, 'Maria Fransiska', 'Female', 3, 10, 5500, 11),
(95, 5039, 'Raymond', 'Male', 3, 0, 0, 0),
(96, 5040, 'Astuty De', 'Female', 3, 5, 26500, 19),
(97, 5041, 'Andrian Tome', 'Male', 3, 10, 1000, 10),
(98, 5042, 'Vernita Mellyna', 'Female', 3, 10, 3500, 5),
(99, 5043, 'Firasyan R. H.', 'Male', 3, 10, 800, 7),
(100, 5044, 'Joshua Sumardi', 'Male', 3, 5, 1400, 0),
(101, 5045, 'Monika Christly', 'Female', 3, 20, 16500, 8),
(102, 5046, 'Brian Henokh', 'Male', 3, 10, 4300, 11),
(103, 5047, 'William Setiawan', 'Male', 3, 10, 1000, 9),
(104, 5048, 'Triandi Shafa', 'Male', 3, 10, 1000, 8),
(105, 3001, 'Herny Mustikawati, B.A.', 'Female', 1, 5, 2000, 2),
(106, 3002, 'Monika Apriana, S.Pd.', 'Female', 1, 5, 0, 1),
(107, 3003, 'Evy Rosali, S.S.', 'Female', 1, 5, 0, 1),
(108, 3004, 'Dwi Riswanto', 'Male', 1, 5, 0, 1),
(109, 3005, 'Maria Fransisca Adriani', 'Female', 1, 5, 0, 1),
(110, 3006, 'Kristanto, S.Psi.', 'Male', 1, 5, 0, 1),
(111, 3007, '?', 'Female', 1, 5, 0, 0),
(112, 3008, 'Hana Irawati, S. Psi.', 'Female', 1, 5, 0, 1),
(113, 3009, 'Adhika Pratama, S. Pd.', 'Male', 1, 5, 0, 1),
(114, 3010, 'Ivan Valiandy, S. E.', 'Male', 1, 5, 0, 1),
(115, 3011, '?', 'Female', 1, 5, 0, 0),
(116, 3012, '?', 'Female', 1, 0, 0, 0),
(117, 3013, 'Dra. Maria Briqitta Kris', 'Female', 1, 5, 0, 1),
(118, 3014, 'Stefanus Roberto, S.Pd.', 'Male', 1, 5, 0, 1),
(119, 3015, 'Jenny M. Simanungkalit', 'Female', 1, 5, 0, 1),
(120, 3016, 'Tatty Sumiarti, S. Sn.', 'Female', 1, 5, 0, 1),
(121, 3017, 'M.H Panjaitan, S.Pd.', 'Male', 1, 5, 0, 1),
(122, 3018, 'Welem Novi Watunglawar, ', 'Male', 1, 5, 0, 1),
(123, 3019, 'Surjati, S.Psi.', 'Female', 1, 5, 0, 1),
(124, 3020, 'Vania Sayora Yuhar', 'Female', 1, 5, 0, 1),
(125, 3021, 'Dendhy Bagus Santoso', 'Male', 1, 5, 3000, 2),
(126, 3022, 'Dra. Anastasia Sudarmi', 'Female', 1, 5, 0, 1),
(127, 3023, 'Iman Alamsyah', 'Male', 1, 5, 0, 1),
(128, 3024, 'Tjang Sumardi', 'Male', 1, 5, 0, 1),
(129, 3025, 'Hanky Purnama, S.S', 'Male', 1, 5, 0, 1),
(130, 3026, 'Drs. Abadi Ginting', 'Male', 1, 5, 0, 1),
(131, 3027, '?', 'Female', 1, 0, 0, 0),
(132, 3028, '?', 'Female', 1, 0, 0, 0),
(133, 3029, '?', 'Female', 1, 0, 0, 0),
(134, 3030, '?', 'Female', 1, 0, 0, 0),
(135, 5049, 'Valdy', 'Male', 3, 0, 0, 0),
(136, 5050, 'Kevin', 'Male', 3, 0, 0, 0),
(137, 5051, 'Satria', 'Male', 3, 0, 0, 0),
(138, 5052, 'Evan', 'Male', 3, 0, 0, 0),
(139, 1023, 'Bernadetha Arina Santika', 'Female', 2, 0, 0, 0),
(140, 1021, 'Nining Siswaningrum, S.E', 'Female', 2, 0, 0, 0),
(141, 5053, 'Samuel', 'Male', 3, 10, 20000, 10),
(142, 5054, 'Sendy Dematra T.', 'Male', 3, 0, 0, 0),
(143, 5055, 'Armando Surbakti', 'Male', 3, 20, 2550, 6),
(144, 5056, 'Yusuf Andi', 'Male', 3, 7, 2000, 4),
(145, 5057, 'Adil S. Dachi', 'Male', 3, 10, 31000, 35),
(146, 5058, '?', 'Female', 3, 0, 0, 0),
(147, 5059, '?', 'Female', 3, 0, 0, 0),
(148, 5060, '?', 'Female', 3, 0, 0, 0),
(149, 5061, '?', 'Female', 3, 0, 0, 0),
(150, 5062, '?', 'Female', 3, 0, 0, 0);

--
-- Indexes for dumped tables
--

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
-- AUTO_INCREMENT for table `registration`
--
ALTER TABLE `registration`
  MODIFY `registration_ID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=151;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `registration`
--
ALTER TABLE `registration`
  ADD CONSTRAINT `kategori_relation` FOREIGN KEY (`kategori_ID`) REFERENCES `kategori` (`kategori_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
