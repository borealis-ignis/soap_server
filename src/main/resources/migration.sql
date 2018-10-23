CREATE DATABASE IF NOT EXISTS `service_static` DEFAULT CHARACTER SET utf8;
use `service_static`;


DROP TABLE IF EXISTS `cities`;
DROP TABLE IF EXISTS `countries`;
DROP TABLE IF EXISTS `currencies`;

create table `currencies` (
  `id` serial not null,
  `code` varchar(3) not null,
  primary key (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `countries` (
  `country_id` serial not null,
  `name` varchar(300) not null,
  `currency_id` bigint unsigned not null,
  foreign key (`currency_id`) references currencies(`id`),
  primary key (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `cities` (
  `city_id` serial not null,
  `country_id` bigint unsigned not null,
  `name` varchar(300) not null,
  `active` tinyint(1) not null,
  foreign key (`country_id`) references countries(`country_id`),
  primary key (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert into `currencies`(code) values('EUR');
SET @eur = LAST_INSERT_ID();
insert into `currencies`(code) values('BYN');
SET @byn = LAST_INSERT_ID();
insert into `currencies`(code) values('pln');
SET @pln = LAST_INSERT_ID();

insert into `countries`(name, currency_id) values('Spain', @eur);
SET @country = LAST_INSERT_ID();
insert into `cities`(name, country_id, active) values('Madrid', @country, 1);
insert into `cities`(name, country_id, active) values('Barcelona', @country, 1);

insert into `countries`(name, currency_id) values('Belarus', @byn);
SET @country = LAST_INSERT_ID();
insert into `cities`(name, country_id, active) values('Minsk', @country, 1);
insert into `cities`(name, country_id, active) values('Mozyr', @country, 1);

insert into `countries`(name, currency_id) values('Poland', @pln);
SET @country = LAST_INSERT_ID();
insert into `cities`(name, country_id, active) values('Warsaw', @country, 1);
insert into `cities`(name, country_id, active) values('Krakow', @country, 1);

insert into countries (name, currency_id) values ('Fake', 1);