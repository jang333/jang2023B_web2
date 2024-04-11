package ezenWeb.conteroller;

import ezenWeb.model.dto.BoardDto;
import ezenWeb.model.dto.MemberDto;
import ezenWeb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member")
//@CrossOrigin("http://localhost:3000") //*요청 근원지를 교차허용
public class MemberController {

    @Autowired
    MemberService memberService;

    @PostMapping("/signup/post.do") //1. 회원가입
    public int doSignupPost(@RequestBody MemberDto memberDto){
        System.out.println("memberDto = " + memberDto);

        return memberService.doSignupPost(memberDto);
    }
//==================== 스프링 시큐리티로 인한 로그인/로그아웃 사용 안함 ===================

//    @PostMapping("/login/post.do") //2. 로그인
//    public boolean doLoginPost(MemberDto memberDto){
//        System.out.println("memberDto = " + memberDto);
//        return memberService.doLoginPost(memberDto);
//    }

//    @GetMapping("/logout/get.do") //3. 로그아웃
//    public boolean doLogoutGet(){
//        return memberService.doLogoutGet();
//    }

// ==================== ========================================= ====================

    @GetMapping("/login/info/get.do")
    public MemberDto doLoginInfo(){
        return memberService.doLoginInfo();
    }

    @GetMapping("/find/memail/get.do")
    public boolean doFindEmail(String memail){
        return memberService.findGetMemail(memail);
    }
    @GetMapping("/find/myboard/get.do")
    public List<Map<Object,Object>> findMyList(){
        return memberService.findByMyBoardList();
    }
}
