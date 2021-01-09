-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 09, 2021 at 07:40 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 7.4.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lumiere_store`
--

-- --------------------------------------------------------

--
-- Table structure for table `clothing`
--

CREATE TABLE `clothing` (
  `ClothingId` int(11) NOT NULL,
  `ClothingSizeId` int(11) NOT NULL,
  `ClothingTypeId` int(11) NOT NULL,
  `ClothingName` varchar(50) NOT NULL,
  `ClothingPrice` int(11) NOT NULL,
  `ClothingStock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `clothing`
--

INSERT INTO `clothing` (`ClothingId`, `ClothingSizeId`, `ClothingTypeId`, `ClothingName`, `ClothingPrice`, `ClothingStock`) VALUES
(1, 1, 1, 'Hakurei Reimu', 10000, 100),
(2, 2, 2, 'Marisa Kirisame', 15000, 100),
(3, 3, 3, 'Utsuho Reiuji', 20000, 100),
(4, 4, 4, 'Cirno', 25000, 100),
(5, 5, 5, 'Remilia Scarlet', 32000, 100),
(6, 6, 6, 'Flandre Scarlet', 42000, 100),
(7, 1, 7, 'Sakura Izayoi', 50000, 100),
(8, 2, 8, 'Hong Meiling', 100000, 100);

-- --------------------------------------------------------

--
-- Table structure for table `clothingsize`
--

CREATE TABLE `clothingsize` (
  `ClothingSizeId` int(11) NOT NULL,
  `ClothingSizeName` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `clothingsize`
--

INSERT INTO `clothingsize` (`ClothingSizeId`, `ClothingSizeName`) VALUES
(1, 'L'),
(2, 'M'),
(3, 'S'),
(4, 'XL'),
(5, 'XS'),
(6, 'XXL');

-- --------------------------------------------------------

--
-- Table structure for table `clothingtype`
--

CREATE TABLE `clothingtype` (
  `ClothingTypeId` int(11) NOT NULL,
  `ClothingTypeName` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `clothingtype`
--

INSERT INTO `clothingtype` (`ClothingTypeId`, `ClothingTypeName`) VALUES
(1, 'Hoodie'),
(2, 'Jacket'),
(3, 'Jeans'),
(4, 'Pants'),
(5, 'Shirt'),
(6, 'Sweetheart'),
(7, 'T-Shirt'),
(8, 'Trousers');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `UserId` int(11) NOT NULL,
  `CustomerPoint` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`UserId`, `CustomerPoint`) VALUES
(2, 0),
(3, 0),
(4, 0),
(5, 0),
(6, 0);

-- --------------------------------------------------------

--
-- Table structure for table `purchasedetail`
--

CREATE TABLE `purchasedetail` (
  `PurchaseId` int(11) NOT NULL,
  `ClothingId` int(11) NOT NULL,
  `PurchasePrice` int(11) NOT NULL,
  `PurchaseQuantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `purchaseheader`
--

CREATE TABLE `purchaseheader` (
  `PurchaseId` int(11) NOT NULL,
  `StaffId` int(11) NOT NULL,
  `VendorId` int(11) NOT NULL,
  `PurchaseDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `ReviewId` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  `ClothingId` int(11) NOT NULL,
  `ReviewScore` int(11) NOT NULL,
  `ReviewDescription` varchar(255) NOT NULL,
  `ReviewDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `saledetail`
--

CREATE TABLE `saledetail` (
  `SaleId` int(11) NOT NULL,
  `ClothingId` int(11) NOT NULL,
  `SalePrice` int(11) NOT NULL,
  `SaleQuantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `saleheader`
--

CREATE TABLE `saleheader` (
  `SaleId` int(11) NOT NULL,
  `CustomerId` int(11) NOT NULL,
  `SaleDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `UserId` int(11) NOT NULL,
  `StaffPositionId` int(11) NOT NULL,
  `StaffSalary` int(11) NOT NULL,
  `NPWP` char(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`UserId`, `StaffPositionId`, `StaffSalary`, `NPWP`) VALUES
(1, 1, 1000000, '123456789012345'),
(7, 2, 1000000, '123456789012345'),
(8, 2, 1000000, '123456789012345'),
(9, 2, 1000000, '123456789012345'),
(10, 2, 1000000, '123456789012345'),
(11, 2, 1000000, '123456789012345');

-- --------------------------------------------------------

--
-- Table structure for table `staffposition`
--

CREATE TABLE `staffposition` (
  `StaffPositionId` int(11) NOT NULL,
  `StaffPositionName` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `staffposition`
--

INSERT INTO `staffposition` (`StaffPositionId`, `StaffPositionName`) VALUES
(1, 'admin'),
(2, 'staff');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `UserId` int(11) NOT NULL,
  `UserName` varchar(50) NOT NULL,
  `UserGender` varchar(6) NOT NULL,
  `UserAddress` varchar(255) NOT NULL,
  `UserPhoneNumber` char(12) NOT NULL,
  `UserEmail` varchar(50) NOT NULL,
  `UserPassword` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`UserId`, `UserName`, `UserGender`, `UserAddress`, `UserPhoneNumber`, `UserEmail`, `UserPassword`) VALUES
(1, 'admin', 'Male', '', '085155228431', 'admin@admin.com', 'admin'),
(2, 'Siward Battisson', 'Male', '1 Carioca Plaza', '816 830 3827', 'sbattisson0@cbsnews.com', 'UBUBwN3nneM'),
(3, 'Maitilde Sheringham', 'Female', '0327 Larry Circle', '494 802 4129', 'msheringham1@webnode.com', 'aH7mdrJXcr5'),
(4, 'Friederike Elcombe', 'Female', '809 Porter Lane', '622 330 9400', 'felcombe2@delicious.com', 'rCqFseeTiF'),
(5, 'Tom Coucher', 'Male', '156 3rd Terrace', '803 371 2933', 'tcoucher3@netscape.com', '8bOUpEMl6gv'),
(6, 'Prent Olland', 'Male', '3764 Cordelia Junction', '879 379 9971', 'polland4@apple.com', 'UcmrsGdNyQJ'),
(7, 'Angy Colbridge', 'Female', '5 Towne Pass', '260 901 5388', 'acolbridge5@netscape.com', 'l7qKFYu'),
(8, 'Saunders Stoodley', 'Male', '4700 Graceland Road', '958 271 5124', 'sstoodley6@parallels.com', 'moCM5CEuMc'),
(9, 'Sigvard Brogden', 'Male', '31329 Maywood Place', '470 147 1570', 'sbrogden7@scribd.com', '2D6lX2'),
(10, 'Lanie Josipovic', 'Female', '9218 Barnett Pass', '112 989 6322', 'ljosipovic8@360.cn', 'AzdQYjqax1pQ'),
(11, 'Waylon Deery', 'Male', '6846 Oak Court', '573 134 9108', 'wdeery9@hibu.com', 'rj2Xt7Kt');

-- --------------------------------------------------------

--
-- Table structure for table `vendor`
--

CREATE TABLE `vendor` (
  `VendorId` int(11) NOT NULL,
  `VendorName` varchar(50) NOT NULL,
  `VendorEmail` varchar(50) NOT NULL,
  `VendorAddress` varchar(255) NOT NULL,
  `VendorPhoneNumber` char(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `vendor`
--

INSERT INTO `vendor` (`VendorId`, `VendorName`, `VendorEmail`, `VendorAddress`, `VendorPhoneNumber`) VALUES
(1, 'Gladeche Company', 'dgladechecompany0@lycos.com,', '31669 Brentwood Way', '619 689 9615'),
(2, 'Brazenor Company', 'lbrazenorcompany1@gov.uk,', '5017 Fremont Junction', '581 328 8841'),
(3, 'McAllan Company', 'dmcallancompany2@google.it,', '9 Fallview Center', '207 728 2376'),
(4, 'Daines Company', 'kdainescompany3@is.gd,', '4 Bluestem Junction', '643 366 2468'),
(5, 'Growcock Company', 'agrowcockcompany4@craigslist.org,', '18302 Di Loreto Plaza', '567 907 1552'),
(6, 'Wyndham Company', 'ewyndhamcompany5@hao123.com,', '9708 Arizona Parkway', '799 633 5197'),
(7, 'Pearce Company', 'dpearcecompany6@nbcnews.com,', '1304 Crest Line Plaza', '306 409 4450'),
(8, 'Karpe Company', 'skarpecompany7@gmpg.org,', '398 Eliot Point', '730 292 4665'),
(9, 'Lawling Company', 'slawlingcompany8@walmart.com,', '8976 Duke Plaza', '980 495 6632'),
(10, 'Shurlock Company', 'vshurlockcompany9@comsenz.com,', '6767 Memorial Place', '260 878 4148');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clothing`
--
ALTER TABLE `clothing`
  ADD PRIMARY KEY (`ClothingId`),
  ADD KEY `ClothingSizeId` (`ClothingSizeId`),
  ADD KEY `ClothingTypeId` (`ClothingTypeId`);

--
-- Indexes for table `clothingsize`
--
ALTER TABLE `clothingsize`
  ADD PRIMARY KEY (`ClothingSizeId`);

--
-- Indexes for table `clothingtype`
--
ALTER TABLE `clothingtype`
  ADD PRIMARY KEY (`ClothingTypeId`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`UserId`);

--
-- Indexes for table `purchasedetail`
--
ALTER TABLE `purchasedetail`
  ADD PRIMARY KEY (`PurchaseId`,`ClothingId`),
  ADD KEY `ClothingId` (`ClothingId`);

--
-- Indexes for table `purchaseheader`
--
ALTER TABLE `purchaseheader`
  ADD PRIMARY KEY (`PurchaseId`),
  ADD KEY `StaffId` (`StaffId`),
  ADD KEY `VendorId` (`VendorId`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`ReviewId`),
  ADD KEY `UserId` (`UserId`),
  ADD KEY `ClothingId` (`ClothingId`);

--
-- Indexes for table `saledetail`
--
ALTER TABLE `saledetail`
  ADD PRIMARY KEY (`SaleId`,`ClothingId`),
  ADD KEY `ClothingId` (`ClothingId`);

--
-- Indexes for table `saleheader`
--
ALTER TABLE `saleheader`
  ADD PRIMARY KEY (`SaleId`),
  ADD KEY `CustomerId` (`CustomerId`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`UserId`),
  ADD KEY `StaffPositionId` (`StaffPositionId`);

--
-- Indexes for table `staffposition`
--
ALTER TABLE `staffposition`
  ADD PRIMARY KEY (`StaffPositionId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserId`);

--
-- Indexes for table `vendor`
--
ALTER TABLE `vendor`
  ADD PRIMARY KEY (`VendorId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clothing`
--
ALTER TABLE `clothing`
  MODIFY `ClothingId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `clothingsize`
--
ALTER TABLE `clothingsize`
  MODIFY `ClothingSizeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `clothingtype`
--
ALTER TABLE `clothingtype`
  MODIFY `ClothingTypeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `purchaseheader`
--
ALTER TABLE `purchaseheader`
  MODIFY `PurchaseId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `ReviewId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `saleheader`
--
ALTER TABLE `saleheader`
  MODIFY `SaleId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `staffposition`
--
ALTER TABLE `staffposition`
  MODIFY `StaffPositionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `UserId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `vendor`
--
ALTER TABLE `vendor`
  MODIFY `VendorId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `clothing`
--
ALTER TABLE `clothing`
  ADD CONSTRAINT `clothing_ibfk_1` FOREIGN KEY (`ClothingSizeId`) REFERENCES `clothingsize` (`ClothingSizeId`),
  ADD CONSTRAINT `clothing_ibfk_2` FOREIGN KEY (`ClothingTypeId`) REFERENCES `clothingtype` (`ClothingTypeId`);

--
-- Constraints for table `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`UserId`) REFERENCES `user` (`UserId`);

--
-- Constraints for table `purchasedetail`
--
ALTER TABLE `purchasedetail`
  ADD CONSTRAINT `purchasedetail_ibfk_1` FOREIGN KEY (`PurchaseId`) REFERENCES `purchaseheader` (`PurchaseId`),
  ADD CONSTRAINT `purchasedetail_ibfk_2` FOREIGN KEY (`ClothingId`) REFERENCES `clothing` (`ClothingId`);

--
-- Constraints for table `purchaseheader`
--
ALTER TABLE `purchaseheader`
  ADD CONSTRAINT `purchaseheader_ibfk_1` FOREIGN KEY (`StaffId`) REFERENCES `staff` (`UserId`),
  ADD CONSTRAINT `purchaseheader_ibfk_2` FOREIGN KEY (`VendorId`) REFERENCES `vendor` (`VendorId`);

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `review_ibfk_1` FOREIGN KEY (`UserId`) REFERENCES `user` (`UserId`),
  ADD CONSTRAINT `review_ibfk_2` FOREIGN KEY (`ClothingId`) REFERENCES `clothing` (`ClothingId`);

--
-- Constraints for table `saledetail`
--
ALTER TABLE `saledetail`
  ADD CONSTRAINT `saledetail_ibfk_1` FOREIGN KEY (`SaleId`) REFERENCES `saleheader` (`SaleId`),
  ADD CONSTRAINT `saledetail_ibfk_2` FOREIGN KEY (`ClothingId`) REFERENCES `clothing` (`ClothingId`);

--
-- Constraints for table `saleheader`
--
ALTER TABLE `saleheader`
  ADD CONSTRAINT `saleheader_ibfk_1` FOREIGN KEY (`CustomerId`) REFERENCES `customer` (`UserId`);

--
-- Constraints for table `staff`
--
ALTER TABLE `staff`
  ADD CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`UserId`) REFERENCES `user` (`UserId`),
  ADD CONSTRAINT `staff_ibfk_2` FOREIGN KEY (`StaffPositionId`) REFERENCES `staffposition` (`StaffPositionId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
