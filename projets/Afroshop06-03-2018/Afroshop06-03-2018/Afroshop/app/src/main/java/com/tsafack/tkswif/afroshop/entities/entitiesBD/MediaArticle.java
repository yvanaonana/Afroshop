package com.tsafack.tkswif.afroshop.entities.entitiesBD;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ESPACE-TEK on 05/02/2018.
 */

public class MediaArticle implements Parcelable{
    private String idMedia;
    private String refArticle;
    private String typeCategorieArticle;
    private String urlMedia;

    public MediaArticle() {
    }

    public MediaArticle(String idMedia, String refArticle, String typeCategorieArticle, String urlMedia) {
        this.idMedia = idMedia;
        this.refArticle = refArticle;
        this.typeCategorieArticle = typeCategorieArticle;
        this.urlMedia = urlMedia;
    }

    protected MediaArticle(Parcel in) {
        idMedia = in.readString();
        refArticle = in.readString();
        typeCategorieArticle = in.readString();
        urlMedia = in.readString();
    }

    public static final Creator<MediaArticle> CREATOR = new Creator<MediaArticle>() {
        @Override
        public MediaArticle createFromParcel(Parcel in) {
            return new MediaArticle(in);
        }

        @Override
        public MediaArticle[] newArray(int size) {
            return new MediaArticle[size];
        }
    };

    public String getIdMedia() {
        return idMedia;
    }

    public void setIdMedia(String idMedia) {
        this.idMedia = idMedia;
    }

    public String getRefArticle() {
        return refArticle;
    }

    public void setRefArticle(String refArticle) {
        this.refArticle = refArticle;
    }

    public String getUrlMedia() {
        return urlMedia;
    }

    public void setUrlMedia(String urlMedia) {
        this.urlMedia = urlMedia;
    }
    public String getTypeCategorieArticle() {
        return typeCategorieArticle;
    }

    public void setTypeCategorieArticle(String typeCategorieArticle) {
        this.typeCategorieArticle = typeCategorieArticle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MediaArticle)) return false;

        MediaArticle that = (MediaArticle) o;

        if (!idMedia.equals(that.idMedia)) return false;
        return refArticle.equals(that.refArticle);

    }

    @Override
    public int hashCode() {
        int result = idMedia.hashCode();
        result = 31 * result + refArticle.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MediaArticle{" +
                "idMedia='" + idMedia + '\'' +
                ", refArticle='" + refArticle + '\'' +
                ", typeCategorieArticle='" + typeCategorieArticle + '\'' +
                ", urlMedia='" + urlMedia + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idMedia);
        parcel.writeString(refArticle);
        parcel.writeString(typeCategorieArticle);
        parcel.writeString(urlMedia);
    }
}
