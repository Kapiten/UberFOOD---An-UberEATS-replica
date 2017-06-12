package com.reverside.entity;

import com.reverside.entity.PromotionPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-09T20:14:35")
@StaticMetamodel(Promotion.class)
public class Promotion_ { 

    public static volatile SingularAttribute<Promotion, String> requirements;
    public static volatile SingularAttribute<Promotion, String> promoType;
    public static volatile SingularAttribute<Promotion, String> dateTo;
    public static volatile SingularAttribute<Promotion, String> description;
    public static volatile SingularAttribute<Promotion, String> promoCode;
    public static volatile SingularAttribute<Promotion, String> title;
    public static volatile SingularAttribute<Promotion, String> dateFrom;
    public static volatile SingularAttribute<Promotion, PromotionPK> promotionPK;

}