select * from tab;

create table dbtest(
    name varchar2(50),
    age number,
    height number(5,2),
    logtime date
);

desc dbtest;
drop table dbtest;
flashback table dbtest to before drop;

insert into dbtest (name, age, height, logtime) values ('illyasviel',11,133,sysdate);
insert into dbtest (name, age,logtime) values ('illya이리야',11,sysdate);

select * from dbtest;

select name, age, height, logtime from dbtest;
insert into dbtest values ('illya',11,133.001,sysdate);
insert into dbtest values ('von',12,133.002,sysdate);
insert into dbtest values ('einzbern',13,133.003,sysdate);
insert into dbtest values ('mmillya',11,133.001,sysdate);
insert into dbtest (name, age,logtime) values ('reimu',18,sysdate);
insert into dbtest (age, height, logtime) values (11,165,sysdate);
insert into dbtest (name, height,logtime) values ('illya',133.001,sysdate);

select count(*) from dbtest;

select count(height) from dbtest;

--정렬
select * from dbtest order by name asc;
select * from dbtest order by name desc;
select * from dbtest order by age asc, height desc;


-- 조건 검색
select * from dbtest where name = 'illya'; // 문자열은 대문자 소문자 구별함
select * from dbtest where name like '%illya%'; // %를 쓰려면 '='이 아니라 like를 써야함
select * from dbtest where name like '__illya'; // _하나는 문자 하나를 가르킨다
select * from dbtest where name like '%illya%' and age >= 11;
select * from dbtest where height is null; // height가 없는 행을 출력
select * from dbtest where height is not null; // height가 있는 행을 출력

--(문제) 사람의 데이터 중 어느 하나의 컬럼에 null이 들어가 있으면 모두 출력하세요
select * from dbtest where name is null or age is null or height is null or logtime is null; // 컬럼 하나하나 is null 붙야함


commit; // 완벽하게 저장함
select * from dbtest;
--삭제
delete from dbtest where name = 'illya';
delete dbtest; // 이 table에 있는 모든 행 삭제
--(문제) 이름이 illya로 시작하는 사람들 모두 삭제
delete from dbtest where name like 'illya%';
rollback; // commit 부분으로 롤백

--수정
update dbtest set age=age+1 where name like '%reimu';
update dbtest set age=30 where name = 'reimu';

--나이가 null 인사람의 데이터를 모두 30살로 수정
update dbtest set age=30 where age is null;

commit;

select * from dbtest;

---------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------
--depart 부서 테이블 생성
create table depart(
    deptno number not null, --부서번호(학과번호)
    dname varchar2(25) not null, --부서명(학과명)
    loc varchar2(10) default null --위치(건물명)  
);


select * from depart;
----drop table depart purge;


INSERT INTO depart (deptno, dname) VALUES (302, '전기공학과');
INSERT INTO depart VALUES (101, '컴퓨터공학과', '1호관');
INSERT INTO depart VALUES (102, '멀티미디어학과', '2호관');
INSERT INTO depart VALUES (201, '전자공학과', '3호관');
INSERT INTO depart VALUES (202, '기계공학과', '4호관');


--emp 테이블
create table emp(
    empno number primary key, --직원번호 primary key:unique(데이터 중복 금지), not null(반드시 데이터가 있어야 한다.)
    name varchar2(20) not null, --이름
    position varchar2(10) not null, --직급
    tel varchar2(15) not null, --연락처
    deptno number not null); --학과번호
    --empYear number, --연차
    --regTime date --입사 날짜
    --primary key(empno)


----drop table emp purge;


--INSERT INTO emp (empno,name,position,tel,deptno,empYear) VALUES (20101, '홍길동', '사원', '031)781-2158', 101, null);
INSERT INTO emp VALUES (20101, '홍길동', '사원', '031)781-2158', 101);
INSERT INTO emp VALUES (10102, '김철수', '과장', '032)261-8947', 101);
INSERT INTO emp VALUES (10103, '이영희', '대리', '02)824-9637', 102);
INSERT INTO emp VALUES (10104, '고길동', '사원', '02)824-9637', 102);
INSERT INTO emp VALUES (10105, '강호동', '사원', '02)824-9637', 102);
INSERT INTO emp VALUES (10106, '아이유', '사원', '02)881-2158', 105);




--중복값을 방지하기 위해 자동으로 순차적으로 증강하는 순변을 생성하는 데이터베이스 객체->시퀀스
create sequence seq_board nocycle nocache; // 시퀀스 생성
drop sequence seq_board; // 시퀀스 삭제

create sequence empno nocycle nocache;
INSERT INTO emp VALUES (10106, '아이유', '사원', '02)881-2158', 105, sysdate);
select empno, to_char(sysdate, 'YY-mm-dd AM hh24:mi:ss') from emp; // YY년, mm월, dd일, hh시(hh24하면 12시 넘어감), mi분, ss초
select empno, to_char(sysdate, '""YYYY"-"MM"-"DD" AM "hh24":"mi":"ss"') from emp; // 업그레이드



--join sql문
--조인구문
--1)내부 조인 inner join : 두 테이블에서 조건이 일치하는 데이터만 조회(교집합)
--방법1. 오라클 전용 구문
select * from emp, depart where emp.deptno=depart.deptno;
select empno, name, dname, emp.deptno from emp, depart where emp.deptno=depart.deptno;

--방법2. Ansi 표준
select * from emp join depart using(deptno);
select empno, name, dname, deptno from emp join depart using(deptno);
--별명, 별칭 alias(as) 짓기
select empno "사원번호", name "사원명", dname "부서번호", deptno "부서명" from emp join depart using(deptno);

--2)외부 조인 outer join : 조건이 일치하지 않는 데이터도 함께 조회(왼쪽전체+교집합, 오른쪽 전체+교집합, 합집합)
--left join
--방법1. Ansi 표준
select * from emp left join depart using(deptno);
select empno, name, dname, deptno from emp left join depart using(deptno);
--방법2. 오라클 전용 구문
select e.name, d.dname, d.deptno from emp e, depart d where e.deptno=d.deptno(+);
--right join
--방법1. Ansi 표준
select * from emp right join depart using(deptno);
--방법2. 오라클 전용 구문
select e.name, d.dname, d.deptno from emp e, depart d where e.deptno(+)=d.deptno;
--full join
select * from depart full join emp using(deptno);
commit;
