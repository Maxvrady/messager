package controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import serviceDAO.ProfileService;
import serviceDAO.services.AuthenticateService;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Action;

@Controller
@RequestMapping("/messager")
public class MessagerController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private AuthenticateService authenticateService;

    @RequestMapping(method = RequestMethod.GET)
    public String getMessagePage(@CookieValue("session_id") String userid, ModelMap modelMap) {
        try {
            if (authenticateService.isAuthenticate(userid)) {
                modelMap.put("userid", userid);

                return "message";
            }
            return "redirect:/login";
        }catch (Exception e){
            return "redirect:/login";
        }
    }
}
