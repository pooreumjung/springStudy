package hello.hello_spring.repository;

import hello.hello_spring.domain.board.Board;
import hello.hello_spring.domain.board.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    void deleteByBoardIdx(Board board);
    List<Comment> findByBoardIdx(Board board);
}
