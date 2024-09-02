//package hello.hello_spring.service;
//
//import hello.hello_spring.domain.User;
//import hello.hello_spring.repository.MemoryUserRepository;
//import hello.hello_spring.repository.UserRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//public class UserServiceTest {
//    UserService userService;
//    MemoryUserRepository userRepository = new MemoryUserRepository();
//
//    @BeforeEach
//    public void beforeEach(){
//        userRepository = new MemoryUserRepository();
//        userService = new UserService(userRepository);
//    }
//
//    @AfterEach
//    public void afterEach(){
//        userRepository.clearStore();
//    }
//
//    @Test
//    public void 회원가입(){
//        //given
//        User user = new User();
//        user.setName("spring");
//
//        //when
//        Long saveId = userService.join(user);
//
//        // then
//        User findUser = userService.findOne(saveId).get();
//        assertThat(findUser.getName()).isEqualTo(findUser.getName());
//    }
//
//    @Test
//    public void 중복_회원_예외(){
//        //given
//        User user1 = new User();
//        user1.setName("spring");
//
//        User user2 = new User();
//        user2.setName("spring");
//
//        // when
//        userService.join(user1);
//        IllegalStateException e = assertThrows(IllegalStateException.class, () -> userService.join(user2));
//        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//    }
//
//
//}
