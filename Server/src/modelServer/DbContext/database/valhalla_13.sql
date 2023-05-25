set schema 'valhalla_fitness';

drop table if exists coach_request cascade;

create table coach_request (
    trainee_username varchar(50) references trainee2(username) unique,
    coach_username varchar(50) references coach(username)
);

insert into coach_request(trainee_username,coach_username) values ('d','coach');