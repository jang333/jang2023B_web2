package ezenWeb.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor @NoArgsConstructor
@Setter @Getter @ToString @SuperBuilder
public class PageDto {

    private int page; //현재페이지
    private int count; //총 페이지수
    private List<Object> data; //본문내용들

}
