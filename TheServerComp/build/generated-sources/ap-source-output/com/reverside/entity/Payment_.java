package com.reverside.entity;

import com.reverside.entity.PaymentPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-09T20:14:35")
@StaticMetamodel(Payment.class)
public class Payment_ { 

    public static volatile SingularAttribute<Payment, String> cvv;
    public static volatile SingularAttribute<Payment, PaymentPK> paymentPK;
    public static volatile SingularAttribute<Payment, String> cardType;
    public static volatile SingularAttribute<Payment, String> expireDate;
    public static volatile SingularAttribute<Payment, String> paypalId;
    public static volatile SingularAttribute<Payment, String> cardNo;

}