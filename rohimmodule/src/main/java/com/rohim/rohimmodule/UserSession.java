package com.rohim.rohimmodule;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class UserSession {
    SharedPreferences preferences;
    Editor editor;
    Context mContext;
    public static final String SP_APP = "module";

    public static final String SP_USER_ID = "user_id";
    public static final String SP_USERNAME = "username";
    public static final String SP_NAMA = "nama";
    public static final String SP_EMAIL = "email";
    public static final String SP_KONTAK = "kontak";
    public static final String SP_ALAMAT = "kontak";
    public static final String SP_FP = "foto_profil";
    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    public UserSession(Context context){
        preferences = context.getSharedPreferences(SP_APP, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void saveSPString(String keySP, String value){
        editor.putString(keySP, value);
        editor.commit();
    }

    public void saveSPInt(String keySP, int value){
        editor.putInt(keySP, value);
        editor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        editor.putBoolean(keySP, value);
        editor.commit();
    }

    public String getSpUserId(){
        return preferences.getString(SP_USER_ID, "");
    }

    public String getSpUsername(){
        return preferences.getString(SP_USERNAME, "");
    }

    public String getSpNama(){
        return preferences.getString(SP_NAMA, "");
    }

    public String getSpKontak(){
        return preferences.getString(SP_KONTAK, "");
    }

    public String getSpEmail(){
        return preferences.getString(SP_EMAIL, "");
    }

    public String getSpFp(){
        return preferences.getString(SP_FP, "");
    }

    public String getSpAlamat(){
        return preferences.getString(SP_ALAMAT, "");
    }

    public Boolean getSPSudahLogin(){
        return preferences.getBoolean(SP_SUDAH_LOGIN, false);
    }

}

