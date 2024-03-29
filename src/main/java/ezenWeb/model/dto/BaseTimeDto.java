package ezenWeb.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder
public class BaseTimeDto {
    public LocalDateTime cdate;
    public LocalDateTime udate;

}
