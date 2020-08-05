--tbl_member2 테이블 설계
create table tbl_member2( --회원 테이블
 userid varchar2(50) primary key --회원아이디
 ,userpw varchar2(100) not null --비번
 ,username varchar2(100) not null --회원이름
 ,regdate date default sysdate --가입날짜
 ,updatedate date default sysdate --수정날짜
 ,enabled char(1) default '1'
);
select * from TBL_MEMBER2 order by userid asc

--권한 부여 테이블
create table tbl_member2_auth(
 userid varchar2(50) not null
 ,auth varchar2(50) not null
 ,constraint fk_member_auth foreign key(userid) references
 tbl_member2(userid) --외래키 설정, constraint로 사용자 정의 제약조건명
 --fk_member_auth를 설정 부모테이블의 userid컬럼 회원 아이디만 저장가능함=> 
 --참조무결성 이라고 한다 primary key(기본키), foreign key (외래키)
);
select * from tbl_member2_auth order by userid asc

--자동 로그인 기능을 만들기 위해서 로그인 정보를 유지하는 테이블을 설계
create table persistent_logins(
 username varchar2(64) not null --아이디
 ,series varchar2(64) primary key --비번
 ,token varchar2(64) not null --토큰 정보
 ,last_used timestamp not null --로그인 한 날짜, 시간
 --timestamp는 날짜 타입
)
select * from persistent_logins;
































































































