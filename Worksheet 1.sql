drop table member;
drop sequence member_no_seq;

CREATE TABLE member
( 
no        NUMBER(8),
name      VARCHAR2(30),
email     VARCHAR2(80),
password  VARCHAR2(30),
gender    VARCHAR2(10)
) ;

ALTER TABLE member
ADD ( CONSTRAINT member_no_pk PRIMARY KEY ( no ) );


CREATE SEQUENCE member_no_seq
 START WITH     1
 INCREMENT BY   1
 MAXVALUE       99999999
 NOCACHE
 NOCYCLE;
 
 
 CREATE TABLE GUESTBOOK (
  NO NUMBER(20) NOT NULL,
  NAME VARCHAR2(20) NOT NULL, 
  PASSWORD VARCHAR2(50) NOT NULL,
  MESSAGE VARCHAR2(2000) NOT NULL,
  REG_DATE DATE,
  PRIMARY KEY (NO) 
);

CREATE SEQUENCE GUESTBOOK_SEQ
INCREMENT BY 1
START WITH 1
MAXVALUE 9999999
NOCACHE
NOCYCLE;

select guestbook_seq.nextval from dual;

insert into guestbook values (guestbook_seq.nextval, 
								'����',
 								'1234',
 								'��������',
								sysdate);
								
select * from guestbook;
   
delete from guestbook;   


	
	 CREATE TABLE BULLETINBOARD (
  b_no NUMBER(20) NOT NULL,
  memberno VARCHAR2(20) NOT NULL, 
  b_title VARCHAR2(50) NOT NULL,
  name VARCHAR2(50) NOT NULL,
  content VARCHAR2(2000) NOT NULL,
  reg_date DATE,
  hit NUMBER(38) NOT NULL,
  
  PRIMARY KEY (b_no) 
);

CREATE SEQUENCE bulletin_no_seq
INCREMENT BY 1
START WITH 1
MAXVALUE 9999999
NOCACHE
NOCYCLE;
	