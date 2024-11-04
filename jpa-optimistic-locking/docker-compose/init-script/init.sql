use `rnd-jpa`;

create table address
(
    id      bigint auto_increment
        primary key,
    user_id bigint       null,
    area    varchar(255) null,
    city    varchar(255) null,
    created_at datetime(6)  null,
    updated_at datetime(6)  null,
    created_by varchar(255) null,
    updated_by varchar(255) null
);

create table user
(
    address_id bigint       null,
    id         bigint auto_increment
        primary key,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    created_at datetime(6)  null,
    updated_at datetime(6)  null,
    created_by varchar(255) null,
    updated_by varchar(255) null,
    constraint UK_address
        unique (address_id),
    constraint FK_address
        foreign key (address_id) references address (id)
);

create table user_order
(
    price       decimal(38, 2) null,
    quantity    int            null,
    total_price decimal(38, 2) null,
    id          bigint auto_increment
        primary key,
    user_id     bigint         null,
    item_name   varchar(255)   null,
    created_at datetime(6)  null,
    updated_at datetime(6)  null,
    created_by varchar(255) null,
    updated_by varchar(255) null,
    constraint FK_user_id
        foreign key (user_id) references user (id)
);

create table user_balance
(
    balance decimal(38, 2) null,
    id      bigint auto_increment
        primary key,
    user_id bigint         null,
    version bigint         not null,
    constraint FK_balance
        foreign key (user_id) references user (id)
);

