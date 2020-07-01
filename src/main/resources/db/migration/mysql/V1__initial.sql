
drop table if exists RECOMMENDATIONS cascade;
drop table if exists USERS cascade;
drop table if exists CITIES cascade;
drop table if exists COUNTRIES cascade;

create table CITIES (
  ID bigint not null,
  LATITUDE float null,
  LONGITUDE float null,
  NAME varchar(255) not null,
  COUNTRY_ID int null,
  primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table COUNTRIES (
  ID int not null,
  CODE varchar(255) not null,
  primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table RECOMMENDATIONS (
  ID bigint not null auto_increment,
  CREATED_DATE timestamp not null,
  GENRE int not null,
  TEMPERATURE float null,
  CITY_ID bigint null,
  USER_ID int null,
  primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `USERS` (
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

alter table CITIES
  add constraint CITIES_COUNTRIES_FK
    foreign key (COUNTRY_ID)
    references COUNTRIES(id);
alter table RECOMMENDATIONS
  add constraint RECOMMENDATIONS_CITIES_FK
    foreign key (CITY_ID)
    references CITIES(id);
alter table RECOMMENDATIONS
  add constraint RECOMMENDATIONS_USERS_FK
    foreign key (USER_ID)
    references USERS(id);
   

/* Tables that are not mapped in Hibernate */
drop table if exists ROLES cascade;
drop table if exists GENRES cascade;

create table ROLES (
  ID int not null, 
  name varchar(255) not null,
  primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table GENRES (
  ID int not null, 
  name varchar(255) not null,
  primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table USERS
  add constraint USERS_ROLES_FK
    foreign key (ROLE)
    references ROLES(id);

alter table RECOMMENDATIONS
  add constraint RECOMMENDATIONS_GENRES_FK
    foreign key (GENRE)
    references GENRES(id);