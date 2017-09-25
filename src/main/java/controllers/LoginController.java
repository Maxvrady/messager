package controllers;

import models.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import serviceDAO.ProfileService;
import serviceDAO.services.AuthenticateService;
import serviceDAO.services.MD5Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private MD5Service md5Service;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private AuthenticateService authenticateService;

    @RequestMapping(method = RequestMethod.GET)
    public String getLoginPage(HttpServletResponse response, HttpServletRequest request) {
        return "login_page";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        HttpServletResponse response) {

        Profile user;

        try {
            user = profileService.getUser(username);
        } catch (Exception e){
            return "login_page";
        }

        if (user.getPassword().equals(md5Service.getMD5Hash(password))) {
            String userId = authenticateService.addUser(user);
            Cookie cookie = new Cookie("session_id", userId);
            response.addCookie(cookie);
            return "redirect:/registration";
        }

        return "login_page";
    }

}
