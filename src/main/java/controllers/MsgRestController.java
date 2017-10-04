package controllers;


import models.Message;
import models.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import serviceDAO.ProfileService;
import serviceDAO.services.JsonService;

import java.util.List;

@RestController
public class MsgRestController {

    @Autowired
    private JsonService jsonService;

    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/messages/{username}")
    public String messages(@PathVariable String username, @CookieValue(value = "session_id") String session_id) {
        if (profileService.isAuthenticate(session_id)) {
            Profile profile = profileService.getUserOfMap(session_id);
            List<Message> messageList = profileService.getDialog(profile.getUsername(), username);
            return jsonService.getJsonFromListObjects(messageList);
        }
        return "BED";
    }

    public JsonService getJsonService() {
        return jsonService;
    }

    public void setJsonService(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    public ProfileService getProfileService() {
        return profileService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }
}
