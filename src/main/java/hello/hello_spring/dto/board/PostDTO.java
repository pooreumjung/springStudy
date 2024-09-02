package hello.hello_spring.dto.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PostDTO {
    private String id;
    private String title;
    private String content;
}
