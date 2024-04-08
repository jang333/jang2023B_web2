package ezenWeb.config;

import ezenWeb.conteroller.ChatSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.servlet.GraphQlWebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration //스프링 컨테이너에 빈 등록
@EnableWebSocket //웹소켓 매핑
public class WebSocketMapping implements WebSocketConfigurer {
    //* 스프링 버전에 따라 라이브러리 이름이 다를 수 있음
        //* spring2.x : WebSocketConfigurer

    @Autowired private ChatSocket chatSocket; //채팅관련서버소캣

    //1. 웹소켓 맵핑 등록
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // - ws로 요청된 url들을 어디로 핸들러 할껀지 설정
        registry.addHandler(chatSocket,"/chat");
    }
    //2. 클라이언트 메시지를 받았을때


}
