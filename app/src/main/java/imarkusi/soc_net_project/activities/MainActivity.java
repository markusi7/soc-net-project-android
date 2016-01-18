package imarkusi.soc_net_project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import imarkusi.soc_net_project.R;
import imarkusi.soc_net_project.custom.ViewPagerAdapter;
import imarkusi.soc_net_project.custom.ViewPagerListener;
import imarkusi.soc_net_project.fragments.DashboardFragment;
import imarkusi.soc_net_project.fragments.ExploreFragment;
import imarkusi.soc_net_project.helpers.PreferencesHelper;
import imarkusi.soc_net_project.views.CustomTabLayout;

/**
 * Created by markusi on 16/01/16.
 */
public class MainActivity extends BaseActivity {

    public static final int REQUEST_CODE_SEARCH = 0xB00B;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.tab_layout)
    CustomTabLayout tabLayout;

    @Bind(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (PreferencesHelper.getAuthToken() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        initTabs();
    }

    private void initTabs() {
        ViewPagerAdapter pagerAdapter =  new ViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new DashboardFragment(), getString(R.string.dashboard));
        pagerAdapter.addFragment(new ExploreFragment(), getString(R.string.explore));
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(pagerAdapter.getTabIndex(), true);
        tabLayout.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPagerListener(viewPager));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.search) {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivityForResult(intent, REQUEST_CODE_SEARCH);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_SEARCH && resultCode == RESULT_OK) {
            //TODO fetch movie by id
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void initToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setDisplayShowTitleEnabled(false);
            }
            toolbar.setTitle(R.string.app_name);
        }
    }
}
