create table orders (
  id bigint not null auto_increment,
  tag varchar(255),
  text varchar(2048) not null,
  user_id bigint,
  price varchar(255) not null,
  primary key (id)
) engine=InnoDB;

create table user_role (
  user_id bigint not null,
  roles varchar(255)
) engine=InnoDB;

create table usr (
  id bigint not null auto_increment,
  activation_code varchar(255),
  active bit not null,
  email varchar(255),
  password varchar(255) not null,
  username varchar(255) not null,
  usr_should varchar(2048),
  order_id bigint,
  primary key (id)
) engine=InnoDB;

