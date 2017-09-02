CREATE DATABASE IF NOT EXISTS `service_static` DEFAULT CHARACTER SET utf8;
use `service_static`;

DROP TABLE IF EXISTS `countries`;
create table `countries` (
  `country_id` serial not null,
  `name` varchar(300) not null,
  `currency` varchar(300) not null,
  primary key (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `cities`;
create table `cities` (
  `city_id` serial not null,
  `country_id` bigint unsigned not null,
  `name` varchar(300) not null,
  `active` tinyint(1) not null,
  foreign key (`country_id`) references countries(`country_id`),
  primary key (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `countries`(name, currency) values('Spain', 'EUR');
SET @country = LAST_INSERT_ID();
insert into `cities`(name, country_id, active) values('Madrid', @country, 1);
insert into `cities`(name, country_id, active) values('Barcelona', @country, 1);

insert into `countries`(name, currency) values('Belarus', 'BYN');
SET @country = LAST_INSERT_ID();
insert into `cities`(name, country_id, active) values('Minsk', @country, 1);
insert into `cities`(name, country_id, active) values('Mozyr', @country, 1);

insert into `countries`(name, currency) values('Poland', 'PLN');
SET @country = LAST_INSERT_ID();
insert into `cities`(name, country_id, active) values('Warsaw', @country, 1);
insert into `cities`(name, country_id, active) values('Krakow', @country, 1);