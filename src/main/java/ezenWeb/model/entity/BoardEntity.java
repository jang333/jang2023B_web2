package ezenWeb.model.entity;

import ezenWeb.model.dto.BoardDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Board")
@AllArgsConstructor @NoArgsConstructor
@Setter @Getter @ToString @Builder
public class BoardEntity extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;

    @Column(columnDefinition = "longtext")
    private String bcontent;

    @Column//(columnDefinition = "unsigned int") unsigned: 증가만함 음수 없음.
    @ColumnDefault("0")
    private int bview;

    //fk 필드
    @JoinColumn(name = "mno_fk")
    @ManyToOne
    private MemberEntity memberEntity;

    //양방향 : 댓글 fk
    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<ReplyEntity> replyEntityList = new ArrayList<>();

    //양방향 : 사진 fk
    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<BoardImgEntity> boardImgEntityList = new ArrayList<>();

    //게시물출력
    public BoardDto toDto(){
        return BoardDto.builder()
                .bno(this.bno)
                .bcontent(this.bcontent)
                .bview(this.bview)
                .mno_fk(memberEntity.getMno())
                .memail(memberEntity.getMemail())
                .cdate(this.getCdate())
                .udate(this.getUdate())
                .bimgList(
                        this.getBoardImgEntityList().stream().map(
                                (imgEntity) -> {return imgEntity.getBimg();}
                        ).collect(Collectors.toList())
                )
                .build();
    }



//    private boolean 필드0;
//
//    private byte 필드1;
//    private short 필드2;
//    private long 필드3;
//
//    private char 필드4;
//
//    private float 필드5;
//    private double 필드6;
//
//    private Date 필드7;
//    private LocalDateTime 필드8;




}


