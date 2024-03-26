package ezenWeb.service;

import ezenWeb.model.dto.MemberDto;
import ezenWeb.model.entity.MemberEntity;
import ezenWeb.model.repository.MemberEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MemberService {
    @Autowired
    MemberEntityRepository memberEntityRepository;
    public boolean doSignupPost(@RequestBody MemberDto memberDto){
        System.out.println("memberDto = " + memberDto);

        // -- Dao 아닌 엔티티 이용한 레코드 저장하는방법
        //1. 엔티티를 만든다
        //2. 리포지토리 통한 엔티티를 저장
        memberEntityRepository.save(memberDto.toEntity());

        return false;
    }
}
