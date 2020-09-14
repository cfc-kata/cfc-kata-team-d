create table if not exists sale_order (
    id varchar(20) not null,
    create_time datetime not null,
    customer_id varchar(20) not null,
    total_price decimal(12, 2) not null default 0,
    total_payment decimal(12, 2) not null default 0,
    status tinyint not null default 0,
    version int,
    primary key (id)
);

create table if not exists product (
    id varchar(20) not null,
    name varchar(200) not null,
    price decimal(12,2) not null default 0,
    primary key (id)
);

create table if not exists order_item (
    id bigint auto_increment,
    order_id varchar(20) not null,
    prod_id varchar(20) not null,
    amount decimal(12,2) not null default 0,
    sub_total decimal(12,2) not null default 0,
    primary key (id)
);

create table if not exists customer (
    id varchar(20) not null,
    name varchar(40) not null,
    id_number varchar(20),
    mobile_phone varchar(20),
    primary key (id)
);


create table if not exists repayment (
    id varchar(20) not null,
    create_time datetime not null,
    repayment_type varchar(20) not null,
    success_items_num int,
    failed_items_num int,
	version int,
    primary key (id)
);

create table if not exists repayment_plan (
    id varchar(20) not null,
    create_time datetime not null,
    repayment_id varchar(20) not null,
    loan_id varchar(20) not null,
	repayment_plan_Id varchar(20) not null,
	amount decimal(12,2) not null default 0,
	repayment_bank_account varchar(20) not null,
	repayment_sts varchar(20),
    primary key (id)
);

create table if not exists t_contract (
    id varchar(32) not null,
    customer_id varchar(32) not null,
    amt decimal(12, 2) not null default 0,
    draw_amt decimal(12, 2) not null default 0,
    rate decimal(12, 4) not null default 0,
    term int,
    status varchar(10) not null,
    over_date datetime ,
    create_time datetime ,
    update_time datetime ,
    primary key (id)
);

create table if not exists t_contract_limit_age (
    id bigint auto_increment,
    amt decimal(12, 2) not null default 0,
    min_age int,
    max_age int,
    status tinyint not null default 1,
    version int,
    primary key (id)
);
