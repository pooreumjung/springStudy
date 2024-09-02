# first commit Readme
스프링부트 + Thymeleaf + JPA + Spring Security로 회원가입, CRUD게시판(댓글, 좋아요 기능 추가), 로그인 구현

# 기본 로직
✏️  필요한 기능

  회원가입<br/>
  회원가입<br/>
  로그인<br/>
  게시글 등록<br/>
  게시글 조회<br/>
  게시글 삭제<br/>
  게시글 수정하기<br/>
  댓글 달기<br/>
  댓글 내역 보기<br/>
  좋아요 기능<br/>

✏️  필요한 패키지

  service <br/>
  repository<br/> 
  controller<br/> 
  domain<br/> 
  dto<br/> 
  config<br/> 

✏️  데이터베이스

  Member(유저)<br/>
    id ⇒ primary key, 유저의 id는 중복되면 안됨<br/>
    name ⇒ 문자열<br/>
    password ⇒ 문자열<br/>
    role ⇒ 권한 ADMIN OR USER<br/>

  Board(게시판)<br/>
    idx ⇒ primary key, 게시글 번호도 중복되면 안됨<br/>
    title ⇒ 제목<br/>
    content ⇒ 내용<br/>
    memberId ⇒ 게시글 작성자<br/>
    likeCount ⇒ 게시글 좋아요 수<br/>

  Comment(댓글)<br/>
    idx ⇒ primary key, 별 의미 없음<br/>
    boardIdx ⇒ 댓글이 달릴 게시글 번호<br/>
    content ⇒ 댓글 내용<br/>
    memberId ⇒ 댓글 작성자<br/>
    
  Likes(좋아요 관리)<br/>
    idx ⇒ primary key, 별 의미 없음<br/>
    boardIdx ⇒ 좋아요를 누른 게시글 번호<br/>
    memberId ⇒ 좋아요를 누른 사람<br/>

✏️ 데이터베이스 관계도

  Member와 Board는 1 : N 관계<br/>
  Board와 Comment는 1 : N 관계<br/>
  Board와 Likes는 1 : N 관계<br/>
  Member와 Likes는 1 : N 관계<br/>


✏️  회원가입

  유저의 아이디는 중복될 수 없음<br/>
  비밀번호 입력과 확인 비밀번호 입력 기능<br/>
  유저의 이름 입력 가능<br/>
  유저의 권한은 자동으로 USER로 부여<br/>
  비밀번호와 비밀번호 확인 입력값이 다르면 회원가입 불가능<br/>
  아이디가 중복되면 회원가입 불가능<br/>
  회원가입시 비밀번호는 암호화 해서 db에 저장<br/>

✏️   로그인
  
  아이디와 비밀번호 입력<br/>
  아이디가 일치하지 않으면 로그인 불가<br/>
  아이디가 일치하지만 비밀번호가 다르면 로그인 불가<br/>

 
✏️  메인 화면

  회원들이 쓴 게시물을 볼 수 있음<br/>
  자신이 쓴 게시물은 수정과 삭제가 가능함<br/>
  게시물에 좋아요를 누를 수 있음<br/>
  게시물에 댓글을 달기 가능<br/>
  각 게시물에 달린 댓글을 댓글 보기를 통해 볼 수 있음<br/>
  게시물을 쓸 수 있음<br/>

✏️  게시물 쓰기

  제목 입력 가능<br/>
  내용 입력 가능<br/>
  작성자는 알아서 화면에 출력됨<br/>
  
✏️  게시물 수정하기

  메인 화면에서 수정하기 버튼을 누를 시 화면에 게시물의 번호, 제목, 내용, 작성자가 출력<br/>
  내용만 수정할 수 있음<br/>

✏️  댓글 달기

  화면에선 자신이 댓글을 달 게시물이 출력<br/>
  댓글은 내용만 등록이 가능<br/>

✏️  댓글 보기

  게시물에 달린 댓글들을 볼 수 있음<br/>
  댓글이 달린 게시글의 번호, 내용과, 작성자가 출력됨<br/>

✏️  삭제하기 && 좋아요 누르기

  삭제하기 버튼을 누르면 화면은 변하지 않고 게시물만 삭제됨<br/>
  좋아요는 중복해서 누를 수 없음<br/>
  인스타그램에서 좋아요를 한 번 누르면 인식되고 두 번 누르면 취소되는 것을 생각하면 됨<br/>
  
  https://pooreumjung.tistory.com/489 [푸으름:티스토리]

  <img src="file:///Users/pooreum/Desktop/image/db.png" width="가로 사이즈" height="세로 사이즈">
