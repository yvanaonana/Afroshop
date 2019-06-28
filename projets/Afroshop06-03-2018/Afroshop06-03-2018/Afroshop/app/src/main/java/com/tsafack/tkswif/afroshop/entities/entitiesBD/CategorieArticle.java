package com.tsafack.tkswif.afroshop.entities.entitiesBD;


import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Date;

/**
 * Created by ESPACE-TEK on 04/02/2018.
 */

public class CategorieArticle implements Parcelable{
    private String IDCATEGORIEARTICLE;
    private String LIBELLECATEGORIEARTICLE;
    private String TYPECATEGORIEARTICLE;
    private String IMAGECATEGORIEARTICLE;
    private String DATECREATIONCATEGORIEARTICLE;

    public CategorieArticle() {
    }

    public CategorieArticle(String idCategorieArticle, String libelleCategorieArticle, String typeCategorieArticle, String imageCategorieArticle,
                            String dateCreationCategorieArticle) {
        this.IDCATEGORIEARTICLE = idCategorieArticle;
        this.LIBELLECATEGORIEARTICLE = libelleCategorieArticle;
        this.TYPECATEGORIEARTICLE = typeCategorieArticle;
        this.IMAGECATEGORIEARTICLE = imageCategorieArticle;
        this.DATECREATIONCATEGORIEARTICLE = dateCreationCategorieArticle;
    }

    protected CategorieArticle(Parcel in) {
        IDCATEGORIEARTICLE = in.readString();
        LIBELLECATEGORIEARTICLE = in.readString();
        TYPECATEGORIEARTICLE = in.readString();
        IMAGECATEGORIEARTICLE = in.readString();
    }

    public static final Creator<CategorieArticle> CREATOR = new Creator<CategorieArticle>() {
        @Override
        public CategorieArticle createFromParcel(Parcel in) {
            return new CategorieArticle(in);
        }

        @Override
        public CategorieArticle[] newArray(int size) {
            return new CategorieArticle[size];
        }
    };

    public String getIdCategorieArticle() {
        return IDCATEGORIEARTICLE;
    }

    public void setIdCategorieArticle(String idCategorieArticle) {
        this.IDCATEGORIEARTICLE = idCategorieArticle;
    }

    public String getTypeCategorieArticle() {
        return TYPECATEGORIEARTICLE;
    }

    public void setTypeCategorieArticle(String typeCategorieArticle) {
        this.TYPECATEGORIEARTICLE = typeCategorieArticle;
    }

    public String getImageCategorieArticle() {
        return IMAGECATEGORIEARTICLE;
    }

    public void setImageCategorieArticle(String imageCategorieArticle) {
        this.IMAGECATEGORIEARTICLE = imageCategorieArticle;
    }

    public String getDateCreationCatégorieArticle() {
        return DATECREATIONCATEGORIEARTICLE;
    }

    public void setDateCreationCatégorieArticle(String dateCreationCatégorieArticle) {
        this.DATECREATIONCATEGORIEARTICLE = dateCreationCatégorieArticle;
    }

    public String getLibelleCategorieArticle() {
        return LIBELLECATEGORIEARTICLE;
    }

    public void setLibelleCategorieArticle(String libelleCategorieArticle) {
        this.LIBELLECATEGORIEARTICLE = libelleCategorieArticle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategorieArticle that = (CategorieArticle) o;

        return IDCATEGORIEARTICLE.equals(that.IDCATEGORIEARTICLE);

    }

    @Override
    public int hashCode() {
        return IDCATEGORIEARTICLE.hashCode();
    }

    @Override
    public String toString() {
        return "CategorieArticle{" +
                "idCategorieArticle='" + IDCATEGORIEARTICLE + '\'' +
                ", libelleCategorieArticle='" + LIBELLECATEGORIEARTICLE + '\'' +
                ", typeCategorieArticle='" + TYPECATEGORIEARTICLE + '\'' +
                ", imageCategorieArticle='" + IMAGECATEGORIEARTICLE + '\'' +
                ", dateCreationCatégorieArticle=" + DATECREATIONCATEGORIEARTICLE +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(IDCATEGORIEARTICLE);
        parcel.writeString(LIBELLECATEGORIEARTICLE);
        parcel.writeString(TYPECATEGORIEARTICLE);
        parcel.writeString(IMAGECATEGORIEARTICLE);
    }
}
