/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.bbpos.security.authentication.bases;

import local.bbpos.security.authentication.services.ServiceRequestMetaData;
import local.bbpos.security.authentication.utils.CryptoUtil;

/**
 *
 * @author smlin
 */
public class BaseServiceClient {
    private ServiceRequestMetaData serviceMetaData;
    
    protected void updateRequestMetaData() {
        serviceMetaData.setRequestId(CryptoUtil.generateRandomBasedUUID());
    }
    public ServiceRequestMetaData getServiceMetaData() {
        return serviceMetaData;
    }

    public void setServiceMetaData(ServiceRequestMetaData serviceMetaData) {
        this.serviceMetaData = serviceMetaData;
    }
}
