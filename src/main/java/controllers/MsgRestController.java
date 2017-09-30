package controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import serviceDAO.ProfileService;

@RestController
public class MsgRestController {

    @Autowired
    ProfileService profileService;

//    @RequestMapping(value = "/messages/{username}")
//    public String messages(@PathVariable String username, @CookieValue(value = "session_id") String session_id) {
//        if (profileService.isAuthenticate(session_id)) {
//
//        }
//    }
}
