/* Roles */
insert into `ROLES` (id, name) values (1, 'USER');
insert into `ROLES` (id, name) values (2,  'ADMIN');


/* Genres */
insert into `GENRES` (id, name) values (1, 'Rock');
insert into `GENRES` (id, name) value (2, 'Pop');
insert into `GENRES` (id, name) values (3, 'Música clássica');


/* User Default admin */
  insert 
    into
        `USERS`
        (active, email, name, password, role) 
    values
        (1, 'admin@admin.com.br', 'Admin', 'U&&PSa5h?4@p+UA', 2);