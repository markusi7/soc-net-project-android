package imarkusi.soc_net_project.adapters;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import imarkusi.soc_net_project.R;
import imarkusi.soc_net_project.custom.ItemClickListener;
import imarkusi.soc_net_project.helpers.ImageHelper;
import imarkusi.soc_net_project.models.Movie;

/**
 * Created by markusi on 18/01/16.
 */
public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movie> items;

    private ItemClickListener<Movie> clickListener;

    public MoviesAdapter(List<Movie> items, ItemClickListener<Movie> clickListener) {
        this.items = items;
        this.clickListener = clickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Movie item = items.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        setTitle(viewHolder.title, item.getTitle(), item.getReleaseDate());
        if (item.getPosterUrl() != null) {
            ImageHelper.loadPosterFromApi(viewHolder.poster, item.getPosterUrl());
        } else {
            viewHolder.poster.setImageDrawable(ContextCompat.getDrawable(viewHolder.poster.getContext(), R.drawable.movie_search_placeholder));
        }
        setGenres(viewHolder.genres, item.getGenres());
        viewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(item);
            }
        });
    }

    public static void setTitle(TextView titleTextView, String title, String releaseDate) {
        String text = title;
        if (releaseDate != null && releaseDate.length() > 0) {
            final String[] splitReleaseDate = releaseDate.split("-");
            if (splitReleaseDate.length > 0) {
                text = text.concat(" (" + splitReleaseDate[0] + ")");
            }
        }
        titleTextView.setText(text);
    }

    public static void setGenres(TextView genresTextView, List<String> genres) {
        genresTextView.setText(generateGenresText(genres));
    }

    public static String generateGenresText(List<String> genres){
        String output = "";
        final String comma = ", ";
        for (String genre : genres) {
            output = output.concat(genre + comma);
        }
        if (genres.size() > 0) {
            output = output.substring(0, output.length() - comma.length());
        }
        return output;
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item)
        View item;

        @Bind(R.id.poster)
        ImageView poster;

        @Bind(R.id.header)
        TextView title;

        @Bind(R.id.genres)
        TextView genres;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
