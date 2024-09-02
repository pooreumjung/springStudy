//package hello.hello_spring.repository;
//
//import hello.hello_spring.domain.User;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//
//public class MemoryUserRepositoryTest {
//    MemoryUserRepository repository = new MemoryUserRepository();
//
//    @AfterEach
//    public void afterEach(){
//        repository.clearStore();
//    }
//
//    @Test
//    public void save(){
//        User user = new User();
//        user.setName("spring");
//
//        repository.save(user);
//        User result = repository.findByID(user.getId()).get();
//        assertThat(user).isEqualTo(result);
//    }
//
//    @Test
//    public void findByName(){
//        User user1 = new User();
//        user1.setName("spring1");
//        repository.save(user1);
//
//        User user2 = new User();
//        user2.setName("spring2");
//        repository.save(user2);
//
//        User result = repository.findByName("spring1").get();
//        assertThat(result).isEqualTo(user1);
//    }
//
//    @Test
//    public void findByPassword(){
//        User user1 = new User();
//        user1.setPassword("spring1");
//        repository.save(user1);
//
//        User user2 = new User();
//        user2.setPassword("spring2");
//        repository.save(user2);
//
//        User result = repository.findByPassword("spring1").get();
//        assertThat(result).isEqualTo(user1);
//    }
//
//    @Test
//    public void findByPhone(){
//        User user1 = new User();
//        user1.setPhone("spring1");
//        repository.save(user1);
//
//        User user2 = new User();
//        user2.setPhone("spring2");
//        repository.save(user2);
//
//        User result = repository.findByPhone("spring1").get();
//        assertThat(result).isEqualTo(user1);
//    }
//
//    @Test
//    public void findAll(){
//        User user1 = new User();
//        user1.setName("spring1");
//        repository.save(user1);
//
//        User user2 = new User();
//        user2.setName("spring2");
//        repository.save(user2);
//
//        List<User> result = repository.findByAll();
//        assertThat(result.size()).isEqualTo(2);
//    }
//
//}
