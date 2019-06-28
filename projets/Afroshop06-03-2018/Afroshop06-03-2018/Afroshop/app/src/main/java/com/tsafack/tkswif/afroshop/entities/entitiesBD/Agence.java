package com.tsafack.tkswif.afroshop.entities.entitiesBD;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Date;

/**
 * Created by ESPACE-TEK on 05/02/2018.
 */

public class Agence implements Parcelable{
    private String idAgence;
    private String nomAgence;
    private Date dateNaissanceAgence;
    private String telephoneAgence;
    private String emailAgence;
    private String paysAgence;
    private String regionAgence;
    private String localiteAgence;
    private String villeAgence;
    private Date dateInscriptionAgence;

    public Agence() {
    }

    public Agence(String idAgence, String nomAgence, Date dateNaissanceAgence, String telephoneAgence,
                  String emailAgence, String paysAgence, String regionAgence, String localiteAgence,
                  String villeAgence, Date dateInscriptionAgence) {
        this.idAgence = idAgence;
        this.nomAgence = nomAgence;
        this.dateNaissanceAgence = dateNaissanceAgence;
        this.telephoneAgence = telephoneAgence;
        this.emailAgence = emailAgence;
        this.paysAgence = paysAgence;
        this.regionAgence = regionAgence;
        this.localiteAgence = localiteAgence;
        this.villeAgence = villeAgence;
        this.dateInscriptionAgence = dateInscriptionAgence;
    }

    protected Agence(Parcel in) {
        idAgence = in.readString();
        nomAgence = in.readString();
        telephoneAgence = in.readString();
        emailAgence = in.readString();
        paysAgence = in.readString();
        regionAgence = in.readString();
        localiteAgence = in.readString();
        villeAgence = in.readString();
    }

    public static final Creator<Agence> CREATOR = new Creator<Agence>() {
        @Override
        public Agence createFromParcel(Parcel in) {
            return new Agence(in);
        }

        @Override
        public Agence[] newArray(int size) {
            return new Agence[size];
        }
    };

    public String getIdAgence() {
        return idAgence;
    }

    public void setIdAgence(String idAgence) {
        this.idAgence = idAgence;
    }

    public String getNomAgence() {
        return nomAgence;
    }

    public void setNomAgence(String nomAgence) {
        this.nomAgence = nomAgence;
    }

    public Date getDateNaissanceAgence() {
        return dateNaissanceAgence;
    }

    public void setDateNaissanceAgence(Date dateNaissanceAgence) {
        this.dateNaissanceAgence = dateNaissanceAgence;
    }

    public String getTelephoneAgence() {
        return telephoneAgence;
    }

    public void setTelephoneAgence(String telephoneAgence) {
        this.telephoneAgence = telephoneAgence;
    }

    public String getEmailAgence() {
        return emailAgence;
    }

    public void setEmailAgence(String emailAgence) {
        this.emailAgence = emailAgence;
    }

    public String getPaysAgence() {
        return paysAgence;
    }

    public void setPaysAgence(String paysAgence) {
        this.paysAgence = paysAgence;
    }

    public String getRegionAgence() {
        return regionAgence;
    }

    public void setRegionAgence(String regionAgence) {
        this.regionAgence = regionAgence;
    }

    public String getLocaliteAgence() {
        return localiteAgence;
    }

    public void setLocaliteAgence(String localiteAgence) {
        this.localiteAgence = localiteAgence;
    }

    public String getVilleAgence() {
        return villeAgence;
    }

    public void setVilleAgence(String villeAgence) {
        this.villeAgence = villeAgence;
    }

    public Date getDateInscriptionAgence() {
        return dateInscriptionAgence;
    }

    public void setDateInscriptionAgence(Date dateInscriptionAgence) {
        this.dateInscriptionAgence = dateInscriptionAgence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Agence agence = (Agence) o;

        return idAgence.equals(agence.idAgence);

    }

    @Override
    public int hashCode() {
        return idAgence.hashCode();
    }

    @Override
    public String toString() {
        return "Agence{" +
                "idAgence='" + idAgence + '\'' +
                ", nomAgence='" + nomAgence + '\'' +
                ", dateNaissanceAgence=" + dateNaissanceAgence +
                ", telephoneAgence='" + telephoneAgence + '\'' +
                ", emailAgence='" + emailAgence + '\'' +
                ", paysAgence='" + paysAgence + '\'' +
                ", regionAgence='" + regionAgence + '\'' +
                ", localiteAgence='" + localiteAgence + '\'' +
                ", villeAgence='" + villeAgence + '\'' +
                ", dateInscriptionAgence=" + dateInscriptionAgence +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idAgence);
        parcel.writeString(nomAgence);
        parcel.writeString(telephoneAgence);
        parcel.writeString(emailAgence);
        parcel.writeString(paysAgence);
        parcel.writeString(regionAgence);
        parcel.writeString(localiteAgence);
        parcel.writeString(villeAgence);
    }
}
