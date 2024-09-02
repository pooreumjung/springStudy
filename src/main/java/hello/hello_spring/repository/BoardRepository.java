package hello.hello_spring.repository;

import hello.hello_spring.domain.board.Board;
import hello.hello_spring.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    long countByIdxAndMemberId(Long idx, String memberId);
}
