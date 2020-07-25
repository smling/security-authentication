/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.bbpos.security.authentication.models;

import java.util.UUID;

/**
 *
 * @author smlin
 */
public class User {
    public UUID id;
    public String loginName;
    public String hashedPassword;

    public User(UUID id, String loginName, String password) {
        this.id=id;
        this.loginName=loginName;
        this.hashedPassword=password;
    }
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
