package com.sunil.test.musicsearchapp;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.sunil.test.musicsearchapp.Utilities.Utils;
import com.sunil.test.musicsearchapp.pojo.Result;

import java.util.List;


public class TrackListAdapter extends ArrayAdapter<Result> {

    private static LayoutInflater inflater=null;

    Context ctx;

    public TrackListAdapter(Context context) {
        super(context, 0);
        this.ctx = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = LayoutInflater.from(ctx).inflate(R.layout.song_row, null);

        TextView title = (TextView)vi.findViewById(R.id.title); // title
        TextView artist = (TextView)vi.findViewById(R.id.artist); // artist name
        TextView duration = (TextView)vi.findViewById(R.id.duration); // duration
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.imageView); // thumb image


        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Result track= (Result)v.getTag();
                Intent iLyricsIntent =new Intent(ctx,LyricsActivity.class);
                String strValue="";
                iLyricsIntent.putExtra(LyricsActivity.KEY_TRACK_NAME, track.getTrackName());
                iLyricsIntent.putExtra(LyricsActivity.KEY_ARTIST_NAME, track.getArtistName());
                iLyricsIntent.putExtra(LyricsActivity.KEY_DURATION, Utils.getDuration(track.getTrackTimeMillis()));
                iLyricsIntent.putExtra(LyricsActivity.KEY_THUMB_URL, track.getArtworkUrl100());
                v.getContext().startActivity(iLyricsIntent);
               // v.startActivity(iSearch);
            }
        });

        Result track = getItem(position);
        vi.setTag(track);
        title.setText(track.getTrackName());
        artist.setText(track.getArtistName());

       String strDuration= Utils.getDuration(track.getTrackTimeMillis());
       duration.setText(strDuration);
         String url=track.getArtworkUrl100();
        Picasso.with(ctx).load(url).into(thumb_image);
        return vi;
    }



    public void setData(List<Result> data) {
        clear();
        if (data != null) {
            for (Result item : data) {
                add(item);
            }
        }
    }
}
