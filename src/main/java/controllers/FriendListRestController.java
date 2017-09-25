package controllers;

import models.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import serviceDAO.ProfileService;
import serviceDAO.services.AuthenticateService;
import socketControl.JsonObj;

@RestController
public class FriendListRestController {

    @Autowired
    ProfileService profileService;

    @Autowired
    AuthenticateService authenticateService;

    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public String answer(@CookieValue(value = "session_id") String userid) {
        if (authenticateService.isAuthenticate(userid)) {
            return authenticateService.getUser(userid).getFriendList();
        }else return "{}";
    }

    @RequestMapping(value = "/friend-add", method = RequestMethod.POST)
    public String addFriend(@CookieValue(value = "session_id") String userid, @RequestParam(value = "friend") String friend) {
        if (authenticateService.isAuthenticate(userid)) {
            try {
                if (profileService.getUser(friend).getUsername().equals(friend)){
                    Profile user = authenticateService.getUser(userid);
                    profileService.addFriend(user, friend);
                    return "OK";
                }
            }catch (Exception e) {
                return "BED";
            }
        }
        return "BED";
    }

}
