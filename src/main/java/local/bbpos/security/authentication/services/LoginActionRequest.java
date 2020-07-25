/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.bbpos.security.authentication.services;

import java.util.UUID;
import local.bbpos.security.authentication.models.User;

/**
 *
 * @author smlin
 */
public class LoginActionRequest {
    private ServiceRequestMetaData metaData;
    private User user;

    public ServiceRequestMetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(ServiceRequestMetaData metaData) {
        this.metaData = metaData;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
