package hello.hello_spring.repository;

import hello.hello_spring.domain.board.Board;
import hello.hello_spring.domain.board.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    List<Likes> findByMemberIdAndBoardIdx(String memberId, Board board);
    void deleteByMemberIdAndBoardIdx(String memberId, Board board);
    void deleteByBoardIdx(Board board);
}
