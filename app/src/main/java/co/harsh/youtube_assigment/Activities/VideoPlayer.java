package co.harsh.youtube_assigment.Activities;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import co.harsh.youtube_assigment.R;

/**
 * Created by harshkaranpuria on 9/25/15.
 */
public class VideoPlayer extends YouTubeFailureRecoveryActivity {
    String VideoId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playerview_demo);
        VideoId = getIntent().getStringExtra("VideoId");
        YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize("AIzaSyBbqzMwhUA53I4RGDOT0L5rjah6cVdPuIk", this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo(VideoId);
        }
    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }
}
