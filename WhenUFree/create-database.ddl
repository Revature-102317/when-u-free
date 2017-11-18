drop table usr cascade constraints
create table usr (id number(19,0) not null, email varchar2(255 char), firstname varchar2(255 char), lastname varchar2(255 char), password_hash varchar2(255 char), phone varchar2(255 char), primary key (id))
