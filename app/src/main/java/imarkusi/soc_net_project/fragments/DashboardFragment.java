package imarkusi.soc_net_project.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import imarkusi.soc_net_project.R;
import imarkusi.soc_net_project.activities.LoginActivity;
import imarkusi.soc_net_project.activities.MovieDetailsActivity;
import imarkusi.soc_net_project.adapters.LikedMoviesAdapter;
import imarkusi.soc_net_project.adapters.MoviesAdapter;
import imarkusi.soc_net_project.custom.ItemClickListener;
import imarkusi.soc_net_project.dagger.components.DaggerDashboardComponent;
import imarkusi.soc_net_project.dagger.modules.DashboardModule;
import imarkusi.soc_net_project.helpers.ImageHelper;
import imarkusi.soc_net_project.helpers.PreferencesHelper;
import imarkusi.soc_net_project.models.Movie;
import imarkusi.soc_net_project.mvp.presenters.DashboardPresenter;
import imarkusi.soc_net_project.mvp.views.DashboardView;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;

/**
 * Created by markusi on 18/01/16.
 */
public class DashboardFragment extends BaseFragment implements DashboardView {

    @Bind(R.id.profile_image)
    CircleImageView profileImageView;

    @Bind(R.id.profile_name)
    TextView profileNameTextView;

    @Bind(R.id.profile_email)
    TextView profileEmailTextView;

    @Bind(R.id.watch_list_header)
    View watchListHeader;

    @Bind(R.id.watch_list)
    RecyclerView watchList;

    @Bind(R.id.liked_list_header)
    View likedListHeader;

    @Bind(R.id.liked_list)
    RecyclerView likedList;

    @Bind(R.id.empty_watch_list_placeholder)
    View emptyWatchListPlaceholder;

    @Inject
    DashboardPresenter presenter;

    private ItemClickListener<Movie> itemClickListener = new ItemClickListener<Movie>() {
        @Override
        public void onItemClick(Movie item) {
            startActivity(MovieDetailsActivity.newIntent(getActivity(), item.getId()));
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, view);
        DaggerDashboardComponent.builder().dashboardModule(new DashboardModule(this)).build().inject(this);
        watchListHeader.setVisibility(View.VISIBLE);
        presenter.init();
        return view;
    }

    @OnClick(R.id.logout)
    void logout() {
        Dialog dialog = new android.app.AlertDialog.Builder(getActivity())
                .setTitle(R.string.app_name)
                .setMessage(getActivity().getString(R.string.confirm_logout))
                .setPositiveButton(getActivity().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.logout();
                    }
                }).setNegativeButton(getActivity().getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setCancelable(true)
                .show();
        dialog.setCanceledOnTouchOutside(true);
    }

    @Override
    public void showName(String username) {
        profileNameTextView.setText(username);
    }

    @Override
    public void showEmail(String email) {
        profileEmailTextView.setText(email);
    }

    @Override
    public void showProfilePicture(String profilePictureUrl) {
        ImageHelper.loadImage(profileImageView, profilePictureUrl);
    }

    @Override
    public void showWatchList(List<Movie> movies) {
        watchList.setVisibility(View.VISIBLE);
        MoviesAdapter adapter = new MoviesAdapter(movies, itemClickListener);
        watchList.setHasFixedSize(true);
        watchList.setAdapter(new SlideInRightAnimationAdapter(new AlphaInAnimationAdapter(adapter)));
        watchList.setLayoutManager(new LinearLayoutManager(getActivity()));
        emptyWatchListPlaceholder.setVisibility(View.GONE);
    }

    @Override
    public void showLikedList(List<Movie> movies) {
        likedList.setVisibility(View.VISIBLE);
        LikedMoviesAdapter adapter = new LikedMoviesAdapter(movies, itemClickListener);
        likedList.setHasFixedSize(true);
        likedList.setAdapter(new SlideInRightAnimationAdapter(new AlphaInAnimationAdapter(adapter)));
        likedList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        likedListHeader.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLogoutSuccessful() {
        getActivity().finish();
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

    @OnClick(R.id.profile_image)
    void onProfileImageClicked(){
        final String facebookUrl = "https://www.facebook.com/";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(facebookUrl + PreferencesHelper.getUserId()));
        startActivity(intent);
    }
}
