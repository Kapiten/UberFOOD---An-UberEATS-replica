/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author StrettO
 */
@Embeddable
public class PaymentPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "payment_id")
    private int paymentId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "person_person_id")
    private int personPersonId;

    public PaymentPK() {
    }

    public PaymentPK(int paymentId, int personPersonId) {
        this.paymentId = paymentId;
        this.personPersonId = personPersonId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getPersonPersonId() {
        return personPersonId;
    }

    public void setPersonPersonId(int personPersonId) {
        this.personPersonId = personPersonId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) paymentId;
        hash += (int) personPersonId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentPK)) {
            return false;
        }
        PaymentPK other = (PaymentPK) object;
        if (this.paymentId != other.paymentId) {
            return false;
        }
        if (this.personPersonId != other.personPersonId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reverside.entity.PaymentPK[ paymentId=" + paymentId + ", personPersonId=" + personPersonId + " ]";
    }
    
}
