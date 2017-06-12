package com.reverside.entity;

import com.reverside.entity.StoreAddressPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-09T20:14:35")
@StaticMetamodel(StoreAddress.class)
public class StoreAddress_ { 

    public static volatile SingularAttribute<StoreAddress, String> unitNo;
    public static volatile SingularAttribute<StoreAddress, String> code;
    public static volatile SingularAttribute<StoreAddress, StoreAddressPK> storeAddressPK;
    public static volatile SingularAttribute<StoreAddress, String> city;
    public static volatile SingularAttribute<StoreAddress, String> suburb;
    public static volatile SingularAttribute<StoreAddress, String> type;
    public static volatile SingularAttribute<StoreAddress, String> streetame;
    public static volatile SingularAttribute<StoreAddress, String> gpsCoord;

}