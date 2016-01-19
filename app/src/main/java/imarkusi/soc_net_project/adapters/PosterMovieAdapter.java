package imarkusi.soc_net_project.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
public class PosterMovieAdapter extends RecyclerView.Adapter<PosterMovieAdapter.ViewHolder> {

    private List<Movie> items;

    private ItemClickListener<Movie> clickListener;

    public PosterMovieAdapter(List<Movie> items, ItemClickListener<Movie> clickListener) {
        this.items = items;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_poster, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Movie item = items.get(position);
        ImageHelper.loadPosterFromApi(holder.image, item.getPosterUrl());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.image)
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
