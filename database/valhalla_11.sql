set schema 'valhalla_fitness';

drop table if exists friendship_requests cascade ;
create table friendship_requests(
    requester_username varchar(50) references trainee2(username),
    accepter_username varchar(50) references trainee2(username)
)