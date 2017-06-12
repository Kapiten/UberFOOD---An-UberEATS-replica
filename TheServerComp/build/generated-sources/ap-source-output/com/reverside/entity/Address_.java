package com.reverside.entity;

import com.reverside.entity.AddressPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-09T20:14:35")
@StaticMetamodel(Address.class)
public class Address_ { 

    public static volatile SingularAttribute<Address, String> unitNo;
    public static volatile SingularAttribute<Address, String> code;
    public static volatile SingularAttribute<Address, String> city;
    public static volatile SingularAttribute<Address, String> suburb;
    public static volatile SingularAttribute<Address, String> type;
    public static volatile SingularAttribute<Address, String> streetame;
    public static volatile SingularAttribute<Address, AddressPK> addressPK;
    public static volatile SingularAttribute<Address, String> gpsCoord;

}