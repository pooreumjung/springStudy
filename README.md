# first commit Readme
스프링부트 + Thymeleaf + JPA + Spring Security로 회원가입, CRUD게시판(댓글, 좋아요 기능 추가), 로그인 구현

# 기본 로직
##✏️  필요한 기능

  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1.회원가입<br/>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.회원가입<br/>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.로그인<br/>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.게시글 등록<br/>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.게시글 조회<br/>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6,게시글 삭제<br/>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.게시글 수정하기<br/>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;8.댓글 달기<br/>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;9.댓글 내역 보기<br/>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;10.좋아요 기능<br/>

✏️  필요한 패키지

  service 패키지
  repository 패키지
  controller 패키지
  domain 패키지
  dto 패키지
  config 패키지

✏️  데이터베이스

  Member(유저)
    id ⇒ primary key, 유저의 id는 중복되면 안됨
    name ⇒ 문자열
    password ⇒ 문자열
    role ⇒ 권한 ADMIN OR USER

  Board(게시판)
    idx ⇒ primary key, 게시글 번호도 중복되면 안됨
    title ⇒ 제목
    content ⇒ 내용
    memberId ⇒ 게시글 작성자
    likeCount ⇒ 게시글 좋아요 수

  Comment(댓글)
    idx ⇒ primary key, 별 의미 없음
    boardIdx ⇒ 댓글이 달릴 게시글 번호
    content ⇒ 댓글 내용
    memberId ⇒ 댓글 작성자
    
  Likes(좋아요 관리)
    idx ⇒ primary key, 별 의미 없음
    boardIdx ⇒ 좋아요를 누른 게시글 번호
    memberId ⇒ 좋아요를 누른 사람

✏️ 데이터베이스 관계도

  Member와 Board는 1 : N 관계
  Board와 Comment는 1 : N 관계
  Board와 Likes는 1 : N 관계
  Member와 Likes는 1 : N 관계


✏️  회원가입
1. 기능

  유저의 아이디는 중복될 수 없음
  비밀번호 입력과 확인 비밀번호 입력 기능
  유저의 이름 입력 가능
  유저의 권한은 자동으로 USER로 부여

2. 주의할 점

  비밀번호와 비밀번호 확인 입력값이 다르면 회원가입 불가능
  아이디가 중복되면 회원가입 불가능
  회원가입시 비밀번호는 암호화 해서 db에 저장

✏️   로그인
  
  아이디와 비밀번호 입력
  아이디가 일치하지 않으면 로그인 불가
  아이디가 일치하지만 비밀번호가 다르면 로그인 불가
 
✏️  메인 화면

  회원들이 쓴 게시물을 볼 수 있음
  자신이 쓴 게시물은 수정과 삭제가 가능함
  게시물에 좋아요를 누를 수 있음
  게시물에 댓글을 달기 가능
  각 게시물에 달린 댓글을 댓글 보기를 통해 볼 수 있음
  게시물을 쓸 수 있음

✏️  게시물 쓰기

  제목 입력 가능
  내용 입력 가능
  작성자는 알아서 화면에 출력됨
  
✏️  게시물 수정하기

  메인 화면에서 수정하기 버튼을 누를 시 화면에 게시물의 번호, 제목, 내용, 작성자가 출력
  내용만 수정할 수 있음

✏️  댓글 달기

  화면에선 자신이 댓글을 달 게시물이 출력
  댓글은 내용만 등록이 가능

✏️  댓글 보기

  게시물에 달린 댓글들을 볼 수 있음
  댓글이 달린 게시글의 번호, 내용과, 작성자가 출력됨

✏️  삭제하기 && 좋아요 누르기

  삭제하기 버튼을 누르면 화면은 변하지 않고 게시물만 삭제됨
  좋아요는 중복해서 누를 수 없음
  인스타그램에서 좋아요를 한 번누르면 인식되고 두 번누르면 취소되는 것을 생각하면 됨
  출처: https://pooreumjung.tistory.com/489 [푸으름:티스토리]
