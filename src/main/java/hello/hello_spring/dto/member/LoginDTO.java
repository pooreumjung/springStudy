package hello.hello_spring.dto.member;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter

public class LoginDTO {
    private String id;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
}
