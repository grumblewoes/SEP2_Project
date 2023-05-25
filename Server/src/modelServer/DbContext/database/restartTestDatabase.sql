drop schema if exists valhalla_test cascade;
create schema valhalla_test;
set schema 'valhalla_test';
create table if not exists trainee2 (
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
create table if not exists folder2 (
                         id bigint generated always as identity primary key ,
                         title varchar(50) ,
                         trainee_username varchar(50) references trainee2(username)
);
create table if not exists exercise_type2 (
    title varchar(50) primary key
);

insert into exercise_type2(title) values ('bench_press');
insert into exercise_type2(title) values ('squat');
insert into exercise_type2(title) values ('deadlift');

create table if not exists user_exercise2 (
                                id  bigint generated always as identity  primary key ,
                                trainee_username varchar(50) references trainee2(username),
                                exercise_name varchar(50) references exercise_type2(title),
                                folderId bigint references folder2(id),
                                repetitions smallint,
                                weight smallint
);
create table if not exists friendship_list(
                                requester_username varchar(50) references trainee2(username)  ,
                                accepter_username varchar(50)  references trainee2(username),
                                primary key (requester_username,accepter_username)
);
create table if not exists friendship_request(
                                   requester_username varchar(50)  references trainee2(username)   ,
                                   accepter_username varchar(50) references trainee2(username),
                                   primary key  (requester_username,accepter_username)
);
create table if not exists coach (
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
create table if not exists coach_request (
                               trainee_username varchar(50) references trainee2(username) unique,
                               coach_username varchar(50) references coach(username)
);
create table if not exists meeting_list(
                             trainee_username varchar(50) references trainee2(username) not null ,
                             coach_username varchar(50)  references coach(username) not null ,
                             date_of_meeting date not null ,
                             primary key (trainee_username,coach_username, date_of_meeting)
);
create table if not exists meeting_request(
                                trainee_username varchar(50) references trainee2(username) not null ,
                                coach_username varchar(50)  references coach(username) not null ,
                                date_of_meeting date not null ,
                                primary key (trainee_username,coach_username, date_of_meeting)
);