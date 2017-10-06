package serviceDAO.services;


import models.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("authenticateService")
public class AuthenticateService {

    @Autowired
    UserTokenService userTokenService;

    private HashMap<String, Profile> authenticatedUser = new HashMap<>();

    public String addUser(Profile profile) {
        String generatedToken = userTokenService.getUserToken();
        authenticatedUser.put(generatedToken, profile);
        return generatedToken;
    }

    public boolean isAuthenticate(String userId) {
        return authenticatedUser.containsKey(userId);
    }

    public void removeUser(String userId) {
        authenticatedUser.remove(userId);
    }

    public Profile getUser(String userid) {
        return (Profile) authenticatedUser.get(userid);
    }

}
