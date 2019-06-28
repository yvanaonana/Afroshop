package com.tsafack.tkswif.afroshop.entities.entitiesBD;


import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Date;

/**
 * Created by ESPACE-TEK on 04/02/2018.
 */

public class Cours implements Parcelable{

    private String idCours;
    private String idFormation;
    private String libCours;
    private String descriptionCours;
    private Date dateCreationCours;
    private String imageCours;

    public Cours() {
    }

    public Cours(String idCours, String idFormation, String libCours, String descriptionCours,
                 Date dateCreationCours, String imageCours) {
        this.idCours = idCours;
        this.idFormation = idFormation;
        this.libCours = libCours;
        this.descriptionCours = descriptionCours;
        this.dateCreationCours = dateCreationCours;
        this.imageCours = imageCours;
    }

    protected Cours(Parcel in) {
        idCours = in.readString();
        idFormation = in.readString();
        libCours = in.readString();
        descriptionCours = in.readString();
        imageCours = in.readString();
    }

    public static final Creator<Cours> CREATOR = new Creator<Cours>() {
        @Override
        public Cours createFromParcel(Parcel in) {
            return new Cours(in);
        }

        @Override
        public Cours[] newArray(int size) {
            return new Cours[size];
        }
    };

    public String getIdCours() {
        return idCours;
    }

    public void setIdCours(String idCours) {
        this.idCours = idCours;
    }

    public String getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(String idFormation) {
        this.idFormation = idFormation;
    }

    public String getLibCours() {
        return libCours;
    }

    public void setLibCours(String libCours) {
        this.libCours = libCours;
    }

    public String getDescriptionCours() {
        return descriptionCours;
    }

    public void setDescriptionCours(String descriptionCours) {
        this.descriptionCours = descriptionCours;
    }

    public Date getDateCreationCours() {
        return dateCreationCours;
    }

    public void setDateCreationCours(Date dateCreationCours) {
        this.dateCreationCours = dateCreationCours;
    }

    public String getImageCours() {
        return imageCours;
    }

    public void setImageCours(String imageCours) {
        this.imageCours = imageCours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cours cours = (Cours) o;

        if (!idCours.equals(cours.idCours)) return false;
        return idFormation.equals(cours.idFormation);

    }

    @Override
    public int hashCode() {
        int result = idCours.hashCode();
        result = 31 * result + idFormation.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Cours{" +
                "idCours='" + idCours + '\'' +
                ", idFormation='" + idFormation + '\'' +
                ", libCours='" + libCours + '\'' +
                ", descriptionCours='" + descriptionCours + '\'' +
                ", dateCreationCours=" + dateCreationCours +
                ", imageCours='" + imageCours + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idCours);
        parcel.writeString(idFormation);
        parcel.writeString(libCours);
        parcel.writeString(descriptionCours);
        parcel.writeString(imageCours);
    }
}
