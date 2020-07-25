/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.bbpos.security.authentication.bases;

import java.security.KeyPair;
import java.util.logging.Level;
import java.util.logging.Logger;
import local.bbpos.security.authentication.services.ServiceRequestMetaData;
import local.bbpos.security.authentication.utils.CryptoUtil;

/**
 *
 * @author smlin
 */
public abstract class BaseService {
    protected KeyPair keyPair;

    public BaseService() {
        try {
            this.keyPair=CryptoUtil.GenerateRSAKey();
        } catch (Exception ex) {
            Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ServiceRequestMetaData initialServiceRequestMetaData()
    {
        ServiceRequestMetaData result=new ServiceRequestMetaData();
        result.setRequestId(CryptoUtil.generateTimeBasedUUID());
        result.setCorrelationId(CryptoUtil.generateRandomBasedUUID());
        result.setPulblicKey(keyPair.getPublic());
        return result;
    }
}
