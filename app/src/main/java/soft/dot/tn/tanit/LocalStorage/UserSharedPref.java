package soft.dot.tn.tanit.LocalStorage;

import android.content.SharedPreferences;

import soft.dot.tn.tanit.Entitites.User;

/**
 * Created by sofien on 06/02/2018.
 */

public class UserSharedPref {
    public static final String USER_ID = "USER_ID";
    public static final String EMAIL = "EMAIL";
    public static final String USER_FIRST_NAME = "USER_FIRST_NAME";
    public static final String USER_LAST_NAME = "USER_LAST_NAME";
    public static final String USER_AGE = "USER_AGE";
    public static final String USER_PASSWRD = "USER_PASSWRD";
    SharedPreferences sharedPreferences;
    public static final String USER_FILE = "user";


    //    public static final String  ;
    public static final String LIKES = "LikesSet";

    public UserSharedPref(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public boolean insertString(String label, String newText) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(label, newText);
        editor.apply();
        return editor.commit();
    }

    public String getString(String label) {
        return sharedPreferences.getString(label, "");
    }

    public boolean insertInt(String label, int newInt) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(label, newInt);
        editor.apply();
        return editor.commit();
    }

    public boolean inserLong(String label, long newInt) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(label, newInt);
        editor.apply();
        return editor.commit();
    }

    public int getInt(String label) {
        return sharedPreferences.getInt(label, -1);
    }

    public static void LogOut(SharedPreferences sharedPreferences) {

        sharedPreferences.edit().clear().apply();
    }

    public void logIn(User user) {
        this.insertString(UserSharedPref.USER_FIRST_NAME, user.getFirstName());
        insertString(UserSharedPref.USER_LAST_NAME, user.getLastName());
        insertString(UserSharedPref.USER_PASSWRD, user.getPassword());
        insertString(UserSharedPref.EMAIL, user.getEmail());
        insertString(UserSharedPref.USER_AGE, user.getAge());
        inserLong(UserSharedPref.USER_ID, user.getId());

    }

}
