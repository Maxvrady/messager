package socketControl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serviceDAO.ProfileService;
import serviceDAO.services.JsonService;

@Service("messageService")
public class MessageService {

    @Autowired
    private JsonService jsonService;

    @Autowired
    private ProfileService profileService;

    public MessageService() {
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
