package socketControl;

import models.Message;
import models.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import serviceDAO.ProfileService;
import serviceDAO.services.JsonService;

import java.util.HashMap;
import java.util.Map;


@Component
public class MessageSocket extends TextWebSocketHandler {

    @Autowired
    private JsonService jsonService;

    @Autowired
    private ProfileService profileService;

    private Map<String, WebSocketSession> sessionMap = new HashMap<>();

    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        session.sendMessage(new TextMessage(message.getPayload()));
        Map jsonMap = jsonService.getJsonAsMap(message.getPayload());

        if (jsonMap.containsKey("method")) {
            session.sendMessage(new TextMessage("bed"));
        }

//        if (profileService.isAuthenticate((String) jsonMap.get("session_id"))) {
//
//            session.sendMessage(new TextMessage("point 1"));
//            Profile userProfile = profileService.getUserOfMap((String) jsonMap.get("session_id"));
//            String method = (String) jsonMap.get("method");
//            session.sendMessage(new TextMessage(method));
//
//
//            if (method.equals("registration")) {
//                sessionMap.put(userProfile.getUsername(), session);
//                session.sendMessage(new TextMessage("OK"));
//            }
//            else if (method.equals("message")){
//                String author = userProfile.getUsername();
//                String to = (String) jsonMap.get("to");
//                String text = (String) jsonMap.get("text");
//
//                Message messageObj = new Message(author, to, text);
//                profileService.saveMessage(new Message(author, to, text));
//
//                if (sessionMap.containsKey(to)) {
//                    String messageString = jsonService.getJsonFromObject(messageObj);
//
//                    WebSocketSession socketSession = sessionMap.get(to);
//                    socketSession.sendMessage(new TextMessage(messageString));
//                }
//            }
//
//
//        }

    }

    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    }

    public ProfileService getProfileService() {
        return profileService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    public JsonService getJsonService() {
        return jsonService;
    }

    public void setJsonService(JsonService jsonService) {
        this.jsonService = jsonService;
    }
}
