package com.sunil.test.musicsearchapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;
import com.sunil.test.musicsearchapp.Utilities.CleanGsonConverter;
import com.sunil.test.musicsearchapp.pojo.LyricsResponse;
import com.sunil.test.musicsearchapp.ws.LyricsApi;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LyricsActivity extends AppCompatActivity {

    public static final String KEY_TRACK_NAME ="KEY_TRACK_NAME" ;
    public static final String KEY_ARTIST_NAME ="KEY_ARTIST_NAME" ;
    public static final String KEY_DURATION ="KEY_DURATION" ;
    public static final String KEY_THUMB_URL ="KEY_THUMB_URL" ;

    TextView title,artist,duration,tracklyrics;
    ImageView thumb_image;

    String strArtistName="",strTrackName="";
    private final String lyricsUrl="http://lyrics.wikia.com";

    //http://lyrics.wikia.com/api.php?action=lyrics&artist=Joe%20Bonamassa&song=So%20Many%20Roads&fmt=xml&func=getSong

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);

        title = (TextView) findViewById(R.id.title); // title
        artist = (TextView) findViewById(R.id.artist); // artist name
        duration = (TextView) findViewById(R.id.duration); // duration
        thumb_image = (ImageView) findViewById(R.id.imageView); // thumb image
        tracklyrics = (TextView) findViewById(R.id.tracklyrics); // lyrics


        strTrackName = getIntent().getStringExtra(KEY_TRACK_NAME);
        strArtistName = getIntent().getStringExtra(KEY_ARTIST_NAME);
        String strDuration = getIntent().getStringExtra(KEY_DURATION);
        String url = getIntent().getStringExtra(KEY_THUMB_URL);

        title.setText(strTrackName);
        artist.setText(strArtistName);
        duration.setText(strDuration);
        Picasso.with(this).load(url).into(thumb_image);

        GsonBuilder gsonBuilder=new GsonBuilder();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(lyricsUrl)
                        .setConverter(new CleanGsonConverter( gsonBuilder.create()))
                                .build();
        LyricsApi restInterface = restAdapter.create(LyricsApi.class);
        restInterface.searchTracks(strArtistName, strTrackName, new Callback<LyricsResponse>() {

            @Override
            public void success(LyricsResponse lyricsResponse, Response response) {
                if (lyricsResponse != null){
                    tracklyrics.setText(lyricsResponse.getLyrics());
                }
            }

            @Override
            public void failure(RetrofitError error) {
            String strError=error.getMessage();
                tracklyrics.setText("error:"+strError);
            }
        });
    }
}
