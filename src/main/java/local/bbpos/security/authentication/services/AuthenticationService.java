/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.bbpos.security.authentication.services;

import java.util.ArrayList;
import java.util.List;
import local.bbpos.security.authentication.bases.BaseService;
import local.bbpos.security.authentication.models.User;
import local.bbpos.security.authentication.utils.CryptoUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 *
 * @author smlin
 */
public class AuthenticationService extends BaseService {
    private static final Logger logger = LogManager.getLogger(AuthenticationService.class);
    private String passwordSalt;

    public AuthenticationService() {
        this.passwordSalt=CryptoUtil.generatePasswordSalt();
    }
    
    public boolean authenticate(byte[] encryptedLoginName, byte[] encryptedPassword) throws Exception {
        logger.info("authenticate() started. EncryptedLoginName"+ encryptedLoginName);
        boolean result=false;
        String plainLoginName=CryptoUtil.decryptString(keyPair.getPrivate(), encryptedLoginName);
        String hashedPassword=CryptoUtil.decryptString(keyPair.getPrivate(), encryptedPassword);
        List<User> users=this.getUsers();
        if(users.stream().anyMatch(o->(o.loginName.equals(plainLoginName)) && (o.hashedPassword.equals(hashedPassword)))) {
            logger.info("user account "+plainLoginName+" found and password match.");
            result=true;
        } else {
            logger.warn("user account "+plainLoginName+" not found or password miss-match.");
        }
        logger.info("user account "+plainLoginName+" found and password match.");
        return result;
    }
    
    private List<User> getUsers() throws Exception {
        logger.info("getUser() started.");
        List<User> result=new ArrayList<User>();
        User user1=new User(CryptoUtil.generateRandomBasedUUID(), "test1",CryptoUtil.hashString("Password"+getPasswordSalt()));
        result.add(user1);
        logger.info("getUser() done. no. of record found: "+result.size());
        return result;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }
}
