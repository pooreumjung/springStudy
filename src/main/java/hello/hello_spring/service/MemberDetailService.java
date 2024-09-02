package hello.hello_spring.service;

import groovy.util.logging.Slf4j;
import hello.hello_spring.domain.member.Member;
import hello.hello_spring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private static final Logger log = LoggerFactory.getLogger(MemberServiceImp.class);

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<Member> memberEntity = memberRepository.findById(id);
        Member member = memberEntity.get();
        return new MemberDetail(member);
    }
}
