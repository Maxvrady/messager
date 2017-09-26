package serviceDAO;

import serviceDAO.services.AuthenticateService;
import serviceDAO.services.MD5Service;
import models.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("profileService")
public class ProfileService {

    @Autowired
    private MD5Service md5Service;

    @Autowired
    private SessionFactory hibernateFactory;

    @Autowired
    private AuthenticateService authenticateService;

    public Profile getUserOfMap(String userid) {
        return authenticateService.getUser(userid);
    }

    public String addUser(Profile profile) {
        return authenticateService.addUser(profile);
    }

    public Boolean isAuthenticate(String userid) {
        return authenticateService.isAuthenticate(userid);
    }



    public Profile addFriend(Profile profile, String username) {
        Session session = hibernateFactory.openSession();
        session.beginTransaction();

        String[] friends = {profile.getFriendList()};

        if (friends.length == 1 && friends[0] == null) {
            profile.setFriendList(username);
        }else {
            profile.setFriendList(profile.getFriendList() + "," + username);
        }
        session.update(profile);

        session.getTransaction().commit();
        session.close();
        return profile;
    }

    public void registrationProfile(Profile profile) {

        Session session = hibernateFactory.openSession();
        session.beginTransaction();

        profile.setPassword(md5Service.getMD5Hash(profile.getPassword()));

        session.save(profile);
        session.getTransaction().commit();
        session.close();

    }

    public Profile getUserOfDb(String username) {

        Session session = hibernateFactory.openSession();
        session.beginTransaction();

        Profile profile = (Profile) session.createQuery("FROM Profile p WHERE p.username = :username")
                .setParameter("username", username).getSingleResult();


        session.getTransaction().commit();
        session.close();
        return profile;
    }

    public ProfileService() {
    }

    public MD5Service getMd5Service() {
        return md5Service;
    }

    public void setMd5Service(MD5Service md5Service) {
        this.md5Service = md5Service;
    }

    public SessionFactory getHibernateFactory() {
        return hibernateFactory;
    }

    public void setHibernateFactory(SessionFactory hibernateFactory) {
        this.hibernateFactory = hibernateFactory;
    }
}
