package ezenWeb.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "boardimg")
@AllArgsConstructor @NoArgsConstructor
@Setter @Getter @ToString @Builder
public class BoardImgEntity {

    @Id
    private String bimg; //파일경로이름

    //단방향 설정
    @ManyToOne
    @JoinColumn(name = "bno_fk")
    private BoardEntity boardEntity; //fk


}
