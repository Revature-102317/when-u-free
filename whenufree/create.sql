create sequence connectionsautoinc start with 1 increment by 1
create sequence freetimeautoinc start with 1 increment by 1
create sequence friendgroupautoinc start with 1 increment by 1
create sequence groupfreetimeautoinc start with 1 increment by 1
create sequence messageautoinc start with 1 increment by 1
create sequence pollautoinc start with 1 increment by 1
create sequence polloptionautoinc start with 1 increment by 1
create sequence userautoinc start with 1 increment by 1
create table connections (connection_id number(19,0) not null, isadmin number(1,0) not null, friendgroupid number(19,0) not null, status number(5,0) not null, userid number(19,0) not null, primary key (connection_id))
create table freetime (freetimeid number(19,0) not null, isdefault number(1,0) not null, scheduled number(1,0) not null, timeslotid number(19,0) not null, userid number(19,0) not null, primary key (freetimeid))
create table friendgroup (friendgroupid number(19,0) not null, name varchar2(255 char) not null, primary key (friendgroupid))
create table friendgroupstatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table friendslist (friend_userid number(19,0) not null, user_userid number(19,0) not null, statusid number(5,0) not null, primary key (friend_userid, user_userid))
create table friendsliststatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table groupfreetime (groupfreetimeid number(19,0) not null, numusers number(10,0) not null, friendgroupid number(19,0) not null, timeslotid number(19,0) not null, primary key (groupfreetimeid))
create table message (messageid number(19,0) not null, pinned number(1,0) not null, text varchar2(255 char) not null, tstamp timestamp not null, authorid number(19,0) not null, friendgroupid number(19,0) not null, pollid number(19,0), primary key (messageid))
create table poll (pollid number(19,0) not null, description varchar2(255 char), primary key (pollid))
create table polloption (polloptionid number(19,0) not null, description varchar2(255 char) not null, pollid number(19,0) not null, primary key (polloptionid))
create table timeslot (timeslotid number(19,0) not null, datetime varchar2(255 char) not null, primary key (timeslotid))
create table usr (userid number(19,0) not null, email varchar2(255 char) not null, firstname varchar2(255 char) not null, lastname varchar2(255 char) not null, passwordhash varchar2(255 char) not null, phone varchar2(255 char), primary key (userid))
create table votes (polloptionid number(19,0) not null, userid number(19,0) not null, primary key (polloptionid, userid))
create index IDX4m1ev9euglqji2626yj4ugjsm on friendgroup (name)
create index IDXike6jbm622c0rdctw27kon6aq on message (friendgroupid)
alter table usr add constraint UKg9l96r670qkidthshajdtxrqf unique (email)
alter table connections add constraint FKmpdnls9ilsm085e7hnktlvmwl foreign key (friendgroupid) references friendgroup
alter table connections add constraint FKi8t4v1nfut2yb4a8w1xstht7v foreign key (status) references friendgroupstatus
alter table connections add constraint FKcxanxngtnuil3o4u2r411fah2 foreign key (userid) references usr
alter table freetime add constraint FKkm9o77htwelhicylsyp4dhd0u foreign key (timeslotid) references timeslot
alter table freetime add constraint FK68ciyys1q02ucepsak8q3b96s foreign key (userid) references usr
alter table friendslist add constraint FKqisc5ald3t7gsa14syqikqtsw foreign key (friend_userid) references usr
alter table friendslist add constraint FK2khq0jmsw0lfrn1emh1mavs29 foreign key (statusid) references friendsliststatus
alter table friendslist add constraint FKqmt690sk2eplg4vscyeej8gsh foreign key (user_userid) references usr
alter table groupfreetime add constraint FKb70f8gpe01f3c8qrxl5pwpj9j foreign key (friendgroupid) references friendgroup
alter table groupfreetime add constraint FKcamkmmde4pb1a77ntiwqd9tt4 foreign key (timeslotid) references timeslot
alter table message add constraint FK8nxse8mttc4vm8q8dxq9xb0lc foreign key (authorid) references usr
alter table message add constraint FKl8f1olsempv6nqtr30trp4rth foreign key (friendgroupid) references friendgroup
alter table message add constraint FK8vwdd22k5v97xfx446cvkhpbg foreign key (pollid) references poll
alter table polloption add constraint FKqempmbl9dfwds5eyx1rksse6n foreign key (pollid) references poll
alter table votes add constraint FK6uxdackwrl6n042ddnm3l5wrk foreign key (userid) references usr
alter table votes add constraint FKciwyci8v6xils1uk1vr75lnm2 foreign key (polloptionid) references polloption
create sequence connectionsautoinc start with 1 increment by 1
create sequence freetimeautoinc start with 1 increment by 1
create sequence friendgroupautoinc start with 1 increment by 1
create sequence groupfreetimeautoinc start with 1 increment by 1
create sequence messageautoinc start with 1 increment by 1
create sequence pollautoinc start with 1 increment by 1
create sequence polloptionautoinc start with 1 increment by 1
create sequence userautoinc start with 1 increment by 1
create table connections (connection_id number(19,0) not null, isadmin number(1,0) not null, friendgroupid number(19,0) not null, status number(5,0) not null, userid number(19,0) not null, primary key (connection_id))
create table freetime (freetimeid number(19,0) not null, isdefault number(1,0) not null, scheduled number(1,0) not null, timeslotid number(19,0) not null, userid number(19,0) not null, primary key (freetimeid))
create table friendgroup (friendgroupid number(19,0) not null, name varchar2(255 char) not null, primary key (friendgroupid))
create table friendgroupstatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table friendslist (friend_userid number(19,0) not null, user_userid number(19,0) not null, statusid number(5,0) not null, primary key (friend_userid, user_userid))
create table friendsliststatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table groupfreetime (groupfreetimeid number(19,0) not null, numusers number(10,0) not null, friendgroupid number(19,0) not null, timeslotid number(19,0) not null, primary key (groupfreetimeid))
create table message (messageid number(19,0) not null, pinned number(1,0) not null, text varchar2(255 char) not null, tstamp timestamp not null, authorid number(19,0) not null, friendgroupid number(19,0) not null, pollid number(19,0), primary key (messageid))
create table poll (pollid number(19,0) not null, description varchar2(255 char), primary key (pollid))
create table polloption (polloptionid number(19,0) not null, description varchar2(255 char) not null, pollid number(19,0) not null, primary key (polloptionid))
create table timeslot (timeslotid number(19,0) not null, datetime varchar2(255 char) not null, primary key (timeslotid))
create table usr (userid number(19,0) not null, email varchar2(255 char) not null, firstname varchar2(255 char) not null, lastname varchar2(255 char) not null, passwordhash varchar2(255 char) not null, phone varchar2(255 char), primary key (userid))
create table votes (polloptionid number(19,0) not null, userid number(19,0) not null, primary key (polloptionid, userid))
create index IDX4m1ev9euglqji2626yj4ugjsm on friendgroup (name)
create index IDXike6jbm622c0rdctw27kon6aq on message (friendgroupid)
alter table usr add constraint UKg9l96r670qkidthshajdtxrqf unique (email)
alter table connections add constraint FKmpdnls9ilsm085e7hnktlvmwl foreign key (friendgroupid) references friendgroup
alter table connections add constraint FKi8t4v1nfut2yb4a8w1xstht7v foreign key (status) references friendgroupstatus
alter table connections add constraint FKcxanxngtnuil3o4u2r411fah2 foreign key (userid) references usr
alter table freetime add constraint FKkm9o77htwelhicylsyp4dhd0u foreign key (timeslotid) references timeslot
alter table freetime add constraint FK68ciyys1q02ucepsak8q3b96s foreign key (userid) references usr
alter table friendslist add constraint FKqisc5ald3t7gsa14syqikqtsw foreign key (friend_userid) references usr
alter table friendslist add constraint FK2khq0jmsw0lfrn1emh1mavs29 foreign key (statusid) references friendsliststatus
alter table friendslist add constraint FKqmt690sk2eplg4vscyeej8gsh foreign key (user_userid) references usr
alter table groupfreetime add constraint FKb70f8gpe01f3c8qrxl5pwpj9j foreign key (friendgroupid) references friendgroup
alter table groupfreetime add constraint FKcamkmmde4pb1a77ntiwqd9tt4 foreign key (timeslotid) references timeslot
alter table message add constraint FK8nxse8mttc4vm8q8dxq9xb0lc foreign key (authorid) references usr
alter table message add constraint FKl8f1olsempv6nqtr30trp4rth foreign key (friendgroupid) references friendgroup
alter table message add constraint FK8vwdd22k5v97xfx446cvkhpbg foreign key (pollid) references poll
alter table polloption add constraint FKqempmbl9dfwds5eyx1rksse6n foreign key (pollid) references poll
alter table votes add constraint FK6uxdackwrl6n042ddnm3l5wrk foreign key (userid) references usr
alter table votes add constraint FKciwyci8v6xils1uk1vr75lnm2 foreign key (polloptionid) references polloption
create sequence connectionsautoinc start with 1 increment by 1
create sequence freetimeautoinc start with 1 increment by 1
create sequence friendgroupautoinc start with 1 increment by 1
create sequence groupfreetimeautoinc start with 1 increment by 1
create sequence messageautoinc start with 1 increment by 1
create sequence pollautoinc start with 1 increment by 1
create sequence polloptionautoinc start with 1 increment by 1
create sequence userautoinc start with 1 increment by 1
create table connections (connection_id number(19,0) not null, isadmin number(1,0) not null, friendgroupid number(19,0) not null, status number(5,0) not null, userid number(19,0) not null, primary key (connection_id))
create table freetime (freetimeid number(19,0) not null, isdefault number(1,0) not null, scheduled number(1,0) not null, timeslotid number(19,0) not null, userid number(19,0) not null, primary key (freetimeid))
create table friendgroup (friendgroupid number(19,0) not null, name varchar2(255 char) not null, primary key (friendgroupid))
create table friendgroupstatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table friendslist (friend_userid number(19,0) not null, user_userid number(19,0) not null, statusid number(5,0) not null, primary key (friend_userid, user_userid))
create table friendsliststatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table groupfreetime (groupfreetimeid number(19,0) not null, numusers number(10,0) not null, friendgroupid number(19,0) not null, timeslotid number(19,0) not null, primary key (groupfreetimeid))
create table message (messageid number(19,0) not null, pinned number(1,0) not null, text varchar2(255 char) not null, tstamp timestamp not null, authorid number(19,0) not null, friendgroupid number(19,0) not null, pollid number(19,0), primary key (messageid))
create table poll (pollid number(19,0) not null, description varchar2(255 char), primary key (pollid))
create table polloption (polloptionid number(19,0) not null, description varchar2(255 char) not null, pollid number(19,0) not null, primary key (polloptionid))
create table timeslot (timeslotid number(19,0) not null, datetime varchar2(255 char) not null, primary key (timeslotid))
create table usr (userid number(19,0) not null, email varchar2(255 char) not null, firstname varchar2(255 char) not null, lastname varchar2(255 char) not null, passwordhash varchar2(255 char) not null, phone varchar2(255 char), primary key (userid))
create table votes (polloptionid number(19,0) not null, userid number(19,0) not null, primary key (polloptionid, userid))
create index IDX4m1ev9euglqji2626yj4ugjsm on friendgroup (name)
create index IDXike6jbm622c0rdctw27kon6aq on message (friendgroupid)
alter table usr add constraint UKg9l96r670qkidthshajdtxrqf unique (email)
alter table connections add constraint FKmpdnls9ilsm085e7hnktlvmwl foreign key (friendgroupid) references friendgroup
alter table connections add constraint FKi8t4v1nfut2yb4a8w1xstht7v foreign key (status) references friendgroupstatus
alter table connections add constraint FKcxanxngtnuil3o4u2r411fah2 foreign key (userid) references usr
alter table freetime add constraint FKkm9o77htwelhicylsyp4dhd0u foreign key (timeslotid) references timeslot
alter table freetime add constraint FK68ciyys1q02ucepsak8q3b96s foreign key (userid) references usr
alter table friendslist add constraint FKqisc5ald3t7gsa14syqikqtsw foreign key (friend_userid) references usr
alter table friendslist add constraint FK2khq0jmsw0lfrn1emh1mavs29 foreign key (statusid) references friendsliststatus
alter table friendslist add constraint FKqmt690sk2eplg4vscyeej8gsh foreign key (user_userid) references usr
alter table groupfreetime add constraint FKb70f8gpe01f3c8qrxl5pwpj9j foreign key (friendgroupid) references friendgroup
alter table groupfreetime add constraint FKcamkmmde4pb1a77ntiwqd9tt4 foreign key (timeslotid) references timeslot
alter table message add constraint FK8nxse8mttc4vm8q8dxq9xb0lc foreign key (authorid) references usr
alter table message add constraint FKl8f1olsempv6nqtr30trp4rth foreign key (friendgroupid) references friendgroup
alter table message add constraint FK8vwdd22k5v97xfx446cvkhpbg foreign key (pollid) references poll
alter table polloption add constraint FKqempmbl9dfwds5eyx1rksse6n foreign key (pollid) references poll
alter table votes add constraint FK6uxdackwrl6n042ddnm3l5wrk foreign key (userid) references usr
alter table votes add constraint FKciwyci8v6xils1uk1vr75lnm2 foreign key (polloptionid) references polloption
create sequence connectionsautoinc start with 1 increment by 1
create sequence freetimeautoinc start with 1 increment by 1
create sequence friendgroupautoinc start with 1 increment by 1
create sequence groupfreetimeautoinc start with 1 increment by 1
create sequence messageautoinc start with 1 increment by 1
create sequence pollautoinc start with 1 increment by 1
create sequence polloptionautoinc start with 1 increment by 1
create sequence userautoinc start with 1 increment by 1
create table connections (connection_id number(19,0) not null, isadmin number(1,0) not null, friendgroupid number(19,0) not null, status number(5,0) not null, userid number(19,0) not null, primary key (connection_id))
create table freetime (freetimeid number(19,0) not null, isdefault number(1,0) not null, scheduled number(1,0) not null, timeslotid number(19,0) not null, userid number(19,0) not null, primary key (freetimeid))
create table friendgroup (friendgroupid number(19,0) not null, name varchar2(255 char) not null, primary key (friendgroupid))
create table friendgroupstatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table friendslist (friend_userid number(19,0) not null, user_userid number(19,0) not null, statusid number(5,0) not null, primary key (friend_userid, user_userid))
create table friendsliststatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table groupfreetime (groupfreetimeid number(19,0) not null, numusers number(10,0) not null, friendgroupid number(19,0) not null, timeslotid number(19,0) not null, primary key (groupfreetimeid))
create table message (messageid number(19,0) not null, pinned number(1,0) not null, text varchar2(255 char) not null, tstamp timestamp not null, authorid number(19,0) not null, friendgroupid number(19,0) not null, pollid number(19,0), primary key (messageid))
create table poll (pollid number(19,0) not null, description varchar2(255 char), primary key (pollid))
create table polloption (polloptionid number(19,0) not null, description varchar2(255 char) not null, pollid number(19,0) not null, primary key (polloptionid))
create table timeslot (timeslotid number(19,0) not null, datetime varchar2(255 char) not null, primary key (timeslotid))
create table usr (userid number(19,0) not null, email varchar2(255 char) not null, firstname varchar2(255 char) not null, lastname varchar2(255 char) not null, passwordhash varchar2(255 char) not null, phone varchar2(255 char), primary key (userid))
create table votes (polloptionid number(19,0) not null, userid number(19,0) not null, primary key (polloptionid, userid))
create index IDX4m1ev9euglqji2626yj4ugjsm on friendgroup (name)
create index IDXike6jbm622c0rdctw27kon6aq on message (friendgroupid)
alter table usr add constraint UKg9l96r670qkidthshajdtxrqf unique (email)
alter table connections add constraint FKmpdnls9ilsm085e7hnktlvmwl foreign key (friendgroupid) references friendgroup
alter table connections add constraint FKi8t4v1nfut2yb4a8w1xstht7v foreign key (status) references friendgroupstatus
alter table connections add constraint FKcxanxngtnuil3o4u2r411fah2 foreign key (userid) references usr
alter table freetime add constraint FKkm9o77htwelhicylsyp4dhd0u foreign key (timeslotid) references timeslot
alter table freetime add constraint FK68ciyys1q02ucepsak8q3b96s foreign key (userid) references usr
alter table friendslist add constraint FKqisc5ald3t7gsa14syqikqtsw foreign key (friend_userid) references usr
alter table friendslist add constraint FK2khq0jmsw0lfrn1emh1mavs29 foreign key (statusid) references friendsliststatus
alter table friendslist add constraint FKqmt690sk2eplg4vscyeej8gsh foreign key (user_userid) references usr
alter table groupfreetime add constraint FKb70f8gpe01f3c8qrxl5pwpj9j foreign key (friendgroupid) references friendgroup
alter table groupfreetime add constraint FKcamkmmde4pb1a77ntiwqd9tt4 foreign key (timeslotid) references timeslot
alter table message add constraint FK8nxse8mttc4vm8q8dxq9xb0lc foreign key (authorid) references usr
alter table message add constraint FKl8f1olsempv6nqtr30trp4rth foreign key (friendgroupid) references friendgroup
alter table message add constraint FK8vwdd22k5v97xfx446cvkhpbg foreign key (pollid) references poll
alter table polloption add constraint FKqempmbl9dfwds5eyx1rksse6n foreign key (pollid) references poll
alter table votes add constraint FK6uxdackwrl6n042ddnm3l5wrk foreign key (userid) references usr
alter table votes add constraint FKciwyci8v6xils1uk1vr75lnm2 foreign key (polloptionid) references polloption
create sequence connectionsautoinc start with 1 increment by 1
create sequence freetimeautoinc start with 1 increment by 1
create sequence friendgroupautoinc start with 1 increment by 1
create sequence groupfreetimeautoinc start with 1 increment by 1
create sequence messageautoinc start with 1 increment by 1
create sequence pollautoinc start with 1 increment by 1
create sequence polloptionautoinc start with 1 increment by 1
create sequence userautoinc start with 1 increment by 1
create table connections (connection_id number(19,0) not null, isadmin number(1,0) not null, friendgroupid number(19,0) not null, status number(5,0) not null, userid number(19,0) not null, primary key (connection_id))
create table freetime (freetimeid number(19,0) not null, isdefault number(1,0) not null, scheduled number(1,0) not null, timeslotid number(19,0) not null, userid number(19,0) not null, primary key (freetimeid))
create table friendgroup (friendgroupid number(19,0) not null, name varchar2(255 char) not null, primary key (friendgroupid))
create table friendgroupstatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table friendslist (friend_userid number(19,0) not null, user_userid number(19,0) not null, statusid number(5,0) not null, primary key (friend_userid, user_userid))
create table friendsliststatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table groupfreetime (groupfreetimeid number(19,0) not null, numusers number(10,0) not null, friendgroupid number(19,0) not null, timeslotid number(19,0) not null, primary key (groupfreetimeid))
create table message (messageid number(19,0) not null, pinned number(1,0) not null, text varchar2(255 char) not null, tstamp timestamp not null, authorid number(19,0) not null, friendgroupid number(19,0) not null, pollid number(19,0), primary key (messageid))
create table poll (pollid number(19,0) not null, description varchar2(255 char), primary key (pollid))
create table polloption (polloptionid number(19,0) not null, description varchar2(255 char) not null, pollid number(19,0) not null, primary key (polloptionid))
create table timeslot (timeslotid number(19,0) not null, datetime varchar2(255 char) not null, primary key (timeslotid))
create table usr (userid number(19,0) not null, email varchar2(255 char) not null, firstname varchar2(255 char) not null, lastname varchar2(255 char) not null, passwordhash varchar2(255 char) not null, phone varchar2(255 char), primary key (userid))
create table votes (polloptionid number(19,0) not null, userid number(19,0) not null, primary key (polloptionid, userid))
create index IDX4m1ev9euglqji2626yj4ugjsm on friendgroup (name)
create index IDXike6jbm622c0rdctw27kon6aq on message (friendgroupid)
alter table usr add constraint UKg9l96r670qkidthshajdtxrqf unique (email)
alter table connections add constraint FKmpdnls9ilsm085e7hnktlvmwl foreign key (friendgroupid) references friendgroup
alter table connections add constraint FKi8t4v1nfut2yb4a8w1xstht7v foreign key (status) references friendgroupstatus
alter table connections add constraint FKcxanxngtnuil3o4u2r411fah2 foreign key (userid) references usr
alter table freetime add constraint FKkm9o77htwelhicylsyp4dhd0u foreign key (timeslotid) references timeslot
alter table freetime add constraint FK68ciyys1q02ucepsak8q3b96s foreign key (userid) references usr
alter table friendslist add constraint FKqisc5ald3t7gsa14syqikqtsw foreign key (friend_userid) references usr
alter table friendslist add constraint FK2khq0jmsw0lfrn1emh1mavs29 foreign key (statusid) references friendsliststatus
alter table friendslist add constraint FKqmt690sk2eplg4vscyeej8gsh foreign key (user_userid) references usr
alter table groupfreetime add constraint FKb70f8gpe01f3c8qrxl5pwpj9j foreign key (friendgroupid) references friendgroup
alter table groupfreetime add constraint FKcamkmmde4pb1a77ntiwqd9tt4 foreign key (timeslotid) references timeslot
alter table message add constraint FK8nxse8mttc4vm8q8dxq9xb0lc foreign key (authorid) references usr
alter table message add constraint FKl8f1olsempv6nqtr30trp4rth foreign key (friendgroupid) references friendgroup
alter table message add constraint FK8vwdd22k5v97xfx446cvkhpbg foreign key (pollid) references poll
alter table polloption add constraint FKqempmbl9dfwds5eyx1rksse6n foreign key (pollid) references poll
alter table votes add constraint FK6uxdackwrl6n042ddnm3l5wrk foreign key (userid) references usr
alter table votes add constraint FKciwyci8v6xils1uk1vr75lnm2 foreign key (polloptionid) references polloption
create sequence connectionsautoinc start with 1 increment by 1
create sequence freetimeautoinc start with 1 increment by 1
create sequence friendgroupautoinc start with 1 increment by 1
create sequence groupfreetimeautoinc start with 1 increment by 1
create sequence messageautoinc start with 1 increment by 1
create sequence pollautoinc start with 1 increment by 1
create sequence polloptionautoinc start with 1 increment by 1
create sequence userautoinc start with 1 increment by 1
create table connections (connection_id number(19,0) not null, isadmin number(1,0) not null, friendgroupid number(19,0) not null, status number(5,0) not null, userid number(19,0) not null, primary key (connection_id))
create table freetime (freetimeid number(19,0) not null, isdefault number(1,0) not null, scheduled number(1,0) not null, timeslotid number(19,0) not null, userid number(19,0) not null, primary key (freetimeid))
create table friendgroup (friendgroupid number(19,0) not null, name varchar2(255 char) not null, primary key (friendgroupid))
create table friendgroupstatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table friendslist (friend_userid number(19,0) not null, user_userid number(19,0) not null, statusid number(5,0) not null, primary key (friend_userid, user_userid))
create table friendsliststatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table groupfreetime (groupfreetimeid number(19,0) not null, numusers number(10,0) not null, friendgroupid number(19,0) not null, timeslotid number(19,0) not null, primary key (groupfreetimeid))
create table message (messageid number(19,0) not null, pinned number(1,0) not null, text varchar2(255 char) not null, tstamp timestamp not null, authorid number(19,0) not null, friendgroupid number(19,0) not null, pollid number(19,0), primary key (messageid))
create table poll (pollid number(19,0) not null, description varchar2(255 char), primary key (pollid))
create table polloption (polloptionid number(19,0) not null, description varchar2(255 char) not null, pollid number(19,0) not null, primary key (polloptionid))
create table timeslot (timeslotid number(19,0) not null, datetime varchar2(255 char) not null, primary key (timeslotid))
create table usr (userid number(19,0) not null, email varchar2(255 char) not null, firstname varchar2(255 char) not null, lastname varchar2(255 char) not null, passwordhash varchar2(255 char) not null, phone varchar2(255 char), primary key (userid))
create table votes (polloptionid number(19,0) not null, userid number(19,0) not null, primary key (polloptionid, userid))
create index IDX4m1ev9euglqji2626yj4ugjsm on friendgroup (name)
create index IDXike6jbm622c0rdctw27kon6aq on message (friendgroupid)
alter table usr add constraint UKg9l96r670qkidthshajdtxrqf unique (email)
alter table connections add constraint FKmpdnls9ilsm085e7hnktlvmwl foreign key (friendgroupid) references friendgroup
alter table connections add constraint FKi8t4v1nfut2yb4a8w1xstht7v foreign key (status) references friendgroupstatus
alter table connections add constraint FKcxanxngtnuil3o4u2r411fah2 foreign key (userid) references usr
alter table freetime add constraint FKkm9o77htwelhicylsyp4dhd0u foreign key (timeslotid) references timeslot
alter table freetime add constraint FK68ciyys1q02ucepsak8q3b96s foreign key (userid) references usr
alter table friendslist add constraint FKqisc5ald3t7gsa14syqikqtsw foreign key (friend_userid) references usr
alter table friendslist add constraint FK2khq0jmsw0lfrn1emh1mavs29 foreign key (statusid) references friendsliststatus
alter table friendslist add constraint FKqmt690sk2eplg4vscyeej8gsh foreign key (user_userid) references usr
alter table groupfreetime add constraint FKb70f8gpe01f3c8qrxl5pwpj9j foreign key (friendgroupid) references friendgroup
alter table groupfreetime add constraint FKcamkmmde4pb1a77ntiwqd9tt4 foreign key (timeslotid) references timeslot
alter table message add constraint FK8nxse8mttc4vm8q8dxq9xb0lc foreign key (authorid) references usr
alter table message add constraint FKl8f1olsempv6nqtr30trp4rth foreign key (friendgroupid) references friendgroup
alter table message add constraint FK8vwdd22k5v97xfx446cvkhpbg foreign key (pollid) references poll
alter table polloption add constraint FKqempmbl9dfwds5eyx1rksse6n foreign key (pollid) references poll
alter table votes add constraint FK6uxdackwrl6n042ddnm3l5wrk foreign key (userid) references usr
alter table votes add constraint FKciwyci8v6xils1uk1vr75lnm2 foreign key (polloptionid) references polloption
create sequence connectionsautoinc start with 1 increment by 1
create sequence freetimeautoinc start with 1 increment by 1
create sequence friendgroupautoinc start with 1 increment by 1
create sequence groupfreetimeautoinc start with 1 increment by 1
create sequence messageautoinc start with 1 increment by 1
create sequence pollautoinc start with 1 increment by 1
create sequence polloptionautoinc start with 1 increment by 1
create sequence userautoinc start with 1 increment by 1
create table connections (connection_id number(19,0) not null, isadmin number(1,0) not null, friendgroupid number(19,0) not null, status number(5,0) not null, userid number(19,0) not null, primary key (connection_id))
create table freetime (freetimeid number(19,0) not null, isdefault number(1,0) not null, scheduled number(1,0) not null, timeslotid number(19,0) not null, userid number(19,0) not null, primary key (freetimeid))
create table friendgroup (friendgroupid number(19,0) not null, name varchar2(255 char) not null, primary key (friendgroupid))
create table friendgroupstatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table friendslist (friend_userid number(19,0) not null, user_userid number(19,0) not null, statusid number(5,0) not null, primary key (friend_userid, user_userid))
create table friendsliststatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table groupfreetime (groupfreetimeid number(19,0) not null, numusers number(10,0) not null, friendgroupid number(19,0) not null, timeslotid number(19,0) not null, primary key (groupfreetimeid))
create table message (messageid number(19,0) not null, pinned number(1,0) not null, text varchar2(255 char) not null, tstamp timestamp not null, authorid number(19,0) not null, friendgroupid number(19,0) not null, pollid number(19,0), primary key (messageid))
create table poll (pollid number(19,0) not null, description varchar2(255 char), primary key (pollid))
create table polloption (polloptionid number(19,0) not null, description varchar2(255 char) not null, pollid number(19,0) not null, primary key (polloptionid))
create table timeslot (timeslotid number(19,0) not null, datetime varchar2(255 char) not null, primary key (timeslotid))
create table usr (userid number(19,0) not null, email varchar2(255 char) not null, firstname varchar2(255 char) not null, lastname varchar2(255 char) not null, passwordhash varchar2(255 char) not null, phone varchar2(255 char), primary key (userid))
create table votes (polloptionid number(19,0) not null, userid number(19,0) not null, primary key (polloptionid, userid))
create index IDX4m1ev9euglqji2626yj4ugjsm on friendgroup (name)
create index IDXike6jbm622c0rdctw27kon6aq on message (friendgroupid)
alter table usr add constraint UKg9l96r670qkidthshajdtxrqf unique (email)
alter table connections add constraint FKmpdnls9ilsm085e7hnktlvmwl foreign key (friendgroupid) references friendgroup
alter table connections add constraint FKi8t4v1nfut2yb4a8w1xstht7v foreign key (status) references friendgroupstatus
alter table connections add constraint FKcxanxngtnuil3o4u2r411fah2 foreign key (userid) references usr
alter table freetime add constraint FKkm9o77htwelhicylsyp4dhd0u foreign key (timeslotid) references timeslot
alter table freetime add constraint FK68ciyys1q02ucepsak8q3b96s foreign key (userid) references usr
alter table friendslist add constraint FKqisc5ald3t7gsa14syqikqtsw foreign key (friend_userid) references usr
alter table friendslist add constraint FK2khq0jmsw0lfrn1emh1mavs29 foreign key (statusid) references friendsliststatus
alter table friendslist add constraint FKqmt690sk2eplg4vscyeej8gsh foreign key (user_userid) references usr
alter table groupfreetime add constraint FKb70f8gpe01f3c8qrxl5pwpj9j foreign key (friendgroupid) references friendgroup
alter table groupfreetime add constraint FKcamkmmde4pb1a77ntiwqd9tt4 foreign key (timeslotid) references timeslot
alter table message add constraint FK8nxse8mttc4vm8q8dxq9xb0lc foreign key (authorid) references usr
alter table message add constraint FKl8f1olsempv6nqtr30trp4rth foreign key (friendgroupid) references friendgroup
alter table message add constraint FK8vwdd22k5v97xfx446cvkhpbg foreign key (pollid) references poll
alter table polloption add constraint FKqempmbl9dfwds5eyx1rksse6n foreign key (pollid) references poll
alter table votes add constraint FK6uxdackwrl6n042ddnm3l5wrk foreign key (userid) references usr
alter table votes add constraint FKciwyci8v6xils1uk1vr75lnm2 foreign key (polloptionid) references polloption
create sequence connectionsautoinc start with 1 increment by 1
create sequence freetimeautoinc start with 1 increment by 1
create sequence friendgroupautoinc start with 1 increment by 1
create sequence groupfreetimeautoinc start with 1 increment by 1
create sequence messageautoinc start with 1 increment by 1
create sequence pollautoinc start with 1 increment by 1
create sequence polloptionautoinc start with 1 increment by 1
create sequence userautoinc start with 1 increment by 1
create table connections (connection_id number(19,0) not null, isadmin number(1,0) not null, friendgroupid number(19,0) not null, status number(5,0) not null, userid number(19,0) not null, primary key (connection_id))
create table freetime (freetimeid number(19,0) not null, isdefault number(1,0) not null, scheduled number(1,0) not null, timeslotid number(19,0) not null, userid number(19,0) not null, primary key (freetimeid))
create table friendgroup (friendgroupid number(19,0) not null, name varchar2(255 char) not null, primary key (friendgroupid))
create table friendgroupstatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table friendslist (friend_userid number(19,0) not null, user_userid number(19,0) not null, statusid number(5,0) not null, primary key (friend_userid, user_userid))
create table friendsliststatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table groupfreetime (groupfreetimeid number(19,0) not null, numusers number(10,0) not null, friendgroupid number(19,0) not null, timeslotid number(19,0) not null, primary key (groupfreetimeid))
create table message (messageid number(19,0) not null, pinned number(1,0) not null, text varchar2(255 char) not null, tstamp timestamp not null, authorid number(19,0) not null, friendgroupid number(19,0) not null, pollid number(19,0), primary key (messageid))
create table poll (pollid number(19,0) not null, description varchar2(255 char), primary key (pollid))
create table polloption (polloptionid number(19,0) not null, description varchar2(255 char) not null, pollid number(19,0) not null, primary key (polloptionid))
create table timeslot (timeslotid number(19,0) not null, datetime varchar2(255 char) not null, primary key (timeslotid))
create table usr (userid number(19,0) not null, email varchar2(255 char) not null, firstname varchar2(255 char) not null, lastname varchar2(255 char) not null, passwordhash varchar2(255 char) not null, phone varchar2(255 char), primary key (userid))
create table votes (polloptionid number(19,0) not null, userid number(19,0) not null, primary key (polloptionid, userid))
create index IDX4m1ev9euglqji2626yj4ugjsm on friendgroup (name)
create index IDXike6jbm622c0rdctw27kon6aq on message (friendgroupid)
alter table usr add constraint UKg9l96r670qkidthshajdtxrqf unique (email)
alter table connections add constraint FKmpdnls9ilsm085e7hnktlvmwl foreign key (friendgroupid) references friendgroup
alter table connections add constraint FKi8t4v1nfut2yb4a8w1xstht7v foreign key (status) references friendgroupstatus
alter table connections add constraint FKcxanxngtnuil3o4u2r411fah2 foreign key (userid) references usr
alter table freetime add constraint FKkm9o77htwelhicylsyp4dhd0u foreign key (timeslotid) references timeslot
alter table freetime add constraint FK68ciyys1q02ucepsak8q3b96s foreign key (userid) references usr
alter table friendslist add constraint FKqisc5ald3t7gsa14syqikqtsw foreign key (friend_userid) references usr
alter table friendslist add constraint FK2khq0jmsw0lfrn1emh1mavs29 foreign key (statusid) references friendsliststatus
alter table friendslist add constraint FKqmt690sk2eplg4vscyeej8gsh foreign key (user_userid) references usr
alter table groupfreetime add constraint FKb70f8gpe01f3c8qrxl5pwpj9j foreign key (friendgroupid) references friendgroup
alter table groupfreetime add constraint FKcamkmmde4pb1a77ntiwqd9tt4 foreign key (timeslotid) references timeslot
alter table message add constraint FK8nxse8mttc4vm8q8dxq9xb0lc foreign key (authorid) references usr
alter table message add constraint FKl8f1olsempv6nqtr30trp4rth foreign key (friendgroupid) references friendgroup
alter table message add constraint FK8vwdd22k5v97xfx446cvkhpbg foreign key (pollid) references poll
alter table polloption add constraint FKqempmbl9dfwds5eyx1rksse6n foreign key (pollid) references poll
alter table votes add constraint FK6uxdackwrl6n042ddnm3l5wrk foreign key (userid) references usr
alter table votes add constraint FKciwyci8v6xils1uk1vr75lnm2 foreign key (polloptionid) references polloption
create sequence connectionsautoinc start with 1 increment by 1
create sequence freetimeautoinc start with 1 increment by 1
create sequence friendgroupautoinc start with 1 increment by 1
create sequence groupfreetimeautoinc start with 1 increment by 1
create sequence messageautoinc start with 1 increment by 1
create sequence pollautoinc start with 1 increment by 1
create sequence polloptionautoinc start with 1 increment by 1
create sequence userautoinc start with 1 increment by 1
create table connections (connection_id number(19,0) not null, isadmin number(1,0) not null, friendgroupid number(19,0) not null, status number(5,0) not null, userid number(19,0) not null, primary key (connection_id))
create table freetime (freetimeid number(19,0) not null, isdefault number(1,0) not null, scheduled number(1,0) not null, timeslotid number(19,0) not null, userid number(19,0) not null, primary key (freetimeid))
create table friendgroup (friendgroupid number(19,0) not null, name varchar2(255 char) not null, primary key (friendgroupid))
create table friendgroupstatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table friendslist (friend_userid number(19,0) not null, user_userid number(19,0) not null, statusid number(5,0) not null, primary key (friend_userid, user_userid))
create table friendsliststatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table groupfreetime (groupfreetimeid number(19,0) not null, numusers number(10,0) not null, friendgroupid number(19,0) not null, timeslotid number(19,0) not null, primary key (groupfreetimeid))
create table message (messageid number(19,0) not null, pinned number(1,0) not null, text varchar2(255 char) not null, tstamp timestamp not null, authorid number(19,0) not null, friendgroupid number(19,0) not null, pollid number(19,0), primary key (messageid))
create table poll (pollid number(19,0) not null, description varchar2(255 char), primary key (pollid))
create table polloption (polloptionid number(19,0) not null, description varchar2(255 char) not null, pollid number(19,0) not null, primary key (polloptionid))
create table timeslot (timeslotid number(19,0) not null, datetime varchar2(255 char) not null, primary key (timeslotid))
create table usr (userid number(19,0) not null, email varchar2(255 char) not null, firstname varchar2(255 char) not null, lastname varchar2(255 char) not null, passwordhash varchar2(255 char) not null, phone varchar2(255 char), primary key (userid))
create table votes (polloptionid number(19,0) not null, userid number(19,0) not null, primary key (polloptionid, userid))
create index IDX4m1ev9euglqji2626yj4ugjsm on friendgroup (name)
create index IDXike6jbm622c0rdctw27kon6aq on message (friendgroupid)
alter table usr add constraint UKg9l96r670qkidthshajdtxrqf unique (email)
alter table connections add constraint FKmpdnls9ilsm085e7hnktlvmwl foreign key (friendgroupid) references friendgroup
alter table connections add constraint FKi8t4v1nfut2yb4a8w1xstht7v foreign key (status) references friendgroupstatus
alter table connections add constraint FKcxanxngtnuil3o4u2r411fah2 foreign key (userid) references usr
alter table freetime add constraint FKkm9o77htwelhicylsyp4dhd0u foreign key (timeslotid) references timeslot
alter table freetime add constraint FK68ciyys1q02ucepsak8q3b96s foreign key (userid) references usr
alter table friendslist add constraint FKqisc5ald3t7gsa14syqikqtsw foreign key (friend_userid) references usr
alter table friendslist add constraint FK2khq0jmsw0lfrn1emh1mavs29 foreign key (statusid) references friendsliststatus
alter table friendslist add constraint FKqmt690sk2eplg4vscyeej8gsh foreign key (user_userid) references usr
alter table groupfreetime add constraint FKb70f8gpe01f3c8qrxl5pwpj9j foreign key (friendgroupid) references friendgroup
alter table groupfreetime add constraint FKcamkmmde4pb1a77ntiwqd9tt4 foreign key (timeslotid) references timeslot
alter table message add constraint FK8nxse8mttc4vm8q8dxq9xb0lc foreign key (authorid) references usr
alter table message add constraint FKl8f1olsempv6nqtr30trp4rth foreign key (friendgroupid) references friendgroup
alter table message add constraint FK8vwdd22k5v97xfx446cvkhpbg foreign key (pollid) references poll
alter table polloption add constraint FKqempmbl9dfwds5eyx1rksse6n foreign key (pollid) references poll
alter table votes add constraint FK6uxdackwrl6n042ddnm3l5wrk foreign key (userid) references usr
alter table votes add constraint FKciwyci8v6xils1uk1vr75lnm2 foreign key (polloptionid) references polloption
create sequence connectionsautoinc start with 1 increment by 1
create sequence freetimeautoinc start with 1 increment by 1
create sequence friendgroupautoinc start with 1 increment by 1
create sequence groupfreetimeautoinc start with 1 increment by 1
create sequence messageautoinc start with 1 increment by 1
create sequence pollautoinc start with 1 increment by 1
create sequence polloptionautoinc start with 1 increment by 1
create sequence userautoinc start with 1 increment by 1
create table connections (connection_id number(19,0) not null, isadmin number(1,0) not null, friendgroupid number(19,0) not null, status number(5,0) not null, userid number(19,0) not null, primary key (connection_id))
create table freetime (freetimeid number(19,0) not null, isdefault number(1,0) not null, scheduled number(1,0) not null, timeslotid number(19,0) not null, userid number(19,0) not null, primary key (freetimeid))
create table friendgroup (friendgroupid number(19,0) not null, name varchar2(255 char) not null, primary key (friendgroupid))
create table friendgroupstatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table friendslist (friend_userid number(19,0) not null, user_userid number(19,0) not null, statusid number(5,0) not null, primary key (friend_userid, user_userid))
create table friendsliststatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table groupfreetime (groupfreetimeid number(19,0) not null, numusers number(10,0) not null, friendgroupid number(19,0) not null, timeslotid number(19,0) not null, primary key (groupfreetimeid))
create table message (messageid number(19,0) not null, pinned number(1,0) not null, text varchar2(255 char) not null, tstamp timestamp not null, authorid number(19,0) not null, friendgroupid number(19,0) not null, pollid number(19,0), primary key (messageid))
create table poll (pollid number(19,0) not null, description varchar2(255 char), primary key (pollid))
create table polloption (polloptionid number(19,0) not null, description varchar2(255 char) not null, pollid number(19,0) not null, primary key (polloptionid))
create table timeslot (timeslotid number(19,0) not null, datetime varchar2(255 char) not null, primary key (timeslotid))
create table usr (userid number(19,0) not null, email varchar2(255 char) not null, firstname varchar2(255 char) not null, lastname varchar2(255 char) not null, passwordhash varchar2(255 char) not null, phone varchar2(255 char), primary key (userid))
create table votes (polloptionid number(19,0) not null, userid number(19,0) not null, primary key (polloptionid, userid))
create index IDX4m1ev9euglqji2626yj4ugjsm on friendgroup (name)
create index IDXike6jbm622c0rdctw27kon6aq on message (friendgroupid)
alter table usr add constraint UKg9l96r670qkidthshajdtxrqf unique (email)
alter table connections add constraint FKmpdnls9ilsm085e7hnktlvmwl foreign key (friendgroupid) references friendgroup
alter table connections add constraint FKi8t4v1nfut2yb4a8w1xstht7v foreign key (status) references friendgroupstatus
alter table connections add constraint FKcxanxngtnuil3o4u2r411fah2 foreign key (userid) references usr
alter table freetime add constraint FKkm9o77htwelhicylsyp4dhd0u foreign key (timeslotid) references timeslot
alter table freetime add constraint FK68ciyys1q02ucepsak8q3b96s foreign key (userid) references usr
alter table friendslist add constraint FKqisc5ald3t7gsa14syqikqtsw foreign key (friend_userid) references usr
alter table friendslist add constraint FK2khq0jmsw0lfrn1emh1mavs29 foreign key (statusid) references friendsliststatus
alter table friendslist add constraint FKqmt690sk2eplg4vscyeej8gsh foreign key (user_userid) references usr
alter table groupfreetime add constraint FKb70f8gpe01f3c8qrxl5pwpj9j foreign key (friendgroupid) references friendgroup
alter table groupfreetime add constraint FKcamkmmde4pb1a77ntiwqd9tt4 foreign key (timeslotid) references timeslot
alter table message add constraint FK8nxse8mttc4vm8q8dxq9xb0lc foreign key (authorid) references usr
alter table message add constraint FKl8f1olsempv6nqtr30trp4rth foreign key (friendgroupid) references friendgroup
alter table message add constraint FK8vwdd22k5v97xfx446cvkhpbg foreign key (pollid) references poll
alter table polloption add constraint FKqempmbl9dfwds5eyx1rksse6n foreign key (pollid) references poll
alter table votes add constraint FK6uxdackwrl6n042ddnm3l5wrk foreign key (userid) references usr
alter table votes add constraint FKciwyci8v6xils1uk1vr75lnm2 foreign key (polloptionid) references polloption
create sequence connectionsautoinc start with 1 increment by 1
create sequence freetimeautoinc start with 1 increment by 1
create sequence friendgroupautoinc start with 1 increment by 1
create sequence groupfreetimeautoinc start with 1 increment by 1
create sequence messageautoinc start with 1 increment by 1
create sequence pollautoinc start with 1 increment by 1
create sequence polloptionautoinc start with 1 increment by 1
create sequence userautoinc start with 1 increment by 1
create table connections (connection_id number(19,0) not null, isadmin number(1,0) not null, friendgroupid number(19,0) not null, status number(5,0) not null, userid number(19,0) not null, primary key (connection_id))
create table freetime (freetimeid number(19,0) not null, isdefault number(1,0) not null, scheduled number(1,0) not null, timeslotid number(19,0) not null, userid number(19,0) not null, primary key (freetimeid))
create table friendgroup (friendgroupid number(19,0) not null, name varchar2(255 char) not null, primary key (friendgroupid))
create table friendgroupstatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table friendslist (friend_userid number(19,0) not null, user_userid number(19,0) not null, statusid number(5,0) not null, primary key (friend_userid, user_userid))
create table friendsliststatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table groupfreetime (groupfreetimeid number(19,0) not null, numusers number(10,0) not null, friendgroupid number(19,0) not null, timeslotid number(19,0) not null, primary key (groupfreetimeid))
create table message (messageid number(19,0) not null, pinned number(1,0) not null, text varchar2(255 char) not null, tstamp timestamp not null, authorid number(19,0) not null, friendgroupid number(19,0) not null, pollid number(19,0), primary key (messageid))
create table poll (pollid number(19,0) not null, description varchar2(255 char), primary key (pollid))
create table polloption (polloptionid number(19,0) not null, description varchar2(255 char) not null, pollid number(19,0) not null, primary key (polloptionid))
create table timeslot (timeslotid number(19,0) not null, datetime varchar2(255 char) not null, primary key (timeslotid))
create table usr (userid number(19,0) not null, email varchar2(255 char) not null, firstname varchar2(255 char) not null, lastname varchar2(255 char) not null, passwordhash varchar2(255 char) not null, phone varchar2(255 char), primary key (userid))
create table votes (polloptionid number(19,0) not null, userid number(19,0) not null, primary key (polloptionid, userid))
create index IDX4m1ev9euglqji2626yj4ugjsm on friendgroup (name)
create index IDXike6jbm622c0rdctw27kon6aq on message (friendgroupid)
alter table usr add constraint UKg9l96r670qkidthshajdtxrqf unique (email)
alter table connections add constraint FKmpdnls9ilsm085e7hnktlvmwl foreign key (friendgroupid) references friendgroup
alter table connections add constraint FKi8t4v1nfut2yb4a8w1xstht7v foreign key (status) references friendgroupstatus
alter table connections add constraint FKcxanxngtnuil3o4u2r411fah2 foreign key (userid) references usr
alter table freetime add constraint FKkm9o77htwelhicylsyp4dhd0u foreign key (timeslotid) references timeslot
alter table freetime add constraint FK68ciyys1q02ucepsak8q3b96s foreign key (userid) references usr
alter table friendslist add constraint FKqisc5ald3t7gsa14syqikqtsw foreign key (friend_userid) references usr
alter table friendslist add constraint FK2khq0jmsw0lfrn1emh1mavs29 foreign key (statusid) references friendsliststatus
alter table friendslist add constraint FKqmt690sk2eplg4vscyeej8gsh foreign key (user_userid) references usr
alter table groupfreetime add constraint FKb70f8gpe01f3c8qrxl5pwpj9j foreign key (friendgroupid) references friendgroup
alter table groupfreetime add constraint FKcamkmmde4pb1a77ntiwqd9tt4 foreign key (timeslotid) references timeslot
alter table message add constraint FK8nxse8mttc4vm8q8dxq9xb0lc foreign key (authorid) references usr
alter table message add constraint FKl8f1olsempv6nqtr30trp4rth foreign key (friendgroupid) references friendgroup
alter table message add constraint FK8vwdd22k5v97xfx446cvkhpbg foreign key (pollid) references poll
alter table polloption add constraint FKqempmbl9dfwds5eyx1rksse6n foreign key (pollid) references poll
alter table votes add constraint FK6uxdackwrl6n042ddnm3l5wrk foreign key (userid) references usr
alter table votes add constraint FKciwyci8v6xils1uk1vr75lnm2 foreign key (polloptionid) references polloption
create sequence connectionsautoinc start with 1 increment by 1
create sequence freetimeautoinc start with 1 increment by 1
create sequence friendgroupautoinc start with 1 increment by 1
create sequence groupfreetimeautoinc start with 1 increment by 1
create sequence messageautoinc start with 1 increment by 1
create sequence pollautoinc start with 1 increment by 1
create sequence polloptionautoinc start with 1 increment by 1
create sequence userautoinc start with 1 increment by 1
create table connections (connection_id number(19,0) not null, isadmin number(1,0) not null, friendgroupid number(19,0) not null, status number(5,0) not null, userid number(19,0) not null, primary key (connection_id))
create table freetime (freetimeid number(19,0) not null, isdefault number(1,0) not null, scheduled number(1,0) not null, timeslotid number(19,0) not null, userid number(19,0) not null, primary key (freetimeid))
create table friendgroup (friendgroupid number(19,0) not null, name varchar2(255 char) not null, primary key (friendgroupid))
create table friendgroupstatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table friendslist (friend_userid number(19,0) not null, user_userid number(19,0) not null, statusid number(5,0) not null, primary key (friend_userid, user_userid))
create table friendsliststatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table groupfreetime (groupfreetimeid number(19,0) not null, numusers number(10,0) not null, friendgroupid number(19,0) not null, timeslotid number(19,0) not null, primary key (groupfreetimeid))
create table message (messageid number(19,0) not null, pinned number(1,0) not null, text varchar2(255 char) not null, tstamp timestamp not null, authorid number(19,0) not null, friendgroupid number(19,0) not null, pollid number(19,0), primary key (messageid))
create table poll (pollid number(19,0) not null, description varchar2(255 char), primary key (pollid))
create table polloption (polloptionid number(19,0) not null, description varchar2(255 char) not null, pollid number(19,0) not null, primary key (polloptionid))
create table timeslot (timeslotid number(19,0) not null, datetime varchar2(255 char) not null, primary key (timeslotid))
create table usr (userid number(19,0) not null, email varchar2(255 char) not null, firstname varchar2(255 char) not null, lastname varchar2(255 char) not null, passwordhash varchar2(255 char) not null, phone varchar2(255 char), primary key (userid))
create table votes (polloptionid number(19,0) not null, userid number(19,0) not null, primary key (polloptionid, userid))
create index IDX4m1ev9euglqji2626yj4ugjsm on friendgroup (name)
create index IDXike6jbm622c0rdctw27kon6aq on message (friendgroupid)
alter table usr add constraint UKg9l96r670qkidthshajdtxrqf unique (email)
alter table connections add constraint FKmpdnls9ilsm085e7hnktlvmwl foreign key (friendgroupid) references friendgroup
alter table connections add constraint FKi8t4v1nfut2yb4a8w1xstht7v foreign key (status) references friendgroupstatus
alter table connections add constraint FKcxanxngtnuil3o4u2r411fah2 foreign key (userid) references usr
alter table freetime add constraint FKkm9o77htwelhicylsyp4dhd0u foreign key (timeslotid) references timeslot
alter table freetime add constraint FK68ciyys1q02ucepsak8q3b96s foreign key (userid) references usr
alter table friendslist add constraint FKqisc5ald3t7gsa14syqikqtsw foreign key (friend_userid) references usr
alter table friendslist add constraint FK2khq0jmsw0lfrn1emh1mavs29 foreign key (statusid) references friendsliststatus
alter table friendslist add constraint FKqmt690sk2eplg4vscyeej8gsh foreign key (user_userid) references usr
alter table groupfreetime add constraint FKb70f8gpe01f3c8qrxl5pwpj9j foreign key (friendgroupid) references friendgroup
alter table groupfreetime add constraint FKcamkmmde4pb1a77ntiwqd9tt4 foreign key (timeslotid) references timeslot
alter table message add constraint FK8nxse8mttc4vm8q8dxq9xb0lc foreign key (authorid) references usr
alter table message add constraint FKl8f1olsempv6nqtr30trp4rth foreign key (friendgroupid) references friendgroup
alter table message add constraint FK8vwdd22k5v97xfx446cvkhpbg foreign key (pollid) references poll
alter table polloption add constraint FKqempmbl9dfwds5eyx1rksse6n foreign key (pollid) references poll
alter table votes add constraint FK6uxdackwrl6n042ddnm3l5wrk foreign key (userid) references usr
alter table votes add constraint FKciwyci8v6xils1uk1vr75lnm2 foreign key (polloptionid) references polloption
create sequence connectionsautoinc start with 1 increment by 1
create sequence freetimeautoinc start with 1 increment by 1
create sequence friendgroupautoinc start with 1 increment by 1
create sequence groupfreetimeautoinc start with 1 increment by 1
create sequence messageautoinc start with 1 increment by 1
create sequence pollautoinc start with 1 increment by 1
create sequence polloptionautoinc start with 1 increment by 1
create sequence userautoinc start with 1 increment by 1
create table connections (connection_id number(19,0) not null, isadmin number(1,0) not null, friendgroupid number(19,0) not null, status number(5,0) not null, userid number(19,0) not null, primary key (connection_id))
create table freetime (freetimeid number(19,0) not null, isdefault number(1,0) not null, scheduled number(1,0) not null, timeslotid number(19,0) not null, userid number(19,0) not null, primary key (freetimeid))
create table friendgroup (friendgroupid number(19,0) not null, name varchar2(255 char) not null, primary key (friendgroupid))
create table friendgroupstatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table friendslist (friend_userid number(19,0) not null, user_userid number(19,0) not null, statusid number(5,0) not null, primary key (friend_userid, user_userid))
create table friendsliststatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table groupfreetime (groupfreetimeid number(19,0) not null, numusers number(10,0) not null, friendgroupid number(19,0) not null, timeslotid number(19,0) not null, primary key (groupfreetimeid))
create table message (messageid number(19,0) not null, pinned number(1,0) not null, text varchar2(255 char) not null, tstamp timestamp not null, authorid number(19,0) not null, friendgroupid number(19,0) not null, pollid number(19,0), primary key (messageid))
create table poll (pollid number(19,0) not null, description varchar2(255 char), primary key (pollid))
create table polloption (polloptionid number(19,0) not null, description varchar2(255 char) not null, pollid number(19,0) not null, primary key (polloptionid))
create table timeslot (timeslotid number(19,0) not null, datetime varchar2(255 char) not null, primary key (timeslotid))
create table usr (userid number(19,0) not null, email varchar2(255 char) not null, firstname varchar2(255 char) not null, lastname varchar2(255 char) not null, passwordhash varchar2(255 char) not null, phone varchar2(255 char), primary key (userid))
create table votes (polloptionid number(19,0) not null, userid number(19,0) not null, primary key (polloptionid, userid))
create index IDX4m1ev9euglqji2626yj4ugjsm on friendgroup (name)
create index IDXike6jbm622c0rdctw27kon6aq on message (friendgroupid)
alter table usr add constraint UKg9l96r670qkidthshajdtxrqf unique (email)
alter table connections add constraint FKmpdnls9ilsm085e7hnktlvmwl foreign key (friendgroupid) references friendgroup
alter table connections add constraint FKi8t4v1nfut2yb4a8w1xstht7v foreign key (status) references friendgroupstatus
alter table connections add constraint FKcxanxngtnuil3o4u2r411fah2 foreign key (userid) references usr
alter table freetime add constraint FKkm9o77htwelhicylsyp4dhd0u foreign key (timeslotid) references timeslot
alter table freetime add constraint FK68ciyys1q02ucepsak8q3b96s foreign key (userid) references usr
alter table friendslist add constraint FKqisc5ald3t7gsa14syqikqtsw foreign key (friend_userid) references usr
alter table friendslist add constraint FK2khq0jmsw0lfrn1emh1mavs29 foreign key (statusid) references friendsliststatus
alter table friendslist add constraint FKqmt690sk2eplg4vscyeej8gsh foreign key (user_userid) references usr
alter table groupfreetime add constraint FKb70f8gpe01f3c8qrxl5pwpj9j foreign key (friendgroupid) references friendgroup
alter table groupfreetime add constraint FKcamkmmde4pb1a77ntiwqd9tt4 foreign key (timeslotid) references timeslot
alter table message add constraint FK8nxse8mttc4vm8q8dxq9xb0lc foreign key (authorid) references usr
alter table message add constraint FKl8f1olsempv6nqtr30trp4rth foreign key (friendgroupid) references friendgroup
alter table message add constraint FK8vwdd22k5v97xfx446cvkhpbg foreign key (pollid) references poll
alter table polloption add constraint FKqempmbl9dfwds5eyx1rksse6n foreign key (pollid) references poll
alter table votes add constraint FK6uxdackwrl6n042ddnm3l5wrk foreign key (userid) references usr
alter table votes add constraint FKciwyci8v6xils1uk1vr75lnm2 foreign key (polloptionid) references polloption
create sequence connectionsautoinc start with 1 increment by 1
create sequence freetimeautoinc start with 1 increment by 1
create sequence friendgroupautoinc start with 1 increment by 1
create sequence groupfreetimeautoinc start with 1 increment by 1
create sequence messageautoinc start with 1 increment by 1
create sequence pollautoinc start with 1 increment by 1
create sequence polloptionautoinc start with 1 increment by 1
create sequence userautoinc start with 1 increment by 1
create table connections (connection_id number(19,0) not null, isadmin number(1,0) not null, friendgroupid number(19,0) not null, status number(5,0) not null, userid number(19,0) not null, primary key (connection_id))
create table freetime (freetimeid number(19,0) not null, isdefault number(1,0) not null, scheduled number(1,0) not null, timeslotid number(19,0) not null, userid number(19,0) not null, primary key (freetimeid))
create table friendgroup (friendgroupid number(19,0) not null, name varchar2(255 char) not null, primary key (friendgroupid))
create table friendgroupstatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table friendslist (friend_userid number(19,0) not null, user_userid number(19,0) not null, statusid number(5,0) not null, primary key (friend_userid, user_userid))
create table friendsliststatus (statusid number(5,0) not null, statusname varchar2(255 char) not null, primary key (statusid))
create table groupfreetime (groupfreetimeid number(19,0) not null, numusers number(10,0) not null, friendgroupid number(19,0) not null, timeslotid number(19,0) not null, primary key (groupfreetimeid))
create table message (messageid number(19,0) not null, pinned number(1,0) not null, text varchar2(255 char) not null, tstamp timestamp not null, authorid number(19,0) not null, friendgroupid number(19,0) not null, pollid number(19,0), primary key (messageid))
create table poll (pollid number(19,0) not null, description varchar2(255 char), primary key (pollid))
create table polloption (polloptionid number(19,0) not null, description varchar2(255 char) not null, pollid number(19,0) not null, primary key (polloptionid))
create table timeslot (timeslotid number(19,0) not null, datetime varchar2(255 char) not null, primary key (timeslotid))
create table usr (userid number(19,0) not null, email varchar2(255 char) not null, firstname varchar2(255 char) not null, lastname varchar2(255 char) not null, passwordhash varchar2(255 char) not null, phone varchar2(255 char), primary key (userid))
create table votes (polloptionid number(19,0) not null, userid number(19,0) not null, primary key (polloptionid, userid))
create index IDX4m1ev9euglqji2626yj4ugjsm on friendgroup (name)
create index IDXike6jbm622c0rdctw27kon6aq on message (friendgroupid)
alter table usr add constraint UKg9l96r670qkidthshajdtxrqf unique (email)
alter table connections add constraint FKmpdnls9ilsm085e7hnktlvmwl foreign key (friendgroupid) references friendgroup
alter table connections add constraint FKi8t4v1nfut2yb4a8w1xstht7v foreign key (status) references friendgroupstatus
alter table connections add constraint FKcxanxngtnuil3o4u2r411fah2 foreign key (userid) references usr
alter table freetime add constraint FKkm9o77htwelhicylsyp4dhd0u foreign key (timeslotid) references timeslot
alter table freetime add constraint FK68ciyys1q02ucepsak8q3b96s foreign key (userid) references usr
alter table friendslist add constraint FKqisc5ald3t7gsa14syqikqtsw foreign key (friend_userid) references usr
alter table friendslist add constraint FK2khq0jmsw0lfrn1emh1mavs29 foreign key (statusid) references friendsliststatus
alter table friendslist add constraint FKqmt690sk2eplg4vscyeej8gsh foreign key (user_userid) references usr
alter table groupfreetime add constraint FKb70f8gpe01f3c8qrxl5pwpj9j foreign key (friendgroupid) references friendgroup
alter table groupfreetime add constraint FKcamkmmde4pb1a77ntiwqd9tt4 foreign key (timeslotid) references timeslot
alter table message add constraint FK8nxse8mttc4vm8q8dxq9xb0lc foreign key (authorid) references usr
alter table message add constraint FKl8f1olsempv6nqtr30trp4rth foreign key (friendgroupid) references friendgroup
alter table message add constraint FK8vwdd22k5v97xfx446cvkhpbg foreign key (pollid) references poll
alter table polloption add constraint FKqempmbl9dfwds5eyx1rksse6n foreign key (pollid) references poll
alter table votes add constraint FK6uxdackwrl6n042ddnm3l5wrk foreign key (userid) references usr
alter table votes add constraint FKciwyci8v6xils1uk1vr75lnm2 foreign key (polloptionid) references polloption