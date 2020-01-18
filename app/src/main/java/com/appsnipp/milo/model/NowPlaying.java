package com.appsnipp.milo.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class NowPlaying implements Parcelable {
    private Double vote_average;
    private String title;
    private String overview;
    private String release_date;
    private String photo;


    public Double getRate() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public NowPlaying(JSONObject object) {
        try {
            Double vote_average = object.getDouble("vote_average");
            String title = object.getString("title");
            String overview = object.getString("overview");
            String release_date = object.getString("release_date");
            String poster_path = object.getString("poster_path");

            this.vote_average = vote_average;
            this.title = title;
            this.overview = overview;
            this.release_date = release_date;
            this.photo = poster_path;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.vote_average);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
        dest.writeString(this.photo);
    }

    protected NowPlaying(Parcel in) {
        this.vote_average = (Double) in.readValue(Double.class.getClassLoader());
        this.title = in.readString();
        this.overview = in.readString();
        this.release_date = in.readString();
        this.photo = in.readString();
    }

    public static final Creator<NowPlaying> CREATOR = new Creator<NowPlaying>() {
        @Override
        public NowPlaying createFromParcel(Parcel source) {
            return new NowPlaying(source);
        }

        @Override
        public NowPlaying[] newArray(int size) {
            return new NowPlaying[size];
        }
    };
}
