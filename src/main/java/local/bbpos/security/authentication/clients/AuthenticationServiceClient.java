/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.bbpos.security.authentication.clients;

import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import local.bbpos.security.authentication.bases.BaseServiceClient;
import local.bbpos.security.authentication.models.User;
import local.bbpos.security.authentication.services.AuthenticationService;
import local.bbpos.security.authentication.services.ServiceRequestMetaData;
import local.bbpos.security.authentication.utils.CryptoUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author smlin
 */
public class AuthenticationServiceClient extends BaseServiceClient {
    
    private static final Logger logger = LogManager.getLogger(AuthenticationServiceClient.class);
    
    private AuthenticationService authenticationService;
    protected ServiceRequestMetaData serviceRequestMetaData;
        
    public AuthenticationServiceClient() {
        authenticationService=new AuthenticationService();
        serviceRequestMetaData=authenticationService.initialServiceRequestMetaData();
    }
    
    public boolean authenticate(String loginName, String password) throws Exception {
        boolean result=false;
        try {
            logger.debug("authentication start. plain loginName="+loginName+"; plain password: "+password);
            byte[] encryptedLoginName=CryptoUtil.encryptString(serviceRequestMetaData.getPulblicKey(), loginName);
            String hashedPassword=CryptoUtil.hashString(password+authenticationService.getPasswordSalt());
            byte[] encryptedPassword=CryptoUtil.encryptString(serviceRequestMetaData.getPulblicKey(), hashedPassword);
            result=authenticationService.authenticate(encryptedLoginName, encryptedPassword);
        } catch (Exception ex) {
            throw new Exception("Exception thrown when authenticate.", ex);
        }
        return result;
    }
}
