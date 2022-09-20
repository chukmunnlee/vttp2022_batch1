create table post (
	post_id int auto_increment,
	title varchar(256),
	media_type varchar(256),
	pic mediumblob,

	primary key(post_id)
);
