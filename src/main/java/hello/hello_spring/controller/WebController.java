package hello.hello_spring.controller;

import hello.hello_spring.domain.board.Board;
import hello.hello_spring.domain.board.Comment;
import hello.hello_spring.service.interfaces.BoardService;
import hello.hello_spring.service.interfaces.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final BoardService boardService;
    private final CommentService commentService;
    private static final Logger log = LoggerFactory.getLogger(WebController.class);

    @GetMapping("/") // 메인화면
    public String index(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        Optional<Object> idOptional =Optional.ofNullable(session.getAttribute("userId"));
        if(idOptional.isEmpty()){
            return "home";
        }

        return "redirect:/member/list";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        Optional<Object> idOptional =Optional.ofNullable(session.getAttribute("userId"));
        if(idOptional.isPresent()){
            return "redirect:/member/list";
        }
        return "login";
    }

    @GetMapping("/signup") // 회원가입
    public String signup(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        Optional<Object> idOptional =Optional.ofNullable(session.getAttribute("userId"));
        if(idOptional.isPresent()){
            return "redirect:/member/list";
        }
        return "signup";
    }

    @GetMapping("/member/list")
    public String memberList(Model model, HttpServletRequest httpServletRequest) {

        HttpSession session = httpServletRequest.getSession();
        Optional<Object> idOptional =Optional.ofNullable(session.getAttribute("userId"));
        if(idOptional.isEmpty()){
            log.error("권한 없음");
            return "redirect:/login";
        }
        String id = idOptional.get().toString();
        List<Board> boardList = boardService.getAllBoardList();
        model.addAttribute("boardList", boardList);
        model.addAttribute("id", id);
        log.info("리스트 출력완료");
        return "list";
    }

    @GetMapping("/member/post")
    public String post(Model model, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        Optional<Object> idOptional = Optional.ofNullable(session.getAttribute("userId"));
        if(idOptional.isEmpty()){
            log.error("글을 쓸려면 로그인을 해야함");
            return "redirect:/login";
        }
        String id = idOptional.get().toString();
        model.addAttribute("id", id);
        return "post";
    }

    @GetMapping("/member/modify/{idx}") // 게시글 수정 화면 넘어가기
    public String modify(Model model, HttpServletRequest httpServletRequest, @PathVariable("idx")Long idx) {
        HttpSession session = httpServletRequest.getSession(false);
        Optional<Object> idOptional = Optional.ofNullable(session.getAttribute("userId"));
        if(idOptional.isEmpty()){
            log.error("게시글을 수정할 아이디가 없음");
            return "redirect:/login";
        }
        log.error(idx.toString());
        Optional<Board> boardOptional = boardService.getModifyBoard(idx);
        if(boardOptional.isEmpty()){
            log.error("게시물이 존재하지 않음");
            return "redirect:/member/list";
        }
        Board board = boardOptional.get();
        if(!idOptional.get().equals(board.getMember().getId())){
            return "redirect:/member/list";
        }
        model.addAttribute("board", board);
        return "modify";
    }

    @GetMapping("/member/comment/{idx}")
    public String comment(@PathVariable("idx") int idx, Model model, HttpServletRequest httpServletRequest) {

        Optional<Board>boardOptional = boardService.getBoardByIdx(idx);
        if(boardOptional.isEmpty()){
            log.error("존재하지 않음");
            return "redirect:/member/list";
        }
        Board board = boardOptional.get();
        HttpSession session = httpServletRequest.getSession();
        Optional<Object> idOptional = Optional.ofNullable(session.getAttribute("userId"));
        if(idOptional.isEmpty()){
            log.error("로그인 되어 있지 않음");
            return "redirect:/member/login";
        }
        String author = idOptional.get().toString();


        model.addAttribute("board",board);
        model.addAttribute("user",board.getMember().getId());
        model.addAttribute("author",author);
        return "comment";
    }

    @GetMapping("/member/comment/{idx}/view")
    public String viewComment(@PathVariable("idx") int idx, Model model, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        Optional<Object> idOptional = Optional.ofNullable(session.getAttribute("userId"));
        if(idOptional.isEmpty()){
            log.error("댓글을 볼려면 로그인을 해야함");
            return "redirect:/member/login";
        }

        Optional<Board>boardOptional = boardService.getBoardByIdx(idx);
        if(boardOptional.isEmpty()){
            log.error("댓글을 볼 게시물이 존재하지 않음");
            return "redirect:/member/list";
        }
        Board board = boardOptional.get();
        log.error(board.toString());
        List<Comment> comments = commentService.getCommentList(board);

        model.addAttribute("comments", comments);
        model.addAttribute("board", board);
        return "view";
    }

}
