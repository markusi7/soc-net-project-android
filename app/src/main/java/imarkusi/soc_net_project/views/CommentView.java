package imarkusi.soc_net_project.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import imarkusi.soc_net_project.R;

/**
 * Created by markusi on 18/01/16.
 */
public class CommentView extends LinearLayout {

    @Bind(R.id.username)
    TextView usernameTextView;

    @Bind(R.id.comment)
    TextView commentTextView;

    public CommentView(Context context, String username, String comment) {
        super(context);
        init();
        setValues(username, comment);
    }

    public CommentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CommentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_comment, this, true);
        ButterKnife.bind(this, view);
    }

    private void setValues(String username, String comment) {
        usernameTextView.setText(username);
        commentTextView.setText(comment);
    }
}
