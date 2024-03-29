package ezenWeb.service;

import ezenWeb.model.dto.BoardDto;
import ezenWeb.model.dto.MemberDto;
import ezenWeb.model.entity.BoardEntity;
import ezenWeb.model.entity.MemberEntity;
import ezenWeb.model.entity.ReplyEntity;
import ezenWeb.model.repository.BoardEntityRepository;
import ezenWeb.model.repository.MemberEntityRepository;
import ezenWeb.model.repository.ReplyEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    //*리포지토리 객체
    @Autowired private BoardEntityRepository boardEntityRepository;
    @Autowired private MemberEntityRepository memberEntityRepository;
    @Autowired private ReplyEntityRepository replyEntityRepository;
    @Autowired private MemberService memberService;

    @Transactional
    public boolean postBoard(BoardDto boardDto){
        MemberDto loginDto = memberService.doLoginInfo();
        if(loginDto == null) return false;

        //1. 로그인된 회원 엔티티 찾기
        Optional<MemberEntity> optionalMemberEntity = memberEntityRepository.findById(loginDto.getMno());
        //2. 찾은 엔티티가 존재하지 않으면
        if(!optionalMemberEntity.isPresent()) return false;
        //3. 엔티티 꺼내기
        MemberEntity memberEntity = optionalMemberEntity.get();

            //- 글쓰기
        BoardEntity saveBoard = boardEntityRepository.save(boardDto.toEntity()) ;
            // -fk 대입
        if(saveBoard.getBno() >=1){//글쓰기 성공했으면
            saveBoard.setMemberEntity(memberEntity);
            return true;
        }

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
        boardEntity.setBcontent("JPA수정테스트중");
        return false;
    }
    @Transactional
    public boolean deleteBoard(){
        boardEntityRepository.deleteById(1);
        return false;
    }
}
