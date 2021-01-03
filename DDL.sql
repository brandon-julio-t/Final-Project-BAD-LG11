drop database if exists Clothing_Store;

create database Clothing_Store;

use Clothing_Store;

create table `User`
(
    UserId          int          not null primary key auto_increment,
    UserName        varchar(50)  not null,
    UserGender      varchar(6)   not null,
    UserAddress     varchar(255) not null,
    UserPhoneNumber char(12)     not null,
    UserEmail       varchar(50)  not null,
    UserPassword    varchar(255) not null
);

create table Customer
(
    UserId        int not null primary key,
    CustomerPoint int not null,
    foreign key (UserId) references User (UserId)
);

create table StaffPosition
(
    StaffPositionId   int not null primary key auto_increment,
    StaffPositionName varchar(50)
);

create table Staff
(
    UserId          int      not null primary key,
    StaffPositionId int      not null,
    StaffSalary     int      not null,
    NPWP            char(15) not null,
    foreign key (UserId) references User (UserId),
    foreign key (StaffPositionId) references StaffPosition (StaffPositionId)
);

create table Vendor
(
    VendorId          int          not null primary key auto_increment,
    VendorName        varchar(50)  not null,
    VendorEmail       varchar(50)  not null,
    VendorAddress     varchar(255) not null,
    VendorPhoneNumber char(12)     not null
);

create table ClothingSize
(
    ClothingSizeId   int         not null primary key auto_increment,
    ClothingSizeName varchar(10) not null
);

create table ClothingType
(
    ClothingTypeId   int         not null primary key auto_increment,
    ClothingTypeName varchar(50) not null
);

create table Clothing
(
    ClothingId     int         not null primary key auto_increment,
    ClothingSizeId int         not null,
    ClothingTypeId int         not null,
    ClothingName   varchar(50) not null,
    ClothingPrice  int         not null,
    ClothingStock  int         not null,
    foreign key (ClothingSizeId) references ClothingSize (ClothingSizeId),
    foreign key (ClothingTypeId) references ClothingType (ClothingTypeiD)
);

create table Review
(
    ReviewId          int          not null primary key auto_increment,
    UserId            int          not null,
    ClothingId        int          not null,
    ReviewScore       int          not null,
    ReviewDescription varchar(255) not null,
    ReviewDate        date         not null,
    foreign key (UserId) references User (UserId),
    foreign key (ClothingId) references Clothing (ClothingId)
);

create table SaleHeader
(
    SaleId     int  not null primary key auto_increment,
    CustomerId int  not null,
    SaleDate   date not null,
    foreign key (CustomerId) references Customer (UserId)
);


create table SaleDetail
(
    SaleId       int not null,
    ClothingId   int not null,
    SaleQuantity int not null,
    primary key (SaleId, ClothingId),
    foreign key (SaleId) references SaleHeader (SaleId),
    foreign key (ClothingId) references Clothing (ClothingId)
);

create table PurchaseHeader
(
    PurchaseId   int  not null primary key auto_increment,
    StaffId      int  not null,
    VendorId     int  not null,
    PurchaseDate date not null,
    foreign key (StaffId) references Staff (UserId),
    foreign key (VendorId) references Vendor (VendorId)
);

create table PurchaseDetail
(
    PurchaseId       int not null,
    ClothingId       int not null,
    PurchaseQuantity int not null,
    primary key (PurchaseId, ClothingId),
    foreign key (PurchaseId) references PurchaseHeader (PurchaseId),
    foreign key (ClothingId) references Clothing (ClothingId)
);

insert into `User` (UserName, UserGender, UserAddress, UserPhoneNumber, UserEmail, UserPassword)
values ('admin', 'Male', '', '085155228431', 'admin@admin.com', 'admin');

insert into `User` (UserName, UserGender, UserAddress, UserPhoneNumber, UserEmail, UserPassword)
values ('Siward Battisson', 'Male', '1 Carioca Plaza', '816 830 3827', 'sbattisson0@cbsnews.com',
        'UBUBwN3nneM'),
       ('Maitilde Sheringham', 'Female', '0327 Larry Circle', '494 802 4129', 'msheringham1@webnode.com',
        'aH7mdrJXcr5'),
       ('Friederike Elcombe', 'Female', '809 Porter Lane', '622 330 9400', 'felcombe2@delicious.com',
        'rCqFseeTiF'),
       ('Tom Coucher', 'Male', '156 3rd Terrace', '803 371 2933', 'tcoucher3@netscape.com',
        '8bOUpEMl6gv'),
       ('Prent Olland', 'Male', '3764 Cordelia Junction', '879 379 9971', 'polland4@apple.com',
        'UcmrsGdNyQJ'),
       ('Angy Colbridge', 'Female', '5 Towne Pass', '260 901 5388', 'acolbridge5@netscape.com',
        'l7qKFYu'),
       ('Saunders Stoodley', 'Male', '4700 Graceland Road', '958 271 5124', 'sstoodley6@parallels.com',
        'moCM5CEuMc'),
       ('Sigvard Brogden', 'Male', '31329 Maywood Place', '470 147 1570', 'sbrogden7@scribd.com',
        '2D6lX2'),
       ('Lanie Josipovic', 'Female', '9218 Barnett Pass', '112 989 6322', 'ljosipovic8@360.cn',
        'AzdQYjqax1pQ'),
       ('Waylon Deery', 'Male', '6846 Oak Court', '573 134 9108', 'wdeery9@hibu.com', 'rj2Xt7Kt');

insert into `Customer` (UserId, CustomerPoint)
values (2, 0),
       (3, 0),
       (4, 0),
       (5, 0),
       (6, 0);

insert into StaffPosition (StaffPositionName)
values ('admin'),
       ('staff');

insert into `Staff` (UserId, StaffPositionId, StaffSalary, NPWP)
values (1, 1, 1000000, '123456789012345'),
       (7, 2, 1000000, '123456789012345'),
       (8, 2, 1000000, '123456789012345'),
       (9, 2, 1000000, '123456789012345'),
       (10, 2, 1000000, '123456789012345'),
       (11, 2, 1000000, '123456789012345');

insert into ClothingSize (ClothingSizeName)
values ('L'),
       ('M'),
       ('S'),
       ('XL'),
       ('XS'),
       ('XXL');

insert into ClothingType (ClothingTypeName)
values ('Hoodie'),
       ('Jacket'),
       ('Jeans'),
       ('Pants'),
       ('Shirt'),
       ('Sweetheart'),
       ('T-Shirt'),
       ('Trousers');

insert into Clothing (ClothingSizeId, ClothingTypeId, ClothingName, ClothingPrice, ClothingStock)
values (1, 1, 'Hakurei Reimu', 10000, 100),
       (2, 2, 'Marisa Kirisame', 15000, 100),
       (3, 3, 'Utsuho Reiuji', 20000, 100),
       (4, 4, 'Cirno', 25000, 100),
       (5, 5, 'Remilia Scarlet', 32000, 100),
       (6, 6, 'Flandre Scarlet', 42000, 100),
       (1, 7, 'Sakura Izayoi', 50000, 100),
       (2, 8, 'Hong Meiling', 100000, 100);

insert into Vendor (VendorName, VendorEmail, VendorAddress, VendorPhoneNumber)
values ('Gladeche Company', 'dgladechecompany0@lycos.com,', '31669 Brentwood Way', '619 689 9615'),
       ('Brazenor Company', 'lbrazenorcompany1@gov.uk,', '5017 Fremont Junction', '581 328 8841'),
       ('McAllan Company', 'dmcallancompany2@google.it,', '9 Fallview Center', '207 728 2376'),
       ('Daines Company', 'kdainescompany3@is.gd,', '4 Bluestem Junction', '643 366 2468'),
       ('Growcock Company', 'agrowcockcompany4@craigslist.org,', '18302 Di Loreto Plaza', '567 907 1552'),
       ('Wyndham Company', 'ewyndhamcompany5@hao123.com,', '9708 Arizona Parkway', '799 633 5197'),
       ('Pearce Company', 'dpearcecompany6@nbcnews.com,', '1304 Crest Line Plaza', '306 409 4450'),
       ('Karpe Company', 'skarpecompany7@gmpg.org,', '398 Eliot Point', '730 292 4665'),
       ('Lawling Company', 'slawlingcompany8@walmart.com,', '8976 Duke Plaza', '980 495 6632'),
       ('Shurlock Company', 'vshurlockcompany9@comsenz.com,', '6767 Memorial Place', '260 878 4148');

select COLUMN_NAME                                                                as 'Column Name',
       DATA_TYPE                                                                  as 'Data Type',
       substring(COLUMN_TYPE, position('(' in COLUMN_TYPE) + 1,
                 position(')' in COLUMN_TYPE) - position('(' in COLUMN_TYPE) - 1) as 'Type Size',
       IS_NULLABLE                                                                as 'Nullable',
       ''                                                                         as 'Notes'
from information_schema.COLUMNS
where TABLE_SCHEMA = 'BAD_Teori'
  and TABLE_NAME = 'vendor'
