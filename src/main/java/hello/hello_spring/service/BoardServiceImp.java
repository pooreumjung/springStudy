package hello.hello_spring.service;

import hello.hello_spring.domain.board.Board;
import hello.hello_spring.domain.member.Member;
import hello.hello_spring.dto.board.PostDTO;
import hello.hello_spring.dto.board.UpdateDTO;
import hello.hello_spring.repository.BoardRepository;
import hello.hello_spring.repository.CommentRepository;
import hello.hello_spring.repository.LikesRepository;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.service.interfaces.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImp implements BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final LikesRepository likesRepository;

    @Override
    public void save(PostDTO postDTO, String id) {
        Optional<Member> member = memberRepository.findById(id); // 작성자의 id가 db에 존재하는지 검사
        if (member.isPresent()) {
            Member memberEntity = member.get();
            Board board = Board.builder()
                    .title(postDTO.getTitle())
                    .content(postDTO.getContent())
                    .member(memberEntity)
                    .likeCount(0)
                    .build();
            boardRepository.save(board);
        }
    }

    @Override
    public List<Board> getAllBoardList() {
        return boardRepository.findAll();
    }

    @Override
    public void update(UpdateDTO updateDTO) {
        Optional<Board> board = boardRepository.findById(updateDTO.getIdx());
        if (board.isPresent()) {
            Board boardEntity = board.get();
            boardEntity.setContent(updateDTO.getContent());
            boardRepository.save(boardEntity);
        }
    }

    @Override
    public Optional<Board> getModifyBoard(Long idx) {
        return boardRepository.findById(idx);
    }

    @Override
    public boolean checkIsOwner(Long idx, String id) {
        long count = boardRepository.countByIdxAndMemberId(idx,id);
        return count != 0;
    }

    @Override
    public Optional<Board> getBoardByIdx(int idx) {
        return boardRepository.findById((long) idx);
    }

    @Transactional
    @Override
    public void delete(Long idx) {
        Optional<Board> board = boardRepository.findById(idx);
        if (board.isPresent()) {
            Board boardEntity = board.get();
            log.error(board.get().toString());
            likesRepository.deleteByBoardIdx(boardEntity);
            commentRepository.deleteByBoardIdx(boardEntity);
            boardRepository.deleteById(idx);
        }
    }

    @Override
    public boolean checkBoardExist(Long idx) {
        Optional<Board> board = boardRepository.findById(idx);
        // 존재하지 않음
        // 존재함
        return board.isPresent();
    }
}
