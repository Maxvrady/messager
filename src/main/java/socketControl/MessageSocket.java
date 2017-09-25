package socketControl;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import serviceDAO.services.JsonService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class MessageSocket extends TextWebSocketHandler {

    @Autowired
    private JsonService jsonService;

    private Map sessionMap = new HashMap<String, WebSocketSession>();


    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    }

    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        Map jsonMap;

        try {
            jsonMap = jsonService.getJsonAsMap(message.getPayload());
            JsonObj jsonObj = new JsonObj();
            jsonObj.setId(123);
            jsonObj.setName("Xyi");
            String json = jsonService.getJsonFromObject(jsonObj);

            if (jsonMap.get("data").equals("bratka")) {
                session.sendMessage(new TextMessage(json));
            }
        }catch (Exception e) {
            session.sendMessage(new TextMessage("xyevo"));
        }

        session.sendMessage(new TextMessage("Ne bratka end"));
    }

    public JsonService getJsonService() {
        return jsonService;
    }

    public void setJsonService(JsonService jsonService) {
        this.jsonService = jsonService;
    }
}
