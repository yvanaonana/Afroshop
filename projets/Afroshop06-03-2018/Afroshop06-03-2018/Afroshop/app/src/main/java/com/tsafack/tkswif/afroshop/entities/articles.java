package com.tsafack.tkswif.afroshop.entities;

/**
 * Created by Stephane on 14/02/2018.
 */

public class articles {
    private String name;
    private int numOfSongs;
    private int thumbnail;

    public articles() {
    }

    public articles(String name, int numOfSongs, int thumbnail) {
        this.name = name;
        this.numOfSongs = numOfSongs;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfSongs() {
        return numOfSongs;
    }

    public void setNumOfSongs(int numOfSongs) {
        this.numOfSongs = numOfSongs;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "articles{" +
                "name='" + name + '\'' +
                ", numOfSongs=" + numOfSongs +
                ", thumbnail=" + thumbnail +
                '}';
    }
}
