package hello.hello_spring.dto.board;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class ListDTO {
    private Long idx;
    private String title;
    private String content;
    private String memberId;
}
