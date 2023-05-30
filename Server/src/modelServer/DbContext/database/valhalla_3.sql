 set schema 'valhalla_fitness';


drop table if exists trainee2 cascade ;
create table trainee2 (
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
                          share_profile boolean,
                          status varchar(50)
);

insert into trainee2 values ('d','d','d','d',null,null,null,null,181,72,true,'my status');

drop table if exists folder2 cascade ;
create table folder2 (
                         id bigint generated always as identity primary key ,
                         title varchar(50) ,
                         trainee_username varchar(50) references trainee2(username)
);



insert into folder2(title, trainee_username) values ('my_folder','d');
insert into folder2(title, trainee_username) values ('your_mom','d');

drop table if exists exercise_type2 cascade ;
create table exercise_type2 (
    title varchar(50) primary key
);

insert into exercise_type2(title) values ('bench_press');
insert into exercise_type2(title) values ('squat');
insert into exercise_type2(title) values ('deadlift');
insert into exercise_type2(title) values ('talking_to_women');

drop table if exists user_exercise2 cascade ;
create table user_exercise2 (
                                id  bigint generated always as identity  primary key ,
                                trainee_username varchar(50) references trainee2(username),
                                exercise_name varchar(50) references exercise_type2(title),
                                folderId bigint references folder2(id),
                                repetitions smallint,
                                weight smallint
);



insert into user_exercise2(trainee_username, exercise_name, folderId, repetitions, weight) values ('d','squat',1,10,120);
insert into user_exercise2(trainee_username, exercise_name, folderId, repetitions, weight) values ('d','deadlift',1,3,150);
insert into user_exercise2(trainee_username, exercise_name, folderId, repetitions, weight) values ('d','deadlift',1,1,170);
insert into user_exercise2(trainee_username, exercise_name, folderId, repetitions, weight) values ('d','talking_to_women',2,3,30);


