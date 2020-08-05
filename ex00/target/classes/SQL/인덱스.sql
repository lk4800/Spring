/* 인덱스란?
 * 1.오라클에서 인덱스를 사용하는 이유는 보다더 빠른 검색을 위해서이다
 * 2.테이블 생성시 컬럼에 기본키 제약조건을 지정하면 오라클에서 
 * 해당 컬럼에 자동으로 인덱스를 부여한다 */

--emp201 테이블 생성
create table emp201(
 empno number(38) primary key --사원번호
 ,ename varchar2(100) --사원명
 ,sal number(38) --급여
);

insert into emp201 values(11,'신지환',150);
insert into emp201 values(12,'신찌환',200);
insert into emp201 values(13,'신찌완',300);

select * from emp201;

--서브쿼리로 복제본 테이블 emp202를 생성 복제본 테이블은 제약조건은 복사 못한다
--그래서 인덱스가 존재하지 않는다
create table emp202 as select * from emp201;

select * from emp202;

--생성된 인덱스명 확인
select table_name, index_name, column_name from user_ind_COLUMNS
where table_name in('EMP201','EMP202');
--table_name 컬럼에는 테이블명이 저장, index_name컬럼에는 인덱스 명이 저장
--column_name 컬럼에는 인덱스가 설정된 컬럼명이 저장

--emp202복제본 테이블의 ename컬럼에 인덱스를 생성
create index idx_emp202_ename -- idx_emp202_ename 이라는 인덱스명이 생성
on emp202(ename);

--id_emp202_empno 인덱스를 생성
create index id_emp202_empno on emp202(empno);

--id_emp202_ename인덱스 삭제
drop index idx_emp202_ename;

--emp202로 테이블 구조만 복제하고 레코드는 복제되지 않는 emp203테이블 생성
create table emp203 as select * from emp202 where 10=0;
--조건식을 틀리게 해서 구조만 복제

select * from emp203;
insert into emp203 values(10,'윤혜진',1000);
insert into emp203 values(20,'신지환',2000);
insert into emp203 values(30,'지환',3000);

--중복레코드가 없는 empno컬럼에 고유인덱스를 생성
create unique index idx_emp203_empno
on emp203(empno);

--중복레코드가 있는 ename 컬럼에 고유 인덱스를 생성하면 에러가 발생
create unique index idx_emp203_ename
on emp203(ename)

--중복레코드가 있는 ename컬럼은 비고유 인덱스를 생성
create index idx_emp203_ename on emp203(ename);


delete from emp203 where empno=10























