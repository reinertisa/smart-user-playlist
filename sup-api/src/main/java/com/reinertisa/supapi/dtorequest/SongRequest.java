package com.reinertisa.supapi.dtorequest;

import jakarta.validation.constraints.NotBlank;

public class SongRequest {

    private long songId;

    @NotBlank(message = "This field is required.")
    private String title;

    @NotBlank(message = "This field is required.")
    private String artist;

    public long getSongId() {
        return songId;
    }

    public void setSongId(long songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
