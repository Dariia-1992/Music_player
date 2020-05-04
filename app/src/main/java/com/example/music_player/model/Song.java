package com.example.music_player.model;

public class Song {
    private String path;
    private String title;
    private int image;
    private String album;
    private String artist;

    public Song(String title) {
        this.title = title;
    }

    public String getPath() { return path; }
    public String getTitle() {
        return title;
    }
    public int getImage() { return image; }
    public String getAlbum() { return album; }
    public String getArtist() { return artist; }
}
