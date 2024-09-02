package hello.hello_spring.domain.member;

import hello.hello_spring.domain.board.Board;
import jakarta.persistence.*;
import lombok.*;


import java.util.*;

@Getter
@Entity
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Member {

    @Id
    @Column(name = "id", nullable = false, length = 20)
    private String id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private MemberRole role;



}