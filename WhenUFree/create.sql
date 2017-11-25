create table connection (connection_id number(19,0) not null, isadmin number(5,0), friendgroupid number(19,0), userid number(19,0), primary key (connection_id))
create table freetime (freetimeid number(19,0) not null, isdefault number(5,0), scheduled number(5,0), timeslotid number(19,0), userid number(19,0), primary key (freetimeid))
create table friendgroup (friendgroupid number(19,0) not null, name varchar2(255 char), primary key (friendgroupid))
create table friendslist (user_id number(19,0) not null, friend_id number(19,0) not null, primary key (user_id, friend_id))
create table groupfreetime (groupfreetimeid number(19,0) not null, numusers number(10,0), friendgroupid number(19,0), timeslotid number(19,0), primary key (groupfreetimeid))
create table message (messageid number(19,0) not null, pinned number(1,0), text varchar2(255 char), tstamp timestamp, friendgroupid number(19,0) not null, pollid number(19,0), primary key (messageid))
create table poll (pollid number(19,0) not null, description varchar2(255 char), primary key (pollid))
create table polloption (polloptionid number(19,0) not null, description varchar2(255 char), pollid number(19,0) not null, primary key (polloptionid))
create table timeslot (timeslotid number(19,0) not null, datetime varchar2(255 char), primary key (timeslotid))
create table usr (userid number(19,0) not null, email varchar2(255 char), firstname varchar2(255 char), lastname varchar2(255 char), passwordhash varchar2(255 char), phone varchar2(255 char), primary key (userid))
create table votes (polloptionid number(19,0) not null, userid number(19,0) not null, primary key (polloptionid, userid))
alter table connection add constraint FKbn9573dsbfcg17htn5jrh7hdg foreign key (friendgroupid) references friendgroup
alter table connection add constraint FK9u0fm189ofvlnrx6g09qjqt7d foreign key (userid) references usr
alter table freetime add constraint FKkm9o77htwelhicylsyp4dhd0u foreign key (timeslotid) references timeslot
alter table freetime add constraint FK68ciyys1q02ucepsak8q3b96s foreign key (userid) references usr
alter table friendslist add constraint FKi4sirsyocjr1etdy56dhm6k17 foreign key (friend_id) references usr
alter table friendslist add constraint FKsudjcwimnn325gd9bmgp3felu foreign key (user_id) references usr
alter table groupfreetime add constraint FKb70f8gpe01f3c8qrxl5pwpj9j foreign key (friendgroupid) references friendgroup
alter table groupfreetime add constraint FKcamkmmde4pb1a77ntiwqd9tt4 foreign key (timeslotid) references timeslot
alter table message add constraint FKl8f1olsempv6nqtr30trp4rth foreign key (friendgroupid) references friendgroup
alter table message add constraint FK8vwdd22k5v97xfx446cvkhpbg foreign key (pollid) references poll
alter table polloption add constraint FKqempmbl9dfwds5eyx1rksse6n foreign key (pollid) references poll
alter table votes add constraint FK6uxdackwrl6n042ddnm3l5wrk foreign key (userid) references usr
alter table votes add constraint FKciwyci8v6xils1uk1vr75lnm2 foreign key (polloptionid) references polloption
