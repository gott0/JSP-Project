package websocket;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ChatingServer")
public class ChatServer {

	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>()); // 동기화 처리

	// 클라이언트 접속 시 실행
	@OnOpen
	public void onOpen(Session session) { // session은 클라이언트의 ID값을 받음
		clients.add(session);
		System.out.println("웹소켓 연결 : " + session.getId());

	}

	
	// 메세지를 받으면 실행
	@OnMessage
	public void onMessage(String message, Session session) throws IOException {// message은 입력한 메세지를 받음
		System.out.println("메세지 전송 : " + session.getId() + ":" + message);
		synchronized (clients) {
			for (Session client : clients) {
				if (!client.equals(session)) {
					client.getBasicRemote().sendText(message);
				}
			}

		} // 동기화
	}

	
	// 클라이언트와의 연결이 끊기면 실행
	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
		System.out.println("웹소켓 종료 : " + session.getId());
	}

	
	// 에러 발생 시
	@OnError
	public void onError(Throwable e) {
		System.out.println("에러발생");
		e.printStackTrace();
	}

	
}// c
