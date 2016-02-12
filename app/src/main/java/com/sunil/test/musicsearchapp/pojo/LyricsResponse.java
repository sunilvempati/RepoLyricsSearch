
package com.sunil.test.musicsearchapp.pojo;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class LyricsResponse {

    @SerializedName("artist")
    @Expose
    private String artist;
    @SerializedName("song")
    @Expose
    private String song;
    @SerializedName("lyrics")
    @Expose
    private String lyrics;
    @SerializedName("url")
    @Expose
    private String url;

    /**
     * 
     * @return
     *     The artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * 
     * @param artist
     *     The artist
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * 
     * @return
     *     The song
     */
    public String getSong() {
        return song;
    }

    /**
     * 
     * @param song
     *     The song
     */
    public void setSong(String song) {
        this.song = song;
    }

    /**
     * 
     * @return
     *     The lyrics
     */
    public String getLyrics() {
        return lyrics;
    }

    /**
     * 
     * @param lyrics
     *     The lyrics
     */
    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
