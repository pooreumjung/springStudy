package hello.hello_spring.service;

import hello.hello_spring.domain.board.Board;
import hello.hello_spring.domain.board.Comment;
import hello.hello_spring.dto.board.CommentPostDTO;
import hello.hello_spring.repository.BoardRepository;
import hello.hello_spring.repository.CommentRepository;
import hello.hello_spring.service.interfaces.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Override
    public List<Comment> getCommentList(Board board) {
        return commentRepository.findByBoardIdx(board);
    }

    @Override
    public void save(CommentPostDTO postDTO,String id, Integer idx) {
        Optional<Board>boardOptional = boardRepository.findById(idx.longValue());
        if(boardOptional.isPresent()) {
            Comment comment = Comment.builder()
                    .boardIdx(boardOptional.get()).content(postDTO.getContent()).memberId(id).build();
            commentRepository.save(comment);
        }
    }
}
