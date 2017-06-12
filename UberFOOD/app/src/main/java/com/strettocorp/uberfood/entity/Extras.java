package com.strettocorp.uberfood.entity;

/**
 * Created by reversidesoftwaresolutions on 2017/06/08.
 */

public class Extras {
    public Integer mExtrasId;
    public String mPrice;
    public String mName;
    public String mTypeId;
    public String mRequired;

    public Integer getExtrasId() {
        return mExtrasId;
    }

    public void setExtrasId(Integer extrasId) {
        mExtrasId = extrasId;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getTypeId() {
        return mTypeId;
    }

    public void setTypeId(String typeId) {
        mTypeId = typeId;
    }

    public String getRequired() {
        return mRequired;
    }

    public void setRequired(String required) {
        mRequired = required;
    }
}
