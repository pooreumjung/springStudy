package hello.hello_spring.service.interfaces;

public interface LikesService {
    boolean checkLikeExist(Integer idx,String memberId);
    boolean checkBoardExist(Integer idx);
    boolean checkMemberIdExist(String memberId);
    void addLike(Integer idx,String memberId);
    void minusLike(Integer idx, String memberId);
}
