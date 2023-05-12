set schema 'valhalla_fitness';

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