set schema 'valhalla_fitness';

drop table if exists friendship_list cascade ;
create table friendship_list(
    requester_username varchar(50) references trainee2(username)  ,
    accepter_username varchar(50)  references trainee2(username),
    primary key (requester_username,accepter_username)
);
drop table if exists friendship_request cascade ;
create table friendship_request(
    requester_username varchar(50)  references trainee2(username)   ,
    accepter_username varchar(50) references trainee2(username),
    primary key  (requester_username,accepter_username)
);


insert into trainee2 values ('a','a','a','a',null,null,null,null,100,100,true,'my status');
insert into trainee2 values ('e','e','e','e',null,null,null,null,100,100,true,'my status');
insert into trainee2 values ('b','b','b','b',null,null,null,null,100,100,true,'my status');
insert into friendship_list values ('d','a');
insert into friendship_list values ('d','e');
insert into friendship_request values ('b','d');

-- select username, status from trainee2 where username in (select accepter_username from friendship_list where requester_username = 'd')

-- select username, status from trainee2 where username in (select requester_username from friendship_list where accepter_username = ?)

