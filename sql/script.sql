create database guestbook;

use guestbook;

CREATE TABLE guestbook_message (
	message_id int not null auto_increment primary key,
    guest_name varchar(50) not null,
    password varchar(10) not null,
    message text not null
);

select * from guestbook_message order by message_id desc;