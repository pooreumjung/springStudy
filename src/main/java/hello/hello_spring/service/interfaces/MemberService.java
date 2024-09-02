package hello.hello_spring.service.interfaces;

import hello.hello_spring.dto.member.LoginDTO;
import hello.hello_spring.dto.member.SignUpDTO;


public interface MemberService {
    boolean checkMemberExist(String id);
    void save(SignUpDTO signUpDTO);
    boolean checkLoginSamePassword(LoginDTO loginDTO);

    boolean checkSignUpSamePassword(SignUpDTO signUpDTO);
}
