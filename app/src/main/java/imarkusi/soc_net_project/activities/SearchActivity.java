package imarkusi.soc_net_project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import imarkusi.soc_net_project.R;
import imarkusi.soc_net_project.adapters.SearchAdapter;
import imarkusi.soc_net_project.custom.ItemClickListener;
import imarkusi.soc_net_project.dagger.components.DaggerSearchComponent;
import imarkusi.soc_net_project.dagger.modules.SearchModule;
import imarkusi.soc_net_project.models.Movie;
import imarkusi.soc_net_project.mvp.presenters.SearchPresenter;
import imarkusi.soc_net_project.mvp.views.SearchView;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;

/**
 * Created by markusi on 17/01/16.
 */
public class SearchActivity extends BaseActivity implements SearchView {

    public static final String MOVIE_ID = "movie_id";

    private static final int DELAY = 500;

    @Bind(R.id.list)
    RecyclerView list;

    @Bind(R.id.search_list_placeholder)
    TextView placeholder;

    @Bind(R.id.search_view)
    MaterialSearchView searchView;

    @Inject
    SearchPresenter presenter;

    private ItemClickListener<Movie> itemClickListener = new ItemClickListener<Movie>() {
        @Override
        public void onItemClick(Movie item) {
            Intent intent = new Intent();
            intent.putExtra(MOVIE_ID, item.getId());
            setResult(RESULT_OK, intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        DaggerSearchComponent.builder().searchModule(new SearchModule(this)).build().inject(this);

        setupSearchView();
    }

    private void setupSearchView() {
        searchView.setVoiceSearch(true);
        searchView.setBackgroundColor(ContextCompat.getColor(this, R.color.primary));
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null && query.length() > 0) {
                    presenter.search(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setHint(getString(R.string.search));
        ImageButton backButton = ButterKnife.findById(this, R.id.action_up_btn);
        backButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_arrow_back));
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

    @Override
    public void showMovies(List<Movie> movies) {
        SearchAdapter adapter = new SearchAdapter(movies, itemClickListener);
        list.setAdapter(new SlideInRightAnimationAdapter(new AlphaInAnimationAdapter(adapter)));
        list.setLayoutManager(new LinearLayoutManager(this));
        placeholder.setVisibility(View.GONE);
        list.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNoMoviesFound() {
        list.setVisibility(View.GONE);
        placeholder.setText(R.string.search_list_empty);
        placeholder.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                searchView.showSearch();
            }
        }, DELAY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            List<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWord = matches.get(0);
                if (!TextUtils.isEmpty(searchWord)) {
                    searchView.setQuery(searchWord, true);
                }
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
