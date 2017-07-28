/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author StrettO
 */
@Entity
@Table(name = "payment_transactions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaymentTransactions.findAll", query = "SELECT p FROM PaymentTransactions p")
    , @NamedQuery(name = "PaymentTransactions.findByPaymentTransactionsId", query = "SELECT p FROM PaymentTransactions p WHERE p.paymentTransactionsId = :paymentTransactionsId")
    , @NamedQuery(name = "PaymentTransactions.findByDate", query = "SELECT p FROM PaymentTransactions p WHERE p.date = :date")
    , @NamedQuery(name = "PaymentTransactions.findByAmount", query = "SELECT p FROM PaymentTransactions p WHERE p.amount = :amount")
    , @NamedQuery(name = "PaymentTransactions.findByPaymentId", query = "SELECT p FROM PaymentTransactions p WHERE p.paymentId = :paymentId")
    , @NamedQuery(name = "PaymentTransactions.findByType", query = "SELECT p FROM PaymentTransactions p WHERE p.type = :type")
    , @NamedQuery(name = "PaymentTransactions.findByReferance", query = "SELECT p FROM PaymentTransactions p WHERE p.referance = :referance")
    , @NamedQuery(name = "PaymentTransactions.findByTo", query = "SELECT p FROM PaymentTransactions p WHERE p.to = :to")})
public class PaymentTransactions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "payment_transactions_id")
    private Integer paymentTransactionsId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "date")
    private String date;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "amount")
    private String amount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "payment_id")
    private String paymentId;
    @Size(max = 45)
    @Column(name = "type")
    private String type;
    @Size(max = 45)
    @Column(name = "referance")
    private String referance;
    @Size(max = 45)
    @Column(name = "to")
    private String to;

    public PaymentTransactions() {
    }

    public PaymentTransactions(Integer paymentTransactionsId) {
        this.paymentTransactionsId = paymentTransactionsId;
    }

    public PaymentTransactions(Integer paymentTransactionsId, String date, String amount, String paymentId) {
        this.paymentTransactionsId = paymentTransactionsId;
        this.date = date;
        this.amount = amount;
        this.paymentId = paymentId;
    }

    public Integer getPaymentTransactionsId() {
        return paymentTransactionsId;
    }

    public void setPaymentTransactionsId(Integer paymentTransactionsId) {
        this.paymentTransactionsId = paymentTransactionsId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReferance() {
        return referance;
    }

    public void setReferance(String referance) {
        this.referance = referance;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentTransactionsId != null ? paymentTransactionsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentTransactions)) {
            return false;
        }
        PaymentTransactions other = (PaymentTransactions) object;
        if ((this.paymentTransactionsId == null && other.paymentTransactionsId != null) || (this.paymentTransactionsId != null && !this.paymentTransactionsId.equals(other.paymentTransactionsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reverside.entity.PaymentTransactions[ paymentTransactionsId=" + paymentTransactionsId + " ]";
    }
    
}
