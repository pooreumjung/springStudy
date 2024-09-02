package hello.hello_spring.service.interfaces;

import hello.hello_spring.domain.board.Board;
import hello.hello_spring.dto.board.PostDTO;
import hello.hello_spring.dto.board.UpdateDTO;

import java.util.List;
import java.util.Optional;


public interface BoardService {
    void save(PostDTO postDTO,String id);
    void update(UpdateDTO updateDTO);
    void delete(Long idx);
    Optional<Board> getModifyBoard(Long idx);
    boolean checkBoardExist(Long idx);
    boolean checkIsOwner(Long idx, String id);
    List<Board> getAllBoardList();
    Optional<Board> getBoardByIdx(int idx);
}
