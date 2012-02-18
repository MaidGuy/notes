SET foreign_key_checks = 0;
drop table if exists thread;
drop table if exists category;
drop table if exists note;
SET foreign_key_checks = 1;


create table category(id int primary key not null auto_increment,
name char(255) not null unique,
description text not null
)ENGINE=INNODB;


create table thread(id int primary key not null auto_increment,
name char(255) not null unique,
category_id int not null,
FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
)ENGINE=INNODB;



create table note(id int primary key not null auto_increment,
content text not null,
thread_id int not null,
created_at timestamp default NOW(),
FOREIGN KEY (thread_id) REFERENCES thread(id) ON DELETE CASCADE
)ENGINE=INNODB;
