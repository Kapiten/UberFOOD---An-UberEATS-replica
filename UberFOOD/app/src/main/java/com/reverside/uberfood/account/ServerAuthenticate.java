package com.reverside.uberfood.account;

import com.reverside.uberfood.entity.Contact;
import com.reverside.uberfood.entity.Person;
import com.reverside.uberfood.entity.User;

/**
 * Created by StrettO on 2017/06/16.
 */

public interface ServerAuthenticate {

    public String userSignUp(User user, Person person, Contact contact, String authType) throws Exception;
    public String userSignIn(User user, String authType) throws Exception;
    public void setLoginResponse(ServerComAuthenticate.LoginResponse loginResponse);

}
