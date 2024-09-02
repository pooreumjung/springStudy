package hello.hello_spring.controller;

import groovy.util.logging.Slf4j;
import hello.hello_spring.dto.board.CommentPostDTO;
import hello.hello_spring.dto.board.PostDTO;
import hello.hello_spring.dto.board.UpdateDTO;
import hello.hello_spring.dto.member.LoginDTO;
import hello.hello_spring.dto.member.SignUpDTO;
import hello.hello_spring.service.interfaces.BoardService;
import hello.hello_spring.service.interfaces.CommentService;
import hello.hello_spring.service.interfaces.LikesService;
import hello.hello_spring.service.interfaces.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final MemberService memberService;
    private final BoardService boardService;
    private final CommentService commentService;
    private final LikesService likesService;
    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    @PostMapping("/signup")
    public String signup(SignUpDTO signUpDTO) {
        boolean isMemberExist = memberService.checkMemberExist(signUpDTO.getId());
        if (isMemberExist) { // 안 되었으므로 다시 회원가입 화면
            log.error("회원가입 실패");
            return "redirect:/signup";
        }

        boolean isSignUpSamePassword = memberService.checkSignUpSamePassword(signUpDTO);
        if(!isSignUpSamePassword) {
            log.error("회원가입 실패");
            return "redirect:/signup";
        }
        memberService.save(signUpDTO);
        log.info("signup success");
        return "redirect:/login";


    }

    @PostMapping("/login")
    public String login(LoginDTO loginDTO, HttpServletRequest httpServletRequest) {
        boolean isMemberExist = memberService.checkMemberExist(loginDTO.getId());
        if (!isMemberExist) {
            log.error("아이디가 존재하지 않음");
            return "redirect:/login";
        }
        boolean isLoginSamePassword = memberService.checkLoginSamePassword(loginDTO);
        if (!isLoginSamePassword) {
            log.error("비밀번호가 일치하지 않음");
            return "redirect:/login";
        }


        log.info("login success");

        httpServletRequest.getSession().invalidate();
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("userId", loginDTO.getId());
        session.setMaxInactiveInterval(1800);
        log.info("세션 생성");
        return "redirect:/member/list";

    }

    @PostMapping("/logout") // 로그아웃
    public String logout(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            log.info("세션 ID 삭제");
            session.invalidate();
        }

        log.info("로그아웃");
        return "redirect:/";
    }

    @PostMapping("/post") // 글 작성하기
    public String post(HttpServletRequest httpServletRequest, PostDTO postDTO) {
        HttpSession session = httpServletRequest.getSession(false);
        Optional<Object> idOptional = Optional.ofNullable(session.getAttribute("userId"));
        if (idOptional.isEmpty()) {
            log.error("로그인을 하지 않으면 글을 작성할 수 없습니다.");
            return "redirect:/login";
        }

        String id = idOptional.get().toString();
        boardService.save(postDTO, id);
        log.info("성공적으로 업로드 되었습니다. 리스트 화면으로 돌아갑니다.");
        return "redirect:/member/list";
    }

    @PostMapping("/delete") // 글 삭제하기
    public String delete(HttpServletRequest httpServletRequest, @RequestParam("idx") Long idx) {

        HttpSession session = httpServletRequest.getSession(true);
        Optional<Object> idOptional = Optional.ofNullable(session.getAttribute("userId"));
        if (idOptional.isEmpty()) {
            log.error("로그인을 하지 않으면 글을 삭제할 수 없습니다.");
            return "redirect:/login";
        }
        boardService.delete(idx);
        log.info("게시물 삭제");
        return "redirect:/member/list";
    }

    @PostMapping("/update") // 글 수정하기
    public String update(UpdateDTO updateDTO, HttpServletRequest httpServletRequest) {

        HttpSession session = httpServletRequest.getSession(false);
        Optional<Object> idOptional = Optional.ofNullable(session.getAttribute("userId"));
        if (idOptional.isEmpty()) {
            log.error("로그인을 하지 않으면 글을 수정할 수 없습니다.");
            return "home";
        }

        String id = idOptional.get().toString();
        boolean isBoardExist = boardService.checkBoardExist(updateDTO.getIdx());

        if (!isBoardExist) { // 게시물 수정 불가능
            log.error("게시글이 존재하지 않습니다.");
            return "redirect:/member/list";
        }

        boolean isOwner = boardService.checkIsOwner(updateDTO.getIdx(),id);
        if(!isOwner){
            log.error("주인이 아닙니다.");
            return "redirect:/member/list";
        }

        boardService.update(updateDTO);
        log.info("글이 수정되었습니다.");
        return "redirect:/member/list";
    }

    @PostMapping("/comment/{idx}/post")
    public String commentPost(@PathVariable("idx") Integer idx, CommentPostDTO postDTO, HttpServletRequest
            httpServletRequest) {

        HttpSession session = httpServletRequest.getSession(false);
        Optional<Object> idOptional = Optional.ofNullable(session.getAttribute("userId"));
        if (idOptional.isEmpty()) {
            log.error("권한 없음");
            return "redirect:/member/login";
        }
        String id = idOptional.get().toString();
        boolean isBoardExistCheck = boardService.checkBoardExist(idx.longValue());
        if (!isBoardExistCheck) {
            log.error("게시물이 존재하지 않음");
            return "redirect:/member/list";
        }

        boolean isMemberExist = memberService.checkMemberExist(id);
        if (!isMemberExist) {
            log.error("멤버에 등록되어 있지 않습니다.");
            return "redirect:/member/login";
        }

        commentService.save(postDTO, id, idx);
        log.info("댓글 업로드");
        return "redirect:/member/list";
    }

    @PostMapping("/board/{idx}/like")
    public String commentLike(@PathVariable("idx") Integer idx, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        Optional<Object> idOptional = Optional.ofNullable(session.getAttribute("userId"));
        if (idOptional.isEmpty()) {
            log.error("좋아요를 누를 권한이 없음");
            return "redirect:/login";
        }

        String memberId = idOptional.get().toString();
        boolean isBoardExistCheck = likesService.checkBoardExist(idx);
        if (!isBoardExistCheck) {
            log.error("게시글이 존재하지 않음");
            return "redirect:/member/list";
        }

        boolean isMemberIdExistCheck = likesService.checkMemberIdExist(memberId);
        if (!isMemberIdExistCheck) {
            log.error("좋아요를 누를 수 있는 권한이 없음");
            return "redirect:/member/list";
        }

        boolean isLikeOverlap = likesService.checkLikeExist(idx, memberId);
        if (isLikeOverlap) { // 좋아요 중복이 아닌 경우
            log.error("좋아요를 중복해서 좋아요 개수 차감");
            likesService.minusLike(idx,memberId);
            return "redirect:/member/list";
        }

        likesService.addLike(idx, memberId);
        log.info("좋아요 성공");
        return "redirect:/member/list";
    }
}



