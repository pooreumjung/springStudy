package hello.hello_spring.service;

import groovy.util.logging.Slf4j;
import hello.hello_spring.domain.member.Member;
import hello.hello_spring.domain.member.MemberRole;
import hello.hello_spring.dto.member.LoginDTO;
import hello.hello_spring.dto.member.SignUpDTO;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
@Slf4j
public class MemberServiceImp implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberDetailService memberDetailService;

    private static final Logger log = LoggerFactory.getLogger(MemberServiceImp.class);

    @Override
    public boolean checkMemberExist(String id) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if(memberOptional.isPresent()){
            log.error("동일한 아이디가 존재");
            return true;
        }
        else {
            log.info("동일한 아이디 없음");
            return false;
        }
    }

    @Override
    public boolean checkSignUpSamePassword(SignUpDTO signUpDTO) {
        if (!(signUpDTO.getConfirmPassword()).equals(signUpDTO.getPassword())) {
            log.error("비밀번호를 다시 입력해 주세요");
            return false;
        }
        else {
            log.info("비빌먼호 일치");
            return true;
        }
    }

    @Override
    public void save(SignUpDTO signUpDTO) {

        String encodingPassword = passwordEncoder.encode(signUpDTO.getPassword());
        signUpDTO.setPassword(encodingPassword);
        log.info("로그인한 ID{}", signUpDTO.getId());

        Member member = Member.builder()
                .id(signUpDTO.getId())
                .name(signUpDTO.getName())
                .password(encodingPassword)
                .role(MemberRole.USER)
                .build();
        memberRepository.save(member);
    }

    @Override
    public boolean checkLoginSamePassword(LoginDTO loginDTO) {
        UserDetails member = memberDetailService.loadUserByUsername(loginDTO.getId());
        if (!(passwordEncoder.matches(loginDTO.getPassword(), member.getPassword()))) {
            log.error("비밀번호가 일치하지 않습니다.");
            return false;
        }
        return true;
    }
}
