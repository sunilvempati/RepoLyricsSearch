package com.sunil.test.musicsearchapp.ws;

import com.sunil.test.musicsearchapp.pojo.TrackResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;




public interface Api {

    @GET("/search")
    void searchTracks(@Query("term") String term,
                      Callback<TrackResponse> callback);


}
