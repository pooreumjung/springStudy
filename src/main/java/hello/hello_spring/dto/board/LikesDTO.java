package hello.hello_spring.dto.board;

import hello.hello_spring.domain.board.Board;
import hello.hello_spring.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class LikesDTO {
    private Member member;
    private Board board;
}
