import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashSet;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import lombok.extern.slf4j.Slf4j;

// 소켓 서버 역할의 클래스

@Slf4j
public class SimpleChatTextWebSocketHandler extends TextWebSocketHandler {

	private Set<WebSocketSession> sessions = new HashSet<>(); // 채팅방 역할
	
	
	
	
	
}
