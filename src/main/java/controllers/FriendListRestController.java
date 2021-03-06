package controllers;

import models.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import serviceDAO.ProfileService;

@RestController
public class FriendListRestController {

    @Autowired
    ProfileService profileService;


    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public String answer(@CookieValue(value = "session_id") String userid) {
        if (profileService.isAuthenticate(userid)) {
            return profileService.getUserOfMap(userid).getFriendList();
        }else return "{}";
    }

    @RequestMapping(value = "/friend-add", method = RequestMethod.POST)
    public String addFriend(@CookieValue(value = "session_id") String userid, @RequestParam(value = "friend") String friend) {
        if (profileService.isAuthenticate(userid)) {
            try {
                if (profileService.getUserOfDb(friend).getUsername().equals(friend)){
                    Profile user = profileService.getUserOfMap(userid);
                    profileService.addFriend(user, friend);
                    return "OK";
                }
            }catch (Exception e) {
                return "BED";
            }
        }
        return "BED";
    }

    public ProfileService getProfileService() {
        return profileService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }
}
