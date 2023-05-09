set schema 'valhalla_fitness';

drop table if exists coach_request cascade;

create table coach_request (
    trainee_username varchar(50) references trainee2(username),
    coach_username varchar(50) references coach(username)
);

