package imarkusi.soc_net_project.helpers;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import imarkusi.soc_net_project.SocNetApp;

/**
 * Created by markusi on 17/01/16.
 */
public class PreferencesHelper {

    private static final String FACEBOOK_TOKEN = "facebook_token";
    private static final String AUTH_TOKEN = "auth_token";
    private static final String USERNAME = "username";
    private static final String PROFILE_PICTURE_URL = "profile_picture_url";

    private static SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SocNetApp.getInstance());
    private static SharedPreferences.Editor edit;

    private PreferencesHelper() {
    }

    public static void saveFacebookToken(String token) {
        saveStringToPreferences(token, FACEBOOK_TOKEN);
    }

    public static String getAuthToken() {
        return getStringFromPreferences(AUTH_TOKEN);
    }

    public static void saveAuthToken(String authToken) {
        saveStringToPreferences(authToken, AUTH_TOKEN);
    }

    public static String getUsername(){
        return getStringFromPreferences(USERNAME);
    }

    public static void saveUsername(String username) {
        saveStringToPreferences(username, USERNAME);
    }

    public static String getProfilePictureUrl(){
        return getStringFromPreferences(PROFILE_PICTURE_URL);
    }

    public static void saveProfilePictureUrl(String url) {
        saveStringToPreferences(url, PROFILE_PICTURE_URL);
    }

    public static void deleteAllSharedPreferences() {
        edit = preferences.edit();
        edit.clear();
        edit.apply();
    }

    private static void saveStringToPreferences(String value, String key) {
        edit = preferences.edit();
        edit.putString(key, value);
        edit.apply();
    }

    private static String getStringFromPreferences(String name) {
        return preferences.getString(name, null);
    }
}
