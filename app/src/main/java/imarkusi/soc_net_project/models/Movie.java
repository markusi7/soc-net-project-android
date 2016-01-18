package imarkusi.soc_net_project.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by markusi on 18/01/16.
 */
public class Movie {

    @SerializedName("_id")
    private String id;

    @SerializedName("poster_path")
    private String posterUrl;

    @SerializedName("overview")
    private String overview;

    @SerializedName("title")
    private String title;

    @SerializedName("genres")
    private List<String > genres;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("watched")
    List<Comment> comments;

    private ViewType viewType;

    public String getId() {
        return id;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getOverview() {
        return overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String > getGenres() {
        return genres;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public ViewType getViewType() {
        return viewType;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setViewType(ViewType viewType) {
        this.viewType = viewType;
    }

    public enum ViewType {
        MOVIE, HEADER
    }
}
