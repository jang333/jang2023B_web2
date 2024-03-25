package ezenWeb.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reply")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString @Builder
public class ReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rno;
    private String rcontent;

    //FK필드
    @JoinColumn(name = "bno_fk")
    @ManyToOne
    private BoardEntity boardEntity;

    @JoinColumn(name = "mno_fk")
    @ManyToOne
    private MemberEntity memberEntity;
}
