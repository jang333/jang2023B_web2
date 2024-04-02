package ezenWeb.model.dto;

import ezenWeb.model.entity.BoardEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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

    //1. 출력용 게시물 이미지 필드 (파일이름만 여러개 출력 => 타입 : String)
    private List<String> bimgList = new ArrayList<>();

    //2. 등록용 게시물 이미지 필드 (JS -- multipart/Form(바이트) --> SPRING )
    private List<MultipartFile> uploadList = new ArrayList<>();



    //글쓰기
    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .bcontent(this.bcontent)
                .build();
    }



}
