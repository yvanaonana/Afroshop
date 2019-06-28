package com.tsafack.tkswif.afroshop.entities.entitiesBD;


import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Date;

/**
 * Created by ESPACE-TEK on 05/02/2018.
 */

public class Utilisateur implements Parcelable{

    private String idUtilisateur;
    private String nomUtilisateur;
    private Date dateNaissanceUtilisateur;
    private String telephoneUtilisateur;
    private String emailUtilisateur;
    private String paysUtilisateur;
    private String regionUtilisateeur;
    private String localiteUtilisateur;
    private String villeUtilisateur;
    private String dateInscriptionUtilisateur;

    public Utilisateur() {
    }

    public Utilisateur(String idUtilisateur, String nomUtilisateur, Date dateNaissanceUtilisateur,
                       String telephoneUtilisateur, String emailUtilisateur, String paysUtilisateur,
                       String regionUtilisateeur, String localiteUtilisateur, String villeUtilisateur,
                       String dateInscriptionUtilisateur) {
        this.idUtilisateur = idUtilisateur;
        this.nomUtilisateur = nomUtilisateur;
        this.dateNaissanceUtilisateur = dateNaissanceUtilisateur;
        this.telephoneUtilisateur = telephoneUtilisateur;
        this.emailUtilisateur = emailUtilisateur;
        this.paysUtilisateur = paysUtilisateur;
        this.regionUtilisateeur = regionUtilisateeur;
        this.localiteUtilisateur = localiteUtilisateur;
        this.villeUtilisateur = villeUtilisateur;
        this.dateInscriptionUtilisateur = dateInscriptionUtilisateur;
    }

    protected Utilisateur(Parcel in) {
        idUtilisateur = in.readString();
        nomUtilisateur = in.readString();
        telephoneUtilisateur = in.readString();
        emailUtilisateur = in.readString();
        paysUtilisateur = in.readString();
        regionUtilisateeur = in.readString();
        localiteUtilisateur = in.readString();
        villeUtilisateur = in.readString();
        dateInscriptionUtilisateur = in.readString();
    }

    public static final Creator<Utilisateur> CREATOR = new Creator<Utilisateur>() {
        @Override
        public Utilisateur createFromParcel(Parcel in) {
            return new Utilisateur(in);
        }

        @Override
        public Utilisateur[] newArray(int size) {
            return new Utilisateur[size];
        }
    };

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public Date getDateNaissanceUtilisateur() {
        return dateNaissanceUtilisateur;
    }

    public void setDateNaissanceUtilisateur(Date dateNaissanceUtilisateur) {
        this.dateNaissanceUtilisateur = dateNaissanceUtilisateur;
    }

    public String getTelephoneUtilisateur() {
        return telephoneUtilisateur;
    }

    public void setTelephoneUtilisateur(String telephoneUtilisateur) {
        this.telephoneUtilisateur = telephoneUtilisateur;
    }

    public String getEmailUtilisateur() {
        return emailUtilisateur;
    }

    public void setEmailUtilisateur(String emailUtilisateur) {
        this.emailUtilisateur = emailUtilisateur;
    }

    public String getPaysUtilisateur() {
        return paysUtilisateur;
    }

    public void setPaysUtilisateur(String paysUtilisateur) {
        this.paysUtilisateur = paysUtilisateur;
    }

    public String getRegionUtilisateeur() {
        return regionUtilisateeur;
    }

    public void setRegionUtilisateeur(String regionUtilisateeur) {
        this.regionUtilisateeur = regionUtilisateeur;
    }

    public String getLocaliteUtilisateur() {
        return localiteUtilisateur;
    }

    public void setLocaliteUtilisateur(String localiteUtilisateur) {
        this.localiteUtilisateur = localiteUtilisateur;
    }

    public String getVilleUtilisateur() {
        return villeUtilisateur;
    }

    public void setVilleUtilisateur(String villeUtilisateur) {
        this.villeUtilisateur = villeUtilisateur;
    }

    public String getDateInscriptionUtilisateur() {
        return dateInscriptionUtilisateur;
    }

    public void setDateInscriptionUtilisateur(String dateInscriptionUtilisateur) {
        this.dateInscriptionUtilisateur = dateInscriptionUtilisateur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Utilisateur that = (Utilisateur) o;

        return idUtilisateur.equals(that.idUtilisateur);

    }

    @Override
    public int hashCode() {
        return idUtilisateur.hashCode();
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "idUtilisateur='" + idUtilisateur + '\'' +
                ", nomUtilisateur='" + nomUtilisateur + '\'' +
                ", dateNaissanceUtilisateur=" + dateNaissanceUtilisateur +
                ", telephoneUtilisateur='" + telephoneUtilisateur + '\'' +
                ", emailUtilisateur='" + emailUtilisateur + '\'' +
                ", paysUtilisateur='" + paysUtilisateur + '\'' +
                ", regionUtilisateeur='" + regionUtilisateeur + '\'' +
                ", localiteUtilisateur='" + localiteUtilisateur + '\'' +
                ", villeUtilisateur='" + villeUtilisateur + '\'' +
                ", dateInscriptionUtilisateur='" + dateInscriptionUtilisateur + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idUtilisateur);
        parcel.writeString(nomUtilisateur);
        parcel.writeString(telephoneUtilisateur);
        parcel.writeString(emailUtilisateur);
        parcel.writeString(paysUtilisateur);
        parcel.writeString(regionUtilisateeur);
        parcel.writeString(localiteUtilisateur);
        parcel.writeString(villeUtilisateur);
        parcel.writeString(dateInscriptionUtilisateur);
    }
}
