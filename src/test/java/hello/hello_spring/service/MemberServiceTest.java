//package hello.hello_spring.service;
//
//import hello.hello_spring.domain.member.Member;
//import hello.hello_spring.repository.MemoryMemberRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//
//// 회원가입 중복 테스트
////class MemberServiceTest {
////
////    MemberService memberService;
////    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
////
////    @BeforeEach
////    public void beforeEach(){
////        memberRepository = new MemoryMemberRepository();
////        memberService = new MemberService(memberRepository); // dependency injection
////    }
////
////    @AfterEach
////    public void afterEach(){
////        memberRepository.clearStore();
////    }
////
////    @Test
////    void 회원가입() { // 테스트는 과감하게 한글로 만들어도 된다
////        // given, 검증 데이터 기반
////        Member member = new Member();
////        member.setName("spring");
////
////        // when, 검증하는 구간
////        Long saveId = memberService.join(member);
////
////        // then, 검증 구간
////        Member findMember = memberService.findOne(saveId).get();
////        assertThat(member.getName()).isEqualTo(findMember.getName());
////    }
////
////    @Test
////    public void 중복_회원_예외(){
////        // given
////        Member member1 = new Member();
////        member1.setName("spring");
////
////        Member member2 = new Member();
////        member2.setName("spring");
////
////        // when
////        memberService.join(member1);
////        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
////        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
////
////
//////        try{
//////            memberService.join(member2);
//////            fail();
//////        } catch (IllegalStateException e){
//////            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//////        }
////
////        // then
////    }
////
////    @Test
////    void findMembers() {
////    }
////
////    @Test
////    void findOne() {
////    }
////}
//
//class MemberServiceTest { // 순수하게 자바 코드로 돌리는 테스트를 단위 테스트 => 좋은 테스트일 확롤이 높다
//    MemberServiceImpl memberService;
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
//
//    @BeforeEach
//    public void beforeEach() {
//        memberRepository = new MemoryMemberRepository();
//        memberService = new MemberServiceImpl(memberRepository);
//    }
//
//    @AfterEach
//    public void afterEach() {
//        memberRepository.clearStore();
//    }
//
//
//    @Test
//    void 회원가입() { // 테스트는 과감하게 한글로 만들어도 된다
//        // given, 검증 데이터 기반
//        Member member = new Member();
//        member.setName("spring");
//
//        // when, 검증하는 구간
//        Long saveId = memberService.join(member);
//
//        // then, 검증 구간
//        Member findMember = memberService.findOne(saveId).get();
//        assertThat(member.getName()).isEqualTo(findMember.getName());
//    }
//
//
//    @Test
//    public void 중복_회원_예외() {
//
//        // given
//        Member member1 = new Member();
//        member1.setName("spring");
//
//        Member member2 = new Member();
//        member2.setName("spring");
//
//        //when
//        memberService.join(member1);
//        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//    }
//}