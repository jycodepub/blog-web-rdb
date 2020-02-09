drop table if exists comment;
drop table if exists post;

create table `post` (
  `id` bigint(20) not null auto_increment,
  `author` varchar (255),
  `content` text,
  `timestamp` datetime,
  primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table comment (
  `id` bigint(20) not null auto_increment,
  `post_id` bigint(20) not null,
  `author` varchar (255),
  `content` text,
  `timestamp` datetime,
  primary key (id),
  index `comment_idx` (post_id),
  foreign key `comment_fk` (post_id) references post(id) on delete cascade
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `user` (
  `username` varchar (255) not null,
  `password` varchar (255) not null,
  primary key (`username`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;