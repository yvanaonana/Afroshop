package com.tsafack.tkswif.afroshop.entities.entitiesBD;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by ESPACE-TEK on 04/02/2018.
 */

public class Formation implements Parcelable{

    private String idFormation;
    private String idChaine;
    private String libFormation;
    private String descriptionFormation;
    private Date dateCreationFormation;
    private String imageFormation;

    public Formation() {
    }

    public Formation(String idFormation, String idChaine, String libFormation,
                     String descriptionFormation, Date dateCreationFormation, String imageFormation) {
        this.idFormation = idFormation;
        this.idChaine = idChaine;
        this.libFormation = libFormation;
        this.descriptionFormation = descriptionFormation;
        this.dateCreationFormation = dateCreationFormation;
        this.imageFormation = imageFormation;
    }

    protected Formation(Parcel in) {
        idFormation = in.readString();
        idChaine = in.readString();
        libFormation = in.readString();
        descriptionFormation = in.readString();
        imageFormation = in.readString();
    }

    public static final Creator<Formation> CREATOR = new Creator<Formation>() {
        @Override
        public Formation createFromParcel(Parcel in) {
            return new Formation(in);
        }

        @Override
        public Formation[] newArray(int size) {
            return new Formation[size];
        }
    };

    public String getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(String idFormation) {
        this.idFormation = idFormation;
    }

    public String getIdChaine() {
        return idChaine;
    }

    public void setIdChaine(String idChaine) {
        this.idChaine = idChaine;
    }

    public String getLibFormation() {
        return libFormation;
    }

    public void setLibFormation(String libFormation) {
        this.libFormation = libFormation;
    }

    public String getDescriptionFormation() {
        return descriptionFormation;
    }

    public void setDescriptionFormation(String descriptionFormation) {
        this.descriptionFormation = descriptionFormation;
    }

    public Date getDateCreationFormation() {
        return dateCreationFormation;
    }

    public void setDateCreationFormation(Date dateCreationFormation) {
        this.dateCreationFormation = dateCreationFormation;
    }

    public String getImageFormation() {
        return imageFormation;
    }

    public void setImageFormation(String imageFormation) {
        this.imageFormation = imageFormation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Formation formation = (Formation) o;

        if (!idFormation.equals(formation.idFormation)) return false;
        return idChaine.equals(formation.idChaine);

    }

    @Override
    public int hashCode() {
        int result = idFormation.hashCode();
        result = 31 * result + idChaine.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Formation{" +
                "idFormation='" + idFormation + '\'' +
                ", idChaine='" + idChaine + '\'' +
                ", libFormation='" + libFormation + '\'' +
                ", descriptionFormation='" + descriptionFormation + '\'' +
                ", dateCreationFormation=" + dateCreationFormation +
                ", imageFormation='" + imageFormation + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idFormation);
        parcel.writeString(idChaine);
        parcel.writeString(libFormation);
        parcel.writeString(descriptionFormation);
        parcel.writeString(dateCreationFormation.toString());
        parcel.writeString(imageFormation);
    }
}
