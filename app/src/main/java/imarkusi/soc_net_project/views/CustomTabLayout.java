package imarkusi.soc_net_project.views;


import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import imarkusi.soc_net_project.R;

/**
 * Created on 26/10/15.
 *
 * @author markusi
 */
public class CustomTabLayout extends RelativeLayout {

    @Bind(R.id.tabs)
    TabLayout tabLayout;

    public CustomTabLayout(Context context) {
        super(context);
        init();
    }

    public CustomTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.tab_layout_custom, this, true);
        ButterKnife.bind(this);
    }

    public void setViewPager(final ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager);
    }
}