package org.yunzhong.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author yunzhong
 *
 */
@Controller
public class WebsocketController {

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public String greeting(String message) throws Exception {
		return "hello: " + message;
	}
}
