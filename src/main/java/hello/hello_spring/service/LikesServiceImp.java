package hello.hello_spring.service;

import groovy.util.logging.Slf4j;
import hello.hello_spring.domain.board.Board;
import hello.hello_spring.domain.board.Likes;
import hello.hello_spring.domain.member.Member;
import hello.hello_spring.repository.BoardRepository;
import hello.hello_spring.repository.LikesRepository;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.service.interfaces.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikesServiceImp implements LikesService {

    @Transactional
    @Override
    public void minusLike(Integer idx, String memberId) {
        Optional<Board> boardOptional = boardRepository.findById(idx.longValue());
        Board board = boardOptional.get();
        if(board.getLikeCount()>=1)
        board.setLikeCount(board.getLikeCount() - 1);
        boardRepository.save(board);
        likesRepository.deleteByMemberIdAndBoardIdx(memberId,board);
    }

    @Override
    public void addLike(Integer idx,String memberId) {
        Optional<Board> boardOptional = boardRepository.findById(idx.longValue());
        Board board = boardOptional.get();
        board.setLikeCount(board.getLikeCount() + 1);
        boardRepository.save(board);

        Likes likes = Likes.builder()
                .boardIdx(board)
                .memberId(memberId)
                .build();
        likesRepository.save(likes);
    }

    private final LikesRepository likesRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Override
    public boolean checkBoardExist(Integer idx) {
        Optional<Board> boardOptional = boardRepository.findById(Long.valueOf(idx));
        if (boardOptional.isEmpty())
            return false;
        else
            return true;
    }

    @Override
    public boolean checkMemberIdExist(String memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (memberOptional.isEmpty())
            return false;
        else
            return true;
    }

    @Override
    public boolean checkLikeExist(Integer idx, String memberId) {
        Optional<Board> boardOptional = boardRepository.findById(Long.valueOf(idx));
        Board board = boardOptional.get();
        List<Likes> likesList = likesRepository.findByMemberIdAndBoardIdx(memberId, board);
        if(likesList.isEmpty()){
            return false;
        }
        else
            return true;
    }
}
