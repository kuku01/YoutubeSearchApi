package co.harsh.youtube_assigment.Utils;

import co.harsh.youtube_assigment.GettersandSetters.CategoriesList;
import co.harsh.youtube_assigment.GettersandSetters.Videos;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface NetworkCalls {
    @GET("/youtube/v3/videoCategories")
    void getCategories(@Query("key") String key,@Query("part") String part,@Query("regionCode") String regionCode, Callback<CategoriesList> categoriesListCallback);

    @GET("/youtube/v3/search/")
    void getVideos(@Query("key") String key,@Query("pageToken") String pageToken,@Query("part") String part,@Query("maxResults") String maxResults,@Query("type") String type,@Query("videoCategoryId") String videoCategoryId, Callback<Videos> videosCallback );

}
