package imarkusi.soc_net_project.adapters;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import imarkusi.soc_net_project.R;
import imarkusi.soc_net_project.SocNetApp;
import imarkusi.soc_net_project.custom.ItemClickListener;
import imarkusi.soc_net_project.helpers.ImageHelper;
import imarkusi.soc_net_project.models.Movie;

/**
 * Created by markusi on 18/01/16.
 */
public class ExploreListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_MOVIE = 0;

    private static final int VIEW_TYPE_HEADER = 1;

    private List<Movie> topRatedMovies;

    private List<Movie> recommendedMovies;

    private ItemClickListener<Movie> clickListener;

    private List<Movie> items;

    public ExploreListAdapter(List<Movie> topRatedMovies, List<Movie> recommendedMovies, ItemClickListener<Movie> clickListener) {
        this.topRatedMovies = topRatedMovies;
        this.recommendedMovies = recommendedMovies;
        this.clickListener = clickListener;
        processData(topRatedMovies, recommendedMovies);
    }

    private void processData(List<Movie> topRatedMovies, List<Movie> recommendedMovies) {
        items = new ArrayList<>();
        if (topRatedMovies != null && !topRatedMovies.isEmpty()) {
            Movie header = new Movie();
            header.setViewType(Movie.ViewType.HEADER);
            header.setTitle(SocNetApp.getInstance().getString(R.string.top_rated));
            items.add(header);
            for (Movie movie : topRatedMovies) {
                movie.setViewType(Movie.ViewType.MOVIE);
                items.add(movie);
            }
        }
        if (recommendedMovies != null && !recommendedMovies.isEmpty()) {
            Movie header = new Movie();
            header.setViewType(Movie.ViewType.HEADER);
            header.setTitle(SocNetApp.getInstance().getString(R.string.recommended));
            items.add(header);
            for (Movie movie : recommendedMovies) {
                movie.setViewType(Movie.ViewType.MOVIE);
                items.add(movie);
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                return new HeaderViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_explore_list_header, parent, false));

            default:
            case VIEW_TYPE_MOVIE:
                return new MoviesAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_movie, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Movie item = items.get(position);
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_HEADER:
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
                headerViewHolder.header.setText(item.getTitle());
                break;
            default:
            case VIEW_TYPE_MOVIE:
                MoviesAdapter.ViewHolder viewHolder = (MoviesAdapter.ViewHolder) holder;
                MoviesAdapter.setTitle(viewHolder.title, item.getTitle(), item.getReleaseDate());
                if (item.getPosterUrl() != null) {
                    ImageHelper.loadPosterFromApi(viewHolder.poster, item.getPosterUrl());
                }
                MoviesAdapter.setGenres(viewHolder.genres, item.getGenres());
                viewHolder.item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onItemClick(item);
                    }
                });
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        final Movie item = items.get(position);
        switch (item.getViewType()) {
            case MOVIE:
                return VIEW_TYPE_MOVIE;
            default:
            case HEADER:
                return VIEW_TYPE_HEADER;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.header)
        TextView header;


        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
