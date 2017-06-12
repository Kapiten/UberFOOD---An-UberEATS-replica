package com.reverside.entity;

import com.reverside.entity.Order1PK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-09T20:14:35")
@StaticMetamodel(Order1.class)
public class Order1_ { 

    public static volatile SingularAttribute<Order1, String> date;
    public static volatile SingularAttribute<Order1, String> orderType;
    public static volatile SingularAttribute<Order1, String> note;
    public static volatile SingularAttribute<Order1, String> orderNo;
    public static volatile SingularAttribute<Order1, String> completionTypeId;
    public static volatile SingularAttribute<Order1, String> recipient;
    public static volatile SingularAttribute<Order1, String> intendedRecipient;
    public static volatile SingularAttribute<Order1, Integer> restaurantId;
    public static volatile SingularAttribute<Order1, Order1PK> order1PK;

}