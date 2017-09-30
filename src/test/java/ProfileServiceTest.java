import models.Message;
import models.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import serviceDAO.ProfileService;
import static org.junit.Assert.*;
import serviceDAO.services.AuthenticateService;
import serviceDAO.services.JsonService;

import java.util.List;

public class ProfileServiceTest {

    private JsonService jsonService = new JsonService();

    private SessionFactory sessionFactory = new Configuration().configure()
            .addAnnotatedClass(Profile.class)
            .addAnnotatedClass(Message.class)
            .buildSessionFactory();

    private ProfileService profileService = new ProfileService();

    {
        profileService.setHibernateFactory(sessionFactory);
    }

    @Test
    public void getJsonMessages() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String actual = "{\"id\":4,\"author\":\"admin\",\"to_send\":\"maxouni\",\"text\":\"Tree message : otsosi 3\"}, {\"id\":7,\"author\":\"maxouni\",\"to_send\":\"admin\",\"text\":\"Tree message : otsosi 5\"}";

        String[] users = {"maxouni", "admin"};

        List<Message> messageList = session.createQuery("from Message where author in (:users) and to_send in (:users)")
                .setParameterList("users", users)
                .list();

        String exepted = jsonService.getJsonFromListObjects(messageList);
        assertEquals(exepted, actual);

    }
}
