package serviceDAO.services;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;


@Service("MD5Service")
public class MD5Service {

    public MD5Service() {}

    public String getMD5Hash(String string) {
        String MD5Hash = DigestUtils.md5Hex(string);
        return MD5Hash;
    }
}
