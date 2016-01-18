package imarkusi.soc_net_project.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import imarkusi.soc_net_project.R;
import imarkusi.soc_net_project.dagger.components.DaggerMovieDetailsComponent;
import imarkusi.soc_net_project.dagger.modules.MovieDetailsModule;
import imarkusi.soc_net_project.helpers.ImageHelper;
import imarkusi.soc_net_project.models.Comment;
import imarkusi.soc_net_project.mvp.presenters.MovieDetailsPresenter;
import imarkusi.soc_net_project.mvp.views.MovieDetailsView;
import imarkusi.soc_net_project.views.CommentView;

/**
 * Created by markusi on 18/01/16.
 */
public class MovieDetailsActivity extends BaseActivity implements MovieDetailsView {

    public static final String MOVIE_ID = "movie_id";

    @Bind(R.id.app_bar)
    AppBarLayout appBarLayout;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @Bind(R.id.header)
    TextView headerTextView;

    @Bind(R.id.poster)
    ImageView posterImageView;

    @Bind(R.id.title)
    TextView titleTextView;

    @Bind(R.id.date_released)
    TextView dateReleasedTextView;

    @Bind(R.id.genres)
    TextView genresTextView;

    @Bind(R.id.overview)
    TextView overviewTextView;

    @Bind(R.id.rating)
    TextView ratingTextView;

    @Bind(R.id.rating_bar)
    RatingBar ratingBar;

    @Bind(R.id.comments)
    LinearLayout commentsLayout;

    @Bind(R.id.comments_placehodler)
    View commentsPlaceholder;

    @Bind(R.id.comment_edit_text)
    EditText commentEditText;

    @Inject
    MovieDetailsPresenter presenter;

    String title;

    String movieId;

    public static Intent newIntent(Context context, String movieId) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra(MOVIE_ID, movieId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        DaggerMovieDetailsComponent.builder().movieDetailsModule(new MovieDetailsModule(this)).build().inject(this);
        movieId = getIntent().getExtras().getString(MOVIE_ID);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                //c/p from decompiled CollapsingToolbarLayout, this boolean value starts scrim animation
                if (collapsingToolbar.getHeight() + i < 2 * ViewCompat.getMinimumHeight(collapsingToolbar)) {
                    collapsingToolbar.setTitle(title);
                } else {
                    collapsingToolbar.setTitle("");
                }
            }
        });
        if (movieId != null) {
            presenter.getMovieDetails(movieId);
        }
    }

    @Override
    public void setPoster(String posterUrl) {
        ImageHelper.loadPosterFromApi(posterImageView, posterUrl);
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
        headerTextView.setText(title);
        titleTextView.setText(title);
    }

    @Override
    public void setDate(String date) {
        dateReleasedTextView.setText(date);
    }

    @Override
    public void setGenres(String genres) {
        genresTextView.setText(genres);
    }

    @Override
    public void setOverview(String overview) {
        overviewTextView.setText(overview);
    }

    @Override
    public void setRating(String rating) {
        ratingTextView.setText(rating);
    }

    @Override
    public void displayComments(List<Comment> comments) {
        commentsPlaceholder.setVisibility(View.GONE);
        for (Comment comment : comments) {
            CommentView commentView = new CommentView(this, comment.getUser(), comment.getComment());
            commentsLayout.addView(commentView);
        }
    }

    @Override
    public void onMovieCommentUpdated() {
        commentEditText.setText("");
        presenter.getMovieDetails(movieId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.comment_button)
    void onCommentPosted() {
        if (!commentEditText.getText().toString().isEmpty()) {
            presenter.postComment(movieId, commentEditText.getText().toString(), String.valueOf(ratingBar.getRating()));
        }
    }

    @OnClick(R.id.rating_button)
    void onRatingClicked() {
        if (Float.compare(ratingBar.getRating(), 0) == 0) {
            Snackbar.make(appBarLayout, R.string.not_rated, Snackbar.LENGTH_LONG).show();
        } else {
            presenter.postComment(movieId, null, String.valueOf(ratingBar.getRating()));
        }
    }

    @OnClick(R.id.add_to_watch_list)
    void addToWatchList(){
    }
}
