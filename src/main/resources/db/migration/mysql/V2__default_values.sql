/* Roles */
insert into `roles` (id, name) values (1, 'USER');
insert into `roles` (id, name) values (2,  'ADMIN');


/* Genres */
insert into `genres` (id, name) values (1, 'Rock');
insert into `genres` (id, name) value (2, 'Pop');
insert into `genres` (id, name) values (3, 'Música clássica');


/* User Default admin */
/* Admin original password: U&&PSa5h?4@p+UA */
  insert 
    into
        `users`
        (active, email, name, password, role) 
    values
        (1, 'admin@admin.com.br', 'Admin', '$2a$10$95GCdkbP.Y.45z10FrxobumgPHUHGqqXybs0FvM12ibDPQSkpA/U.', 2);