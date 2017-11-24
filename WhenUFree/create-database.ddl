drop table user cascade constraints
create table user (id number(19,0) not null, email varchar2(255 char), firstname varchar2(255 char), lastname varchar2(255 char), password_hash varchar2(255 char), phone varchar2(255 char), primary key (id))
