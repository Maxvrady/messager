package serviceDAO.services;


import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service("userToken")
public class UserTokenService {

    public UserTokenService() {
    }

    public String getUserToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte byteToken[] = new byte[50];
        secureRandom.nextBytes(byteToken);
        return byteToken.toString();
    }
}
