package ezenWeb.model.entity;

import ezenWeb.model.dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@AllArgsConstructor @NoArgsConstructor
@Setter @Getter @ToString @Builder
public class MemberEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mno;

    @Column(length = 50, unique = true)
    private String memail;

    @Column(length = 255)
    private String mpassword;

    @Column(length = 20, nullable = false)
    private String mname;

    @Column(name = "mrol")
    @ColumnDefault("'user'")
    private String mrol;

    //양방향 : 게시물 fk @OneToMany(mappedBy = "해당테이블 fk자바필드명")
    @OneToMany(mappedBy = "memberEntity") //자바에서만 양방향
    @ToString.Exclude // 해당 객체 호출시 해당 필드는 호출하지 않는다.
    @Builder.Default //빌더패턴 사용시 해당 필드의 초기값을 빌더 초기값으로 사용
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    //양방향 : 댓글 fk
    @OneToMany(mappedBy = "memberEntity")
    @ToString.Exclude
    @Builder.Default
    private List<ReplyEntity> replyEntityList = new ArrayList<>();

    // - 엔티티를 dto로 변환하는 메소드 //호출
    public MemberDto toDto(){
        return MemberDto.builder()
                .mno(this.mno)
                .mname(this.mname)
                .memail(this.memail)
                .mrol(this.mrol)
                .build();
    }
}
