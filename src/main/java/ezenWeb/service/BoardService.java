package ezenWeb.service;

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

@Service
public class BoardService {

    //*리포지토리 객체
    @Autowired private BoardEntityRepository boardEntityRepository;
    @Autowired private MemberEntityRepository memberEntityRepository;
    @Autowired private ReplyEntityRepository replyEntityRepository;

    @Transactional
    public boolean postBoard(){
        //System.out.println("BoardService.postBoard");
        //===== 테스트 =====
        //1. 회원가입
            //1. 엔티티 객체 생성
        MemberEntity memberEntity = MemberEntity.builder()
                .memail("qwe@qwe.com")
                .mname("유재석")
                .mpassword("1234")
                .build();
        //System.out.println("memberEntity = " + memberEntity);
            //2. 해당 엔티티를 DB에 저장할 수 있도록 조작
        MemberEntity saveMemberEntity = memberEntityRepository.save(memberEntity);

        //2. 회원가입된 회원으로 글쓰기
            //1. 엔티티 객체 생성
        BoardEntity boardEntity = BoardEntity.builder()
                .bcontent("게시물글입니다.")
            //2. ********* 글쓴이[fk대입]
                .memberEntity(saveMemberEntity)
                .build();
            //3. 해당 엔티티를 DB에 저장할수 있도록 조작
        BoardEntity saveBoardEntity = boardEntityRepository.save(boardEntity);

        //3. 해당 글에 댓글 작성
            //1. 엔티티 객체 생성
        ReplyEntity replyEntity = ReplyEntity.builder()
                .rcontent("댓글입니다.")
            //2. ********* 글쓴이[fk대입]
                .memberEntity(saveMemberEntity)
            //2. ********* 게시물[fk대입]
                .boardEntity(saveBoardEntity)
                .build();
            //3. 해당 엔티티를 DB에 저장할 수 있도록 조작
        replyEntityRepository.save(replyEntity);

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
