//package hello.hello_spring.repository;
//
//import hello.hello_spring.domain.member.Member;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
////class MemoryMemberRepositoryTest {
////
////    // 모든 테스트는 테스트 순서에 상관없이 다 작동하도록 설계해야함 -> 순서 의존도를 가지면 됨
////
////    MemoryMemberRepository repository = new MemoryMemberRepository();
////    // 테스트 클래스를 먼저 작성후 멤버 리포지토리를 작성하기, ex) 별 모얄 작품을 만들 때 미리 검증할 수 있는 틀을 만들고 나중에 잘 맞는기 확인해보기 => 테스트 주도 개발(TDD), 여기서는 tdd는 아님)
////    // 많은 테스트를 한 번에 돌리기 => gradlew test입력
////
////    @AfterEach // 테스트가 끝날 때마다 어떤 동작을 하도록 설정해줌, 이걸 하지 않으면 테스트를 돌릴 때마다 다른 객체값이 테스트 메소드 안에 들어가져서 오류가 발생할 수 있음
////    public void afterEach(){
////        repository.clearStore();
////    }
////
////
////    @Test // 이거를 입력하면 이제부터 테스트케이스를 실행할 수 있음
////    public void save() {
////        Member member = new Member();
////        member.setName("spring");
////
////        repository.save(member);
////        Member result = repository.findById(member.getId()).get(); // get으로 바로 꺼내는 것이 좋은 방법이 아니다
////        assertThat(member).isEqualTo(result); // 테스트케이스에서 통과하지 않으면 그 다음 단계로 넘어가지 못하게 막아버린다
////    }
////
////    @Test
////    public void findByName () {
////        Member member1 = new Member();
////        member1.setName("spring1");
////        repository.save(member1);
////
////        Member member2 = new Member();
////        member2.setName("spring2");
////        repository.save(member2);
////
////        Member result = repository.findByName("spring1").get();
////        assertThat(result).isEqualTo(member1);
////    }
////
////    @Test
////    public void findAll(){
////        Member member1 = new Member();
////        member1.setName("spring1");
////        repository.save(member1);
////
////        Member member2 = new Member();
////        member2.setName("spring2");
////        repository.save(member2);
////
////        List<Member> result = repository.findAll();
////        assertThat(result.size()).isEqualTo(2);
////    } // 테스트가 끝날 떄마다 repository 데이터를 클리어 해줘야 한다, 안 그러면 에러뜸
////}
//
//class MemoryMemberRepositoryTest {
//    MemoryMemberRepository repository = new MemoryMemberRepository();
//
//    @AfterEach// 테스트케이스가 끝날 때마다 어떤 동작을 하도록 설정, 이걸 하지 않으면 테스트를 돌릴 때마다 다른 객체값이 테스트 메소드 안에 들어가져서 오류 발생
//    public void afterEach(){
//        repository.clearStore();
//    }
//
//    @Test // 이걸 써야지 테스트케이스를 실행할 수 있음
//    public void save(){
//        Member member = new Member();
//        member.setName("spring");
//
//        repository.save(member);
//        Member result = repository.findById(member.getId()).get(); // get으로 바로 꺼내는 것이 좋은 방법이 아니다
//        assertThat(member).isEqualTo(result); // 테스트케이스에서 통과하지 않으면 그 다음 단계로 넘어가지 못하게 막아버린다
//    }
//
//    @Test
//    public void findByName(){
//        Member member1 = new Member();
//        member1.setName("spring1");
//        repository.save(member1);
//
//        Member member2 = new Member();
//        member2.setName("spring2");
//        repository.save(member2);
//
//        Member result = repository.findByName("spring1").get();
//        assertThat(result).isEqualTo(member1);
//    }
//
//    @Test
//    public void findAll(){
//        Member member1 = new Member();
//        member1.setName("spring1");
//        repository.save(member1);
//
//        Member member2 = new Member();
//        member2.setName("spring2");
//        repository.save(member2);
//
//        List<Member> result = repository.findAll();
//        assertThat(result.size()).isEqualTo(2);
//    } // 테스트가 끝날 때마다 repository 데이터를 클리어 해줘야 한다, 안 그러면 에어 뜸
//
//}