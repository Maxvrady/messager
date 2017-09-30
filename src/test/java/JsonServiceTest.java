import org.junit.Before;
import org.junit.Test;
import serviceDAO.services.JsonService;
import socketControl.JsonObj;

import java.util.*;

import static org.junit.Assert.*;

public class JsonServiceTest {

    private JsonService jsonService;

    @Before
    public void newJsonService() {
        jsonService = new JsonService();
    }


    @Test
    public void getJsonAsMapTest() {
        final String JSONSTRING = "{ name: 'maxouni', born : {city : 'moskov', year : 2012} }";

        Map jsonPerson = jsonService.getJsonAsMap(JSONSTRING);
        assertEquals(jsonPerson.get("name"), "maxouni");

        Map jsonPersonBorn = (Map) jsonPerson.get("born");

        assertEquals(jsonPersonBorn.get("city"), "moskov");
        assertEquals(jsonPersonBorn.get("year"), 2012.0);
    }

    @Test
    public void getJsonFromObject() {
        final String actual = "{\"id\":2,\"name\":\"maxouni\"}";

        JsonObj jsonObj = new JsonObj(2, "maxouni");

        String exepted = jsonService.getJsonFromObject(jsonObj);
        assertEquals(exepted, actual);
    }

    @Test
    public void testGetJsonFromListObjects() {
        final String actual = "{\"id\":1,\"name\":\"alex\"}, {\"id\":2,\"name\":\"serega\"}";

        JsonObj jsonObj0 = new JsonObj(1, "alex");
        JsonObj jsonObj1 = new JsonObj(2, "serega");

        List listObj = new ArrayList();

        listObj.add(jsonObj0);
        listObj.add(jsonObj1);

        String exepted = jsonService.getJsonFromListObjects(listObj);

        assertEquals(exepted, actual);

    }

}
