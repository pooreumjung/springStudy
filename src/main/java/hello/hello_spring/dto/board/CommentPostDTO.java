package hello.hello_spring.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentPostDTO {
        //private Integer idx;
        private String content;
        // private String author;
}
