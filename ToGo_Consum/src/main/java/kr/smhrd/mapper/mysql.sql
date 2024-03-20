-- 실행 alt +s

-- member table 생성
create table member(
   email varchar(100) not null,
   pw varchar(100) not null,
   tel varchar(100) not null,
   address varchar(100) not null,
   primary key(email)
);

-- test 데이터 넣기
insert into member values('admin@smhrd.com', '1234', '010-0000-0000', '동구 동명동');
insert into member values('admin', '1234', '010-0000-0000', '동구 동명동');
-- 조회하기
select * from member;

-- message table 생성

-- 숫자 알아서 증가
create table message(
	num int not null auto_increment, 
   sendE varchar(100),
   receiveE varchar(100),
   message varchar(2000),
   m_date datetime default now(),
   primary key(num)
);

create table message(
   num int not null auto_increment,
   sendE varchar(100),
   receiveE varchar(100),
   message varchar(2000),
   m_date datetime default now(),
   primary key(num)
);

-- test 데이터
insert into message(sendE, receiveE,message) values('test1','test1','test1');
insert into message(sendE, receiveE,message) values('test2','test2','test2');
select * from message;

-- board table 생성

create table board(
	num int not null auto_increment,
	title varchar(100) not null, 
   writer varchar(100) not null,
   filename varchar(100),
   content varchar(2000) not null,
   b_date datetime default now(),
   primary key(num)
);

-- 조회수 컬럼 추가
alter table board add(count int default 0);


drop table board;
select * from board;
insert into board(title, writer, filename, content) values('test1','test1','test1','test1');
insert into board(title, writer, filename, content) values('test12','test12','test12','test12');