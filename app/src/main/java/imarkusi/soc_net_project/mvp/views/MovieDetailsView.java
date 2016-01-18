package imarkusi.soc_net_project.mvp.views;

import java.util.List;

import imarkusi.soc_net_project.models.Comment;

/**
 * Created by markusi on 18/01/16.
 */
public interface MovieDetailsView extends BaseView {

    void setPoster(String posterUrl);

    void setTitle(String title);

    void setDate(String date);

    void setGenres(String genres);

    void setOverview(String overview);

    void setRating(String  rating);

    void displayComments(List<Comment> comments);

    void onMovieCommentUpdated();
}
