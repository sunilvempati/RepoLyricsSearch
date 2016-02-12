package com.sunil.test.musicsearchapp.ws;

import com.sunil.test.musicsearchapp.pojo.LyricsResponse;
import com.sunil.test.musicsearchapp.pojo.TrackResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;


public interface LyricsApi {

    @GET("/api.php?action=lyrics&fmt=json")
    void searchTracks(@Query("artist") String artist,@Query("song") String trackName,
                      Callback<LyricsResponse> callback);

}
