package imarkusi.soc_net_project.helpers;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import timber.log.Timber;

/**
 * Created by markusi on 18/01/16.
 */
public class ImageHelper {

    private static final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/";

    public static final String SIZE = "w500";

    private ImageHelper() {
    }

    public static void loadImageFromApi(ImageView view, String imageUrl) {
        String url = POSTER_BASE_URL + SIZE + imageUrl;
        Timber.d("url: %s", url);
        Glide.with(view.getContext()).load(url).crossFade().into(view);
    }
}
