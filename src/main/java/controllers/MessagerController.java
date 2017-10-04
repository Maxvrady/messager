package controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import serviceDAO.ProfileService;

@Controller
@RequestMapping("/messager")
public class MessagerController {

    @Autowired
    private ProfileService profileService;


    @RequestMapping(method = RequestMethod.GET)
    public String getMessagePage(@CookieValue(value = "session_id", defaultValue = "null") String userid, ModelMap modelMap) {
        try {
            if (profileService.isAuthenticate(userid)) {
                modelMap.put("userid", userid);

                return "message";
            }
            return "redirect:/login";
        }catch (Exception e){
            return "redirect:/login";
        }
    }

    public ProfileService getProfileService() {
        return profileService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }
}
