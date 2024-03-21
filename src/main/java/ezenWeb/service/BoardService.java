package ezenWeb.service;

import ezenWeb.model.entity.BoardEntity;
import ezenWeb.model.repository.BoardEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    //*리포지토리 객체
    @Autowired private BoardEntityRepository boardEntityRepository;
    @Transactional
    public boolean postBoard(){
        System.out.println("BoardService.postBoard");
        //1. 엔티티 객체 생성
        BoardEntity boardEntity = BoardEntity.builder()
                .bno(1)
                .btitle("JPA테스트중")
                .build();
        //2. 리포지토리를 이용한 엔티티를 테이블에 대입
        boardEntityRepository.save(boardEntity); //insert
        System.out.println("boardEntity = " + boardEntity);

        return false;
    }
    @Transactional
    public List<Object> getBoard(){
        //1. 리포지토리를 이용한 모든 엔티티를 호출
        List<BoardEntity> result = boardEntityRepository.findAll();
        System.out.println("result = " + result);
        return null;
    }
    @Transactional
    public boolean putBoard(){
        BoardEntity boardEntity = boardEntityRepository.findById(1).get();
        boardEntity.setBtitle("JPA수정테스트중");
        return false;
    }
    @Transactional
    public boolean deleteBoard(){
        boardEntityRepository.deleteById(1);
        return false;
    }
}
