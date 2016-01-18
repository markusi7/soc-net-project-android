package imarkusi.soc_net_project.custom;

import android.support.v4.view.ViewPager;

/**
 * Created on 22/09/15.
 *
 * @author markusi
 */

public class ViewPagerListener implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;

    public ViewPagerListener(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        ((ViewPagerAdapter) viewPager.getAdapter()).setTabIndex(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
