package com.sunil.test.musicsearchapp;

import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.sunil.test.musicsearchapp.pojo.TrackResponse;
import com.sunil.test.musicsearchapp.ws.Api;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class SongsListActivity extends AppCompatActivity {

    public static final String KEY_SEARCH_TRACK ="KEY_SEARCH_TRACK";
    private String searchString=null;
    private TextView emptyTextView;
    private View progressBar;
    private ListView listView;
    private TrackListAdapter adapter;
    private final String searchUrl="https://itunes.apple.com";


    @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songs_list);
         searchString= getIntent().getStringExtra(KEY_SEARCH_TRACK);
        emptyTextView =(TextView) findViewById(R.id.textView1);
        progressBar = findViewById(R.id.progressBar1);

        adapter = new TrackListAdapter(this);
        listView =(ListView)findViewById(R.id.songslist);
        listView.setAdapter(adapter);

        progressBar.setVisibility(View.VISIBLE);
        emptyTextView.setVisibility(View.GONE);
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(searchUrl).build();
        Api restInterface = restAdapter.create(Api.class);
        restInterface.searchTracks(searchString, new Callback<TrackResponse>() {
            @Override
            public void success(TrackResponse searchResp, Response response) {
                progressBar.setVisibility(View.GONE);
                if (searchResp != null){
                    if(searchResp.getResults().size()>0)
                         adapter.setData(searchResp.getResults());
                    else
                        emptyTextView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                progressBar.setVisibility(View.GONE);
                String strMsg = error.getMessage();
                emptyTextView.setVisibility(View.VISIBLE);
                emptyTextView.setText(strMsg);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
