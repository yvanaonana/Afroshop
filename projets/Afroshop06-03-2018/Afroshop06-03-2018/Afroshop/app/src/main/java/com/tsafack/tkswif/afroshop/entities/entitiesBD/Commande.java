package com.tsafack.tkswif.afroshop.entities.entitiesBD;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Date;

/**
 * Created by ESPACE-TEK on 05/02/2018.
 */

public class Commande implements Parcelable{
    private String numCommande;
    private String idUtilisateur;
    private String libCommmande;
    private Date dateCommande;
    private boolean etatCommande;
    private String evolutionCommande;

    public Commande() {
    }

    public Commande(String numCommande, String idUtilisateur, String libCommmande, Date dateCommande,
                    boolean etatCommande, String evolutionCommande) {
        this.numCommande = numCommande;
        this.idUtilisateur = idUtilisateur;
        this.libCommmande = libCommmande;
        this.dateCommande = dateCommande;
        this.etatCommande = etatCommande;
        this.evolutionCommande = evolutionCommande;
    }

    protected Commande(Parcel in) {
        numCommande = in.readString();
        idUtilisateur = in.readString();
        libCommmande = in.readString();
        etatCommande = in.readByte() != 0;
        evolutionCommande = in.readString();
    }

    public static final Creator<Commande> CREATOR = new Creator<Commande>() {
        @Override
        public Commande createFromParcel(Parcel in) {
            return new Commande(in);
        }

        @Override
        public Commande[] newArray(int size) {
            return new Commande[size];
        }
    };

    public String getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(String numCommande) {
        this.numCommande = numCommande;
    }

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getLibCommmande() {
        return libCommmande;
    }

    public void setLibCommmande(String libCommmande) {
        this.libCommmande = libCommmande;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public boolean isEtatCommande() {
        return etatCommande;
    }

    public void setEtatCommande(boolean etatCommande) {
        this.etatCommande = etatCommande;
    }

    public String getEvolutionCommande() {
        return evolutionCommande;
    }

    public void setEvolutionCommande(String evolutionCommande) {
        this.evolutionCommande = evolutionCommande;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Commande commande = (Commande) o;

        if (!numCommande.equals(commande.numCommande)) return false;
        return idUtilisateur.equals(commande.idUtilisateur);

    }

    @Override
    public int hashCode() {
        int result = numCommande.hashCode();
        result = 31 * result + idUtilisateur.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "numCommande='" + numCommande + '\'' +
                ", idUtilisateur='" + idUtilisateur + '\'' +
                ", libCommmande='" + libCommmande + '\'' +
                ", dateCommande=" + dateCommande +
                ", etatCommande=" + etatCommande +
                ", evolutionCommande='" + evolutionCommande + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(numCommande);
        parcel.writeString(idUtilisateur);
        parcel.writeString(libCommmande);
        parcel.writeByte((byte) (etatCommande ? 1 : 0));
        parcel.writeString(evolutionCommande);
    }
}
