package hello.hello_spring.domain.board;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "boardIdx", nullable = false)
    private Board boardIdx;

    @Column(name = "content", nullable = false, length = 20)
    private String content;

    @Column(name = "memberId", nullable = false)
    private String memberId;

}