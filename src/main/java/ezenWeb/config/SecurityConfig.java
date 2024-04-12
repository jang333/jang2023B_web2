package ezenWeb.config;

import ezenWeb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {//시큐리티를 컴스텀 하는곳

    @Autowired
    MemberService memberService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //1. HTTP 요청에 따른 부여된 권한/상태 확인 후 PATH(자원) 제한
        http.authorizeHttpRequests(
                authorizeRequest //매개변수
                        ->       //람다식
                authorizeRequest
                        // get HTTP : /board : 인증(로그인)된 회원이면 허가
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/board")).authenticated()
                        // get HTTP : /board/write : 인증(로그인)된 회원이면서 ROLE이 USER이면 허가
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/board/write")).hasAnyRole("USER")
                        // /chat : 인증(로그인)되고 ROLE이 TEAM1이거나 TEAM2이면 허가
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/chat")).hasAnyRole("TEAM1","TEAM2")
                        // get HTTP : 그외 PATH(/**)는 모두 허가
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/**")).permitAll()
        );

        //2. 로그인 폼 커스텀 (기존 controller 매핑 주석/삭제 처리)
            //http.formLogin(AbstractHttpConfigurer ::disable);//시큐리티가 제공하는 로그인 폼을 사용하지 않음
        http.formLogin( //AXIOS,AJAX 사용시 contentType : form
                로그인관련매개변수
                        ->
                로그인관련매개변수
                        .loginPage("/member/login")                     //로그인 할 view url 정의
                        .loginProcessingUrl("/member/login/post.do")    //로그인을 처리할 url 정의
                        .usernameParameter("memail")                    //로그인에 사용할 id 변수명
                        .passwordParameter("mpassword")                 //로그인에 사용할 pw 변수명
                        //.defaultSuccessUrl("/")                         //로그인 성공하면 반환될 url
                        //.failureForwardUrl("/member/login")             //로그인 실패하면 반환될 url
                        //.successHandler((request : http요청객체, response:http응답객체, authentication:성공유저인증정보객체) -> { })
                        .successHandler((request, response, authentication) -> {
                            response.setContentType("application/json;utf-8");
                            response.getWriter().println("true");   //@RequestBody 역할
                        })
                        //.failureHandler((request: http요청객체, response:http응답객체, exception: 실패정보객체) -> {})
                        .failureHandler((request, response, exception) -> {
                            System.out.println("exception = " + exception); //실패 예외 이유
                            response.setContentType("application/json;utf-8");
                            response.getWriter().println("false");
                        })
                );

        //3. 로그아웃 커스텀 (기존 controller 매핑 주석/삭제 처리)
        http.logout(
                로그아웃관련매개변수 ->
                        로그아웃관련매개변수
                                .logoutRequestMatcher( new AntPathRequestMatcher("/member/logout/get.do")) //로그아웃 처리할 url 정의
                                .logoutSuccessUrl("/")  //로그아웃에 성공하면 반환될 url
                                .invalidateHttpSession(true) //세션초기화
        );

        //4. csrf (post, put 요청금지) 공격 방지 : 특정(인증/허가) url만 post,put 가능하도록
        http.csrf(AbstractHttpConfigurer ::disable); //csrf 사용안함 //개발작업시
            //csrf 사용시 //배포시
        //http.csrf(매개변수 -> 매개변수.ignoringRequestMatchers(AntPathRequestMatcher("/member/login")))

        //5. 로그인 처리에 서비스를 등록
        http.userDetailsService(memberService);

        //6. oauth2(소셜 로그인)
        http.oauth2Login(oAuth2LoginConfigurer -> {
           oAuth2LoginConfigurer
                   .loginPage("/member/login") // oauth2 로그인을 할 view url 정의
                   .userInfoEndpoint(userInfoEndpointConfig -> {
                       userInfoEndpointConfig.userService(memberService);
                   });
        });
            //Endpoint : 종착점
            // 세션 : 1.(톰캣)http서블릿세션 2.(JS)Session 3.(WS)WebSocketSession
                // - 저장소

        return http.build();

    }//m end


    //2. 시큐리티가 패스워드 검증할때 사용할 암호화 객체
    @Bean //해당 메소드를 스프링컨테이너 등록
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
