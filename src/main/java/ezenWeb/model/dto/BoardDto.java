package ezenWeb.model.dto;

import ezenWeb.model.entity.BoardEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder //부모필드까지 적용
public class BoardDto extends BaseTimeDto {

    private int bno;
    private String bcontent;
    private int bview;
    private int mno_fk;     //(memberEntity) 회원번호
    private String memail;  //(memberEntity) 회원이메일


    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .bcontent(this.bcontent)
                .build();
    }



}
