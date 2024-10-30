create table address
(
    id      bigint auto_increment
        primary key,
    user_id bigint null,
    area    varchar(255) null,
    city    varchar(255) null
);

create table user
(
    address_id bigint null,
    id         bigint auto_increment
        primary key,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    constraint UKdhlcfg8h1drrgu0irs1ro3ohb
        unique (address_id),
    constraint FKddefmvbrws3hvl5t0hnnsv8ox
        foreign key (address_id) references address (id)
);

create table user_order
(
    price       decimal(38, 2) null,
    quantity    int null,
    total_price decimal(38, 2) null,
    id          bigint auto_increment
        primary key,
    user_id     bigint null,
    item_name   varchar(255) null,
    constraint FKddefmvbrws3hvl5t0hnnsv8ob
        foreign key (user_id) references user (id)
);





ó