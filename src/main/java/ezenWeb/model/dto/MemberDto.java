package ezenWeb.model.dto;

import ezenWeb.model.entity.BoardEntity;
import ezenWeb.model.entity.MemberEntity;
import ezenWeb.model.entity.ReplyEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor @NoArgsConstructor
@Setter @Getter @ToString @SuperBuilder
public class MemberDto extends BaseTimeDto {

    private int mno;
    private String memail;
    private String mpassword;
    private String mname;
    private String mrol;


    // - dto를 엔티티로 변환하는 메소드 //저장
    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .mno(this.mno)
                .mname(this.mname)
                .mpassword(this.mpassword)
                .memail(this.memail)
                .mrol(this.mrol)
                .build();
    }
}
