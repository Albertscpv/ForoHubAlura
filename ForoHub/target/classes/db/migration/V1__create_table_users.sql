create table users(
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    cellphoneNumber varchar(100) not null unique,
    country varchar(100) not null,

    primary key(id)
);