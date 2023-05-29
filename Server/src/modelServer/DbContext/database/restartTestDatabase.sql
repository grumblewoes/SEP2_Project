drop schema if exists valhalla_test cascade;
create schema valhalla_test;
set schema 'valhalla_test';


-- drop schema if exists valhalla_fitness cascade;
-- create schema valhalla_fitness;
-- set schema 'valhalla_fitness';


drop table if exists coach cascade ;
create table coach (
                       username varchar(50) primary key ,
                       password varchar(50),
                       first_name varchar(50),
                       last_name varchar(50),
                       height numeric(5,2),
                       weight numeric(5,2),
                       bench_best smallint,
                       squat_best smallint,
                       deadlift_best smallint,
                       status varchar(50)
);

drop table if exists trainee2 cascade ;
create table trainee2 (
                          username varchar(50) primary key ,
                          password varchar(50),
                          first_name varchar(50),
                          last_name varchar(50),
                          bench_best smallint,
                          squat_best smallint,
                          deadlift_best smallint,
                          coach_username varchar(50) references coach(username),
                          height numeric(5,2),
                          weight numeric(5,2),
                          share_profile boolean,
                          status varchar(50)
);

-- insert into trainee2 values ('d','d','d','d',null,null,null,null,181,72,true,'my status');

drop table if exists folder2 cascade ;
create table folder2 (
                         id bigint generated always as identity primary key ,
                         title varchar(50) ,
                         trainee_username varchar(50) references trainee2(username)
);



-- insert into folder2(title, trainee_username) values ('my_folder','d');
-- insert into folder2(title, trainee_username) values ('your_mom','d');

drop table if exists exercise_type2 cascade ;
create table exercise_type2 (
    title varchar(50) primary key
);
insert into exercise_type2(title) values ('bench_press');
insert into exercise_type2(title) values ('squat');
insert into exercise_type2(title) values ('deadlift');
insert into exercise_type2(title) values ('lat_pulldown');
insert into exercise_type2(title) values ('overhead_press');
insert into exercise_type2(title) values ('barbell_curls');
insert into exercise_type2(title) values ('leg_curl');
insert into exercise_type2(title) values ('facepull');
insert into exercise_type2(title) values ('seated_row');
insert into exercise_type2(title) values ('dumbell_press');
insert into exercise_type2(title) values ('dumbbell_curls');

drop table if exists user_exercise2 cascade ;
create table user_exercise2 (
                                id  bigint generated always as identity  primary key ,
                                trainee_username varchar(50) references trainee2(username),
                                exercise_name varchar(50) references exercise_type2(title),
                                folderId bigint references folder2(id),
                                repetitions smallint,
                                weight smallint
);



-- insert into user_exercise2(trainee_username, exercise_name, folderId, repetitions, weight) values ('d','squat',1,10,120);
-- insert into user_exercise2(trainee_username, exercise_name, folderId, repetitions, weight) values ('d','deadlift',1,3,150);
-- insert into user_exercise2(trainee_username, exercise_name, folderId, repetitions, weight) values ('d','deadlift',1,1,170);
-- insert into user_exercise2(trainee_username, exercise_name, folderId, repetitions, weight) values ('d','leg_curl',2,3,30);




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


-- insert into trainee2 values ('a','a','a','a',null,null,null,null,100,100,true,'my status');
-- insert into trainee2 values ('e','e','e','e',null,null,null,null,100,100,true,'my status');
-- insert into trainee2 values ('b','b','b','b',null,null,null,null,100,100,true,'my status');
-- insert into friendship_list values ('d','a');
-- insert into friendship_list values ('d','e');
-- insert into friendship_request values ('b','d');




-- insert into coach(username,password,first_name,last_name,height,weight) values ('coach','coach','dasdasd','dasdasd',120,120);
-- insert into coach(username,password,first_name,last_name,height,weight) values ('c','c','dasdasd','dasdasd',120,120);




drop table if exists coach_request cascade;

create table coach_request (
                               trainee_username varchar(50) references trainee2(username) unique,
                               coach_username varchar(50) references coach(username)
);

-- insert into coach_request(trainee_username,coach_username) values ('d','coach');



drop table if exists meeting_list cascade ;
create table meeting_list(
                             trainee_username varchar(50) references trainee2(username) not null ,
                             coach_username varchar(50)  references coach(username) not null ,
                             date_of_meeting date not null ,
                             primary key (trainee_username,coach_username, date_of_meeting)
);
drop table if exists meeting_request cascade ;
create table meeting_request(
                                trainee_username varchar(50) references trainee2(username) not null ,
                                coach_username varchar(50)  references coach(username) not null ,
                                date_of_meeting date not null ,
                                primary key (trainee_username,coach_username, date_of_meeting)
);
