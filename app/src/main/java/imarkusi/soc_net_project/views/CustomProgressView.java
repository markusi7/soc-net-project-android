package imarkusi.soc_net_project.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import imarkusi.soc_net_project.R;

/**
 * Created on 20/10/15.
 *
 * @author markusi
 */
public class CustomProgressView extends RelativeLayout {

    private static final int DEGREES_360 = 360;

    private static final float HALF_ROTATION = 0.5f;

    private static final int DURATION = 1500;

    @Bind(R.id.loader)
    ImageView loader;

    public CustomProgressView(Context context) {
        super(context);
        init();
    }

    public CustomProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomProgressView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        View view = inflate(getContext(), R.layout.custom_progress_dialog, null);
        ButterKnife.bind(this, view);
        addView(view);
        if (!isInEditMode()) {
            RotateAnimation rotate = new RotateAnimation(0, DEGREES_360,
                    Animation.RELATIVE_TO_SELF, HALF_ROTATION, Animation.RELATIVE_TO_SELF, HALF_ROTATION);
            rotate.setDuration(DURATION);
            rotate.setRepeatCount(Animation.INFINITE);
            loader.startAnimation(rotate);
        }
    }
}