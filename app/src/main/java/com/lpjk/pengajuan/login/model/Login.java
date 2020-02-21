package org.pindaiaja.pindaiapp.login.model;

import android.text.TextUtils;

public class Login implements  InterfaceLogin{
    String username, password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int isValidData() {
        if(TextUtils.isEmpty(getUsername())) {
            return 0;
//        } else if(getPassword().length() <= 6) {
//            return 1;
        } else {
            return -1;
        }
    }

}
