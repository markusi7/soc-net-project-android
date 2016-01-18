package imarkusi.soc_net_project.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import imarkusi.soc_net_project.R;
import imarkusi.soc_net_project.activities.MovieDetailsActivity;
import imarkusi.soc_net_project.adapters.ExploreListAdapter;
import imarkusi.soc_net_project.custom.ItemClickListener;
import imarkusi.soc_net_project.dagger.components.DaggerExploreComponent;
import imarkusi.soc_net_project.dagger.modules.ExploreModule;
import imarkusi.soc_net_project.models.Movie;
import imarkusi.soc_net_project.mvp.presenters.ExplorePresenter;
import imarkusi.soc_net_project.mvp.views.ExploreView;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;

/**
 * Created by markusi on 18/01/16.
 */
public class ExploreFragment extends BaseFragment implements ExploreView {

    @Bind(R.id.list)
    RecyclerView list;

    @Inject
    ExplorePresenter presenter;

    private ItemClickListener<Movie> itemClickListener = new ItemClickListener<Movie>() {
        @Override
        public void onItemClick(Movie item) {
            startActivity(MovieDetailsActivity.newIntent(getActivity(), item.getId()));
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        ButterKnife.bind(this, view);

        DaggerExploreComponent.builder().exploreModule(new ExploreModule(this)).build().inject(this);

        presenter.getRecommendedMovies();
        return view;
    }

    @Override
    public void showRecommendedMovies(List<Movie> topRatedMovies, List<Movie> recommendedMovies) {
        ExploreListAdapter adapter = new ExploreListAdapter(topRatedMovies, recommendedMovies, itemClickListener);
        list.setHasFixedSize(true);
        list.setAdapter(new SlideInRightAnimationAdapter(new AlphaInAnimationAdapter(adapter)));
        list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }
}
