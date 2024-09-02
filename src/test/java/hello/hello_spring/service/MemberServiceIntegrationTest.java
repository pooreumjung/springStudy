//package hello.hello_spring.service;
//
//import hello.hello_spring.domain.member.Member;
//import jakarta.transaction.Transactional;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//// 통합 테스트
//@SpringBootTest // 스프링 테스트 시 사용하는 어노테이션
//@Transactional // 테스트를 실행할 때 트렌잭션을 먼저 실행 후, db에 insert릃 하고 테스트가 끝나면 롤백을 해줘서 db에 넣은 데이터를 다시 없애준다, 테스트는 자동 커밋이기 떄문에 주의할 것
//public class MemberServiceIntegrationTest { // 테스트 코드를 만들 때는 제일 편한 방법, autowired
//
//    @Autowired
//    MemberServiceImpl memberService;
//    @Autowired MemberRepository memberRepository;
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
//
//}
