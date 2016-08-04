package co.harsh.youtube_assigment.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.harsh.youtube_assigment.GettersandSetters.Videos;
import co.harsh.youtube_assigment.R;
import co.harsh.youtube_assigment.Utils.NetworkUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by harshkaranpuria on 9/25/15.
 */
public class VideoCategorized extends AppCompatActivity {
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videocategorized);
        final ListView videosLV = (ListView) findViewById(R.id.lvVideos);
        NetworkUtils mNetworkUtils = new NetworkUtils(this);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Logging in, Please wait.");
        mProgressDialog.setCancelable(false);
        if(mNetworkUtils.isnetconnected()) {
            mProgressDialog.show();
            mNetworkUtils.getapi().getVideos("AIzaSyBbqzMwhUA53I4RGDOT0L5rjah6cVdPuIk","","snippet", "25","video",getIntent().getStringExtra("categoryId"), new Callback<Videos>() {
                @Override
                public void success(final Videos videoList, Response response) {
                    mProgressDialog.dismiss();
                    ArrayList<Videos> videos = new ArrayList<>();

                    for (int i = 0;i <videoList.getItems().size();i++){
                        videos.add(videoList);
                    }
                    PageAdapter adapter = new PageAdapter(getApplicationContext(),videos);
                    videosLV.setAdapter(adapter);
                    videosLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent f = new Intent(getApplicationContext(),VideoPlayer.class);
                            f.putExtra("VideoId",videoList.getItems().get(i).getId().getVideoId());
                            startActivity(f);
                        }

                    });

                }

                @Override
                public void failure(RetrofitError error) {
                    mProgressDialog.dismiss();
                    Log.d("Error:", error.toString());
                }
            });
        }else {
            Snackbar.make(videosLV, "No Network Detected", Snackbar.LENGTH_SHORT).show();
        }
    }


    private static final class PageAdapter extends BaseAdapter {
        private final List<Videos> entries;
        private final Map<YouTubeThumbnailView, YouTubeThumbnailLoader> thumbnailViewToLoaderMap;
        private final LayoutInflater inflater;
        private final ThumbnailListener thumbnailListener;
        public PageAdapter(Context context, List<Videos> entries) {
            this.entries = entries;
            thumbnailViewToLoaderMap = new HashMap<>();
            inflater = LayoutInflater.from(context);
            thumbnailListener = new ThumbnailListener();
        }



        @Override
        public int getCount() {
            return entries.size();
        }

        @Override
        public Videos getItem(int position) {
            return entries.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            Videos entry = entries.get(position);

             if (view == null) {
                view = inflater.inflate(R.layout.list_item_videos, parent, false);
                YouTubeThumbnailView thumbnail = (YouTubeThumbnailView) view.findViewById(R.id.thumbnail);
                thumbnail.setTag(entry.getItems().get(position).getId().getVideoId());
                thumbnail.initialize("AIzaSyBbqzMwhUA53I4RGDOT0L5rjah6cVdPuIk", thumbnailListener);
            } else {
                YouTubeThumbnailView thumbnail = (YouTubeThumbnailView) view.findViewById(R.id.thumbnail);
                YouTubeThumbnailLoader loader = thumbnailViewToLoaderMap.get(thumbnail);
                if (loader == null) {
                    thumbnail.setTag(entry.getItems().get(position).getId().getVideoId());
                } else {
                    thumbnail.setImageResource(R.drawable.loading_thumbnail);
                    loader.setVideo(entry.getItems().get(position).getId().getVideoId());
                }
            }
            TextView label = ((TextView) view.findViewById(R.id.nameText));
            label.setText(entry.getItems().get(position).getSnippet().getTitle());
            return view;
        }

        private final class ThumbnailListener implements
                YouTubeThumbnailView.OnInitializedListener,
                YouTubeThumbnailLoader.OnThumbnailLoadedListener {

            @Override
            public void onInitializationSuccess(
                    YouTubeThumbnailView view, YouTubeThumbnailLoader loader) {
                loader.setOnThumbnailLoadedListener(this);
                thumbnailViewToLoaderMap.put(view, loader);
                view.setImageResource(R.drawable.loading_thumbnail);
                String videoId = (String) view.getTag();
                loader.setVideo(videoId);
            }

            @Override
            public void onInitializationFailure(
                    YouTubeThumbnailView view, YouTubeInitializationResult loader) {
                view.setImageResource(R.drawable.no_thumbnail);
            }

            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView view, String videoId) {
            }

            @Override
            public void onThumbnailError(YouTubeThumbnailView view, YouTubeThumbnailLoader.ErrorReason errorReason) {
                view.setImageResource(R.drawable.no_thumbnail);
            }
        }

    }



}
