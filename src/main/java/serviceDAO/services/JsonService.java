package serviceDAO.services;


import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JsonService {

    public JsonService() {
    }

    public Map getJsonAsMap(String string) {
        try {
            Map map = new Gson().fromJson(string, Map.class);
            return map;
        }catch (Exception e){
            return new HashMap<String, Object>();
        }
    }

    public String getJsonFromObject(Object object) {
        String jsonObj = new Gson().toJson(object);
        return jsonObj;
    }
}
