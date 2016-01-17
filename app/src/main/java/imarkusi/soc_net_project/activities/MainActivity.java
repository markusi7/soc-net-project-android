package imarkusi.soc_net_project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import imarkusi.soc_net_project.R;
import imarkusi.soc_net_project.helpers.PreferencesHelper;

/**
 * Created by markusi on 16/01/16.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (PreferencesHelper.getAuthToken() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
