package controllers;

import form.RegistrationForm;
import models.Profile;
import org.springframework.web.bind.annotation.CookieValue;
import serviceDAO.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private ProfileService profileService;

    @RequestMapping(method = RequestMethod.GET)
    public String getRegistrationPage(ModelMap modelMap,
                                      @CookieValue(value = "session_id", defaultValue = "null") String userid) {

        if (profileService.isAuthenticate(userid)) {
            return "redirect:/messager";
        }

        modelMap.addAttribute("registrationForm", new RegistrationForm());
        return "registration_page";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registration(@Valid RegistrationForm registrationForm,
                               BindingResult result,
                               @CookieValue(value = "session_id", defaultValue = "null") String userid) {

        if (profileService.isAuthenticate(userid)) {
            return "redirect:/messager";
        }

        if (result.hasErrors()) return "registration_page";
        Profile profile = new Profile();

        profile.setFirstName(registrationForm.getName());
        profile.setEmail(registrationForm.getEmail());
        profile.setPassword(registrationForm.getPassword());
        profile.setUsername(registrationForm.getUsername());

        profileService.registrationProfile(profile);
        return "redirect:/login";
    }

    public ProfileService getProfileService() {
        return profileService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }
}
