package ezenWeb.service;

import ezenWeb.model.dto.BoardDto;
import ezenWeb.model.dto.MemberDto;
import ezenWeb.model.dto.PageDto;
import ezenWeb.model.entity.BoardEntity;
import ezenWeb.model.entity.BoardImgEntity;
import ezenWeb.model.entity.MemberEntity;
import ezenWeb.model.entity.ReplyEntity;
import ezenWeb.model.repository.BoardEntityRepository;
import ezenWeb.model.repository.BoardImgRepository;
import ezenWeb.model.repository.MemberEntityRepository;
import ezenWeb.model.repository.ReplyEntityRepository;
import jakarta.transaction.Transactional;
import org.hibernate.service.spi.Stoppable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardService {

    //*리포지토리 객체
    @Autowired private BoardEntityRepository boardEntityRepository;
    @Autowired private MemberEntityRepository memberEntityRepository;
    @Autowired private ReplyEntityRepository replyEntityRepository;
    @Autowired private MemberService memberService;
    @Autowired private FileService fileService;
    @Autowired
    private BoardImgRepository  boardImgRepository;

    //C
    @Transactional
    public boolean postBoard(BoardDto boardDto){
        System.out.println("boardDto = " + boardDto);
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
        System.out.println("saveBoard = " + saveBoard);

        System.out.println(boardDto.getUploadList());

            //1.하나씩 업로드서비스에 업로드 요청
        boardDto.getUploadList().forEach((i)->{
            System.out.println("i = " + i);
            //2.하나씩 업로드 된 파일명 반환받기
            String fileName = fileService.fileUpload(i);
            if(fileName != null) {
                //3.하나씩 업로드된 파일명으로 게시물파일엔티티생성
                BoardImgEntity boardImgEntity = BoardImgEntity.builder()
                        .bimg(fileName)
                        .boardEntity(saveBoard) //fk : 게시물 fk
                        .build();
                //4. 엔티티 영속성 (서버가 종료되면 삭제되니까 => DB에 저장하려고)
                boardImgRepository.save(boardImgEntity);
            }
        });


            // -fk 대입
        if(saveBoard.getBno() >=1){//글쓰기 성공했으면
            saveBoard.setMemberEntity(memberEntity);
            return true;
        }

        return false;


    }

    //게시물 출력
    @Transactional
    public PageDto getBoard(int page, int view){

        // ====================== 1 ======================
/*
        //1. 리포지토리를 이용한 모든 엔티티를 호출
        List<BoardEntity> result = boardEntityRepository.findAll();
        //2. Entity ---> Dto 변환
        List<BoardDto> boardDtoList = new ArrayList<>();
            //1. 꺼내온 entity를 순환
        for(int i = 0; i<result.size();i++){
            //2. entity 하나씩 꺼내기
            BoardEntity boardEntity = result.get(i);
            //3. 해당 엔티티를 Dto로 변환
            BoardDto boardDto = boardEntity.toDto();
                    //------------- 게시물안에 게시물 사진
                    List<String> bimgList = new ArrayList<>();
                    for(int j = 0 ; j<boardEntity.getBoardImgEntityList().size(); j++){
                        BoardImgEntity boardImgEntity = boardEntity.getBoardImgEntityList().get(j);
                        System.out.println("boardImgEntity = " + boardImgEntity);

                        String bimg = boardImgEntity.getBimg();
                        bimgList.add(bimg);
                    }
                    boardDto.setBimgList(bimgList);
            //4. 변환된 dto 리스트에 담기
            boardDtoList.add(boardDto);
        }
        System.out.println("result = " + result);
        return boardDtoList;
 */
        // ====================== = ======================

        // ====================== 2 ======================
        //현재페이지 : page /페이지당 표시할 게시물 수 : view
        //1.pageable 인터페이스 이용한 페이징 처리
        Pageable pageable = PageRequest.of(page-1,view);
            //1. 페이징처리된 엔티티 호출
        Page<BoardEntity> boardEntityPage = boardEntityRepository.findAll(pageable);

            //-- List가 아닌 Page 타입일때 List 동일한 메소드 사용하고 추가 가능
        System.out.println("boardEntityPage.getTotalPages() 전체페이지수 = " + boardEntityPage.getTotalPages());
        int count = boardEntityPage.getTotalPages();
        System.out.println("boardEntityPage.getTotalElements() 전체게시물수 = " + boardEntityPage.getTotalElements());

        List<Object> data = boardEntityPage.stream().map(
                (boardEntity )->{
                    return boardEntity.toDto();
                }).collect(Collectors.toList());
            //2. 페이지DTO 반환값 구성
        PageDto pageDto = PageDto.builder()
                .data(data) // 페이징처리된 레코드들을 대입
                .page(page) // 현재 페이지수
                .count(count)    // 총페이지수
                .build();

        //2. 게시물 레코드들을
        return pageDto;
        // ====================== = ======================

    }// m e


    @Transactional
    public boolean putBoard(){
        BoardEntity boardEntity = boardEntityRepository.findById(1).get();
        boardEntity.setBcontent("JPA수정테스트중");
        return false;
    }
    @Transactional
    public boolean deleteBoard(int bno){
        if(bno==0) return false;
        ///로그인된 정보
        MemberDto loginDto = memberService.doLoginInfo();
        if(loginDto == null) return false;
        //2.내 게시물 확인
        Optional<BoardEntity> optionalBoardEntity = boardEntityRepository.findById(bno);
        if(optionalBoardEntity.isPresent()){
            if(optionalBoardEntity.get().getMemberEntity().getMno() == loginDto.getMno()){
                boardEntityRepository.deleteById(bno);
                return true;
            }
        }

        return true;
    }
}
