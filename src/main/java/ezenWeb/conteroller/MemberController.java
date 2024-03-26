package ezenWeb.conteroller;

import ezenWeb.model.dto.MemberDto;
import ezenWeb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@CrossOrigin("http://localhost:3000") //요청 근원지를 교차허용
public class MemberController {

    @Autowired
    MemberService memberService;

    @PostMapping("/signup/post.do")
    public boolean doSignupPost(@RequestBody MemberDto memberDto){
        System.out.println("memberDto = " + memberDto);
        return memberService.doSignupPost(memberDto);
    }
}
