package hello.hello_spring.dto.member;

import lombok.*;

@Getter
@Setter

public class SignUpDTO {
    private String name;
    private String id;
    private String password;
    private String confirmPassword;
}
