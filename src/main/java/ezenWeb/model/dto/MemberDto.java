package ezenWeb.model.dto;

import ezenWeb.model.entity.BoardEntity;
import ezenWeb.model.entity.MemberEntity;
import ezenWeb.model.entity.ReplyEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
                .mname(this.mname)
                //new BCryptPasswordEncoder().encode(암호화 할 데이터)
                /*
                    암호화란 :
                    암호 : 정보를 이해할 수 없도록 => 사람이 이해할 수 없도록
                        - 이해할 수 없도록 자기만의 방법으로 변경
                        - 스프링 시큐리티가 제외하는 방법 종류 : bcrypt 암호화 제공
                 */
                .mpassword(new BCryptPasswordEncoder().encode(this.mpassword))
                .memail(this.memail)
                .build();
    }
}
