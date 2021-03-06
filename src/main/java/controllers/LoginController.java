package controllers;

import models.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import serviceDAO.ProfileService;
import serviceDAO.services.MD5Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private MD5Service md5Service;

    @Autowired
    private ProfileService profileService;


    @RequestMapping(method = RequestMethod.GET)
    public String getLoginPage(@CookieValue(value = "session_id", defaultValue = "null") String userid) {
        if (profileService.isAuthenticate(userid)) {
            return "redirect:/messager";
        }
        return "login_page";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @CookieValue(value = "session_id", defaultValue = "null") String userid,
                        HttpServletResponse response) {

        if (profileService.isAuthenticate(userid)) {
            return "redirect:/messager";
        }

        Profile user;

        try {
            user = profileService.getUserOfDb(username);
        } catch (Exception e){
            return "login_page";
        }

        if (user.getPassword().equals(md5Service.getMD5Hash(password))) {
            String userId = profileService.addUser(user);
            Cookie cookie = new Cookie("session_id", userId);
            response.addCookie(cookie);
            return "redirect:/messager";
        }

        return "login_page";
    }

    public MD5Service getMd5Service() {
        return md5Service;
    }

    public void setMd5Service(MD5Service md5Service) {
        this.md5Service = md5Service;
    }

    public ProfileService getProfileService() {
        return profileService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }
}
