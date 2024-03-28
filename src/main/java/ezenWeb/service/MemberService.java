package ezenWeb.service;

import ezenWeb.model.dto.MemberDto;
import ezenWeb.model.entity.MemberEntity;
import ezenWeb.model.repository.MemberEntityRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberEntityRepository memberEntityRepository;

    //1.회원가입
    public int doSignupPost(@RequestBody MemberDto memberDto){
        System.out.println("memberDto = " + memberDto);

        // 리포지스토리를 통한 모든 회원엔티티호출
        List<MemberEntity> memberEntityList = memberEntityRepository.findAll();
        // 이메일중복검사실행
        for(int i = 0 ; i<memberEntityList.size() ; i++){
            MemberEntity m = memberEntityList.get(i);
            if(m.getMemail().equals(memberDto.getMemail())){
                return 2;
            }
        }

        // -- Dao 아닌 엔티티 이용한 레코드 저장하는방법
        //1. 엔티티를 만든다
        //2. 리포지토리 통한 엔티티를 저장 (엔티티 저장 성공시 매핑된 엔티티 반환)
        MemberEntity saveEntity = memberEntityRepository.save(memberDto.toEntity());
        //3. 엔티티가 생성 되었는지 (pk 생성유무로) 확인
        if(saveEntity.getMno()>0) return 1;
        return 0;
    }

    //*로그인 했다는 증거/기록
    @Autowired private HttpServletRequest request;

    //2.로그인(세션 저장)
    public boolean doLoginPost(MemberDto memberDto){
        System.out.println("MemberService.doLoginPost");
        System.out.println("memberDto = " + memberDto);

        //1. 리포스토리를 통한 모든 회원엔티티 호출
        List<MemberEntity> memberEntityList = memberEntityRepository.findAll();
        //System.out.println("memberEntityList = " + memberEntityList);
        //2. dto와 동일한 아이디/패스워드 찾기
        for(int i = 0 ; i < memberEntityList.size() ; i++){
            MemberEntity m = memberEntityList.get(i);
            //3.만약에 아이디가 동일하면(엔티티와 dto)
            if(m.getMemail().equals(memberDto.getMemail())){
                //4. 만약에 비밀번호가 동일하면
                if(m.getMpassword().equals(memberDto.getMpassword())){
                    //5.세선저장
                    request.getSession().setAttribute("loginInfo", memberDto);
                    return true;
                }
            }
        }

        return false;
    }

    //3.로그아웃(세션 삭제)
    public boolean doLogoutGet(){
        request.getSession().setAttribute("loginInfo",null);
        return true;
    }

    //4. 현재 로그인된 회원정보 호출(세션 값 호출/반환)
    public MemberDto doLoginInfo(){
        Object object =request.getSession().getAttribute("loginInfo");
        if(object != null){
            return (MemberDto)object;
        }
        return null;
    }
}
