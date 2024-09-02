package hello.hello_spring.service.interfaces;

import hello.hello_spring.domain.board.Board;
import hello.hello_spring.domain.board.Comment;
import hello.hello_spring.dto.board.CommentPostDTO;

import java.util.List;

public interface CommentService {
    void save(CommentPostDTO postDTO,String id,Integer idx);
    List<Comment> getCommentList(Board board);
}
