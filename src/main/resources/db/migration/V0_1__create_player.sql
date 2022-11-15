create table player (
    id uuid primary key,
    username varchar(30) not null,
    password varchar(60) not null,
    email varchar(50) not null,
    first_name varchar(20) not null,
    last_name varchar(30) not null,
    created_at timestamp not null,
    is_active bool not null
);

create table roles (
    id uuid primary key,
    name varchar(20) not null
);

create table player_roles (
    player_id uuid not null,
    role_id uuid not null,
    primary key (player_id, role_id)
);

alter table player_roles add constraint role_id_fk foreign key (role_id) references roles;

alter table player_roles add constraint player_id_fk foreign key (player_id) references player;

insert into
    roles(id, name)
values
    ('21e3f8c1-9f8c-437d-9ae5-de635dc8d202', 'ROLE_USER'),
    ('463ba3a0-fe9b-4453-ad60-dc2492c9ecff', 'ROLE_ADMIN');