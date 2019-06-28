package com.tsafack.tkswif.afroshop.entities.entitiesBD;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by ESPACE-TEK on 04/02/2018.
 */

public class Chaine implements Parcelable{

    private String idChaine;
    private String idUtilisateur;
    private String libChaine;
    private String descriptionChaine;
    private Date dateCreationChaine;
    private String imageChaine;

    public Chaine() {
    }

    public Chaine(String idChaine, String idUtilisateur, String libChaine, String descriptionChaine,
                  Date dateCreationChaine, String imageChaine) {
        this.idChaine = idChaine;
        this.idUtilisateur = idUtilisateur;
        this.libChaine = libChaine;
        this.descriptionChaine = descriptionChaine;
        this.dateCreationChaine = dateCreationChaine;
        this.imageChaine = imageChaine;
    }

    protected Chaine(Parcel in) {
        idChaine = in.readString();
        idUtilisateur = in.readString();
        libChaine = in.readString();
        descriptionChaine = in.readString();
        imageChaine = in.readString();
    }

    public static final Creator<Chaine> CREATOR = new Creator<Chaine>() {
        @Override
        public Chaine createFromParcel(Parcel in) {
            return new Chaine(in);
        }

        @Override
        public Chaine[] newArray(int size) {
            return new Chaine[size];
        }
    };

    public String getIdChaine() {
        return idChaine;
    }

    public void setIdChaine(String idChaine) {
        this.idChaine = idChaine;
    }

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getLibChaine() {
        return libChaine;
    }

    public void setLibChaine(String libChaine) {
        this.libChaine = libChaine;
    }

    public String getDescriptionChaine() {
        return descriptionChaine;
    }

    public void setDescriptionChaine(String descriptionChaine) {
        this.descriptionChaine = descriptionChaine;
    }

    public Date getDateCreationChaine() {
        return dateCreationChaine;
    }

    public void setDateCreationChaine(Date dateCreationChaine) {
        this.dateCreationChaine = dateCreationChaine;
    }

    public String getImageChaine() {
        return imageChaine;
    }

    public void setImageChaine(String imageChaine) {
        this.imageChaine = imageChaine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chaine chaine = (Chaine) o;

        if (!idChaine.equals(chaine.idChaine)) return false;
        return idUtilisateur.equals(chaine.idUtilisateur);

    }

    @Override
    public int hashCode() {
        int result = idChaine.hashCode();
        result = 31 * result + idUtilisateur.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Chaine{" +
                "idChaine='" + idChaine + '\'' +
                ", idUtilisateur='" + idUtilisateur + '\'' +
                ", libChaine='" + libChaine + '\'' +
                ", descriptionChaine='" + descriptionChaine + '\'' +
                ", dateCreationChaine=" + dateCreationChaine +
                ", imageChaine='" + imageChaine + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idChaine);
        parcel.writeString(idUtilisateur);
        parcel.writeString(libChaine);
        parcel.writeString(descriptionChaine);
        parcel.writeString(dateCreationChaine.toString());
        parcel.writeString(imageChaine);
    }
}
