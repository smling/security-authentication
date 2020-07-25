/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.bbpos.security.authentication.services;

import java.security.Key;
import java.util.UUID;

/**
 *
 * @author smlin
 */
public class ServiceRequestMetaData {
    private UUID requestId;
    private UUID correlationId;
    private Key pulblicKey;

    public UUID getRequestId() {
        return requestId;
    }

    public void setRequestId(UUID requestId) {
        this.requestId = requestId;
    }

    public UUID getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(UUID correlationId) {
        this.correlationId = correlationId;
    }

    public Key getPulblicKey() {
        return pulblicKey;
    }

    public void setPulblicKey(Key pulblicKey) {
        this.pulblicKey = pulblicKey;
    }
}
