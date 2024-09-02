package hello.hello_spring.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class UpdateDTO {
    private Long idx;
    private String content;
}
