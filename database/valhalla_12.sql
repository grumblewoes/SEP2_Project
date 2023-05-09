set schema 'valhalla_fitness';

drop table if exists coach cascade ;
create table coach (
                          username varchar(50) primary key ,
                          password varchar(50),
                          first_name varchar(50),
                          last_name varchar(50),
                          bench_best smallint,
                          squat_best smallint,
                          deadlift_best smallint,
                          coach_username varchar(50),
                          height numeric(5,2),
                          weight numeric(5,2),
                          status varchar(50),
                          share boolean
);