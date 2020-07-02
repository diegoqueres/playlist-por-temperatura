
drop table if exists recommendations cascade;
drop table if exists users cascade;
drop table if exists cities cascade;
drop table if exists countries cascade;

create table cities (
  ID bigint not null,
  LATITUDE float null,
  LONGITUDE float null,
  NAME varchar(255) not null,
  COUNTRY_ID int null,
  primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table countries (
  ID int not null,
  CODE varchar(255) not null,
  primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table recommendations (
  ID bigint not null auto_increment,
  CREATED_DATE timestamp not null,
  GENRE int not null,
  TEMPERATURE float null,
  CITY_ID bigint null,
  USER_ID int null,
  primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `users` (
  ID int not null auto_increment,
  ACTIVE boolean not null,
  CREATED_DATE timestamp not null default now(),
  EMAIL varchar(255) not null,
  NAME varchar(255) not null,
  PASSWORD varchar(255) not null,
  ROLE int not null,
  UPDATED_DATE timestamp not null default now(),
  primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table cities
  add constraint CITIES_COUNTRIES_FK
    foreign key (COUNTRY_ID)
    references countries(id);
alter table recommendations
  add constraint RECOMMENDATIONS_CITIES_FK
    foreign key (CITY_ID)
    references cities(id);
alter table recommendations
  add constraint RECOMMENDATIONS_USERS_FK
    foreign key (USER_ID)
    references users(id);
   

/* Tables that are not mapped in Hibernate */
drop table if exists roles cascade;
drop table if exists genres cascade;

create table roles (
  ID int not null, 
  name varchar(255) not null,
  primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table genres (
  ID int not null, 
  name varchar(255) not null,
  primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table users
  add constraint USERS_ROLES_FK
    foreign key (ROLE)
    references roles(id);

alter table recommendations
  add constraint RECOMMENDATIONS_GENRES_FK
    foreign key (GENRE)
    references genres(id);