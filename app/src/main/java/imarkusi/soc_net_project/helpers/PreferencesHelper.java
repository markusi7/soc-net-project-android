package imarkusi.soc_net_project.helpers;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

import imarkusi.soc_net_project.SocNetApp;

/**
 * Created by markusi on 17/01/16.
 */
public class PreferencesHelper {

    private static final String USER_ID = "user_id";
    private static final String FACEBOOK_TOKEN = "facebook_token";
    private static final String AUTH_TOKEN = "auth_token";
    private static final String USERNAME = "username";
    private static final String WATCHLIST_IDS = "watchlist_ids";
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

    public static String getUsername() {
        return getStringFromPreferences(USERNAME);
    }

    public static void setUsername(String username) {
        saveStringToPreferences(username, USER_ID);
    }

    public static String getUserId() {
        return getStringFromPreferences(USER_ID);
    }

    public static void saveUserId(String userId) {
        saveStringToPreferences(userId, USER_ID);
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

    public static Set<String> getWatchListIds() {
        return preferences.getStringSet(WATCHLIST_IDS, null);
    }

    public static void saveWatchlistIds(Set<String> ids) {
        edit = preferences.edit();
        edit.putStringSet(WATCHLIST_IDS, ids);
        edit.apply();
    }
}
