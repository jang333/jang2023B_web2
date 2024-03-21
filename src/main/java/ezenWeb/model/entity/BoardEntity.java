package ezenWeb.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "board")
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardEntity {
    @Id //pk키 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int bno;        //게시물번호 pk
    @Column(name = "title", length = 10, nullable = false, unique = true)
    private String btitle;

    private boolean 필드0;

    private byte 필드1;
    private short 필드2;
    private long 필드3;

    private char 필드4;

    private float 필드5;
    private double 필드6;

    private Date 필드7;
    private LocalDateTime 필드8;




}


