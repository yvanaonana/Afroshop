package com.tsafack.tkswif.afroshop.entities.entitiesBD;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tsamo ulrich on 24/04/2018.
 */

public class AgenceBancaire implements Parcelable {

    private String loginAgenceBancaire;
    private String nomAgenceBancaire;
    private String telephoneAgenceBancaire;
    private String emailAgenceBancaire;
    private String siegeAgenceBancaire;


    protected AgenceBancaire(Parcel in) {
        loginAgenceBancaire = in.readString();
        nomAgenceBancaire = in.readString();
        telephoneAgenceBancaire = in.readString();
        emailAgenceBancaire = in.readString();
        siegeAgenceBancaire = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(loginAgenceBancaire);
        dest.writeString(nomAgenceBancaire);
        dest.writeString(telephoneAgenceBancaire);
        dest.writeString(emailAgenceBancaire);
        dest.writeString(siegeAgenceBancaire);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AgenceBancaire> CREATOR = new Creator<AgenceBancaire>() {
        @Override
        public AgenceBancaire createFromParcel(Parcel in) {
            return new AgenceBancaire(in);
        }

        @Override
        public AgenceBancaire[] newArray(int size) {
            return new AgenceBancaire[size];
        }
    };

    public AgenceBancaire() {
    }

    public AgenceBancaire(String loginAgenceBancaire, String nomAgenceBancaire, String telephoneAgenceBancaire,
                          String emailAgenceBancaire, String siegeAgenceBancaire) {
        this.loginAgenceBancaire = loginAgenceBancaire;
        this.nomAgenceBancaire = nomAgenceBancaire;
        this.telephoneAgenceBancaire = telephoneAgenceBancaire;
        this.emailAgenceBancaire = emailAgenceBancaire;
        this.siegeAgenceBancaire = siegeAgenceBancaire;
    }

    public String getLoginAgenceBancaire() {
        return loginAgenceBancaire;
    }

    public void setLoginAgenceBancaire(String loginAgenceBancaire) {
        this.loginAgenceBancaire = loginAgenceBancaire;
    }

    public String getNomAgenceBancaire() {
        return nomAgenceBancaire;
    }

    public void setNomAgenceBancaire(String nomAgenceBancaire) {
        this.nomAgenceBancaire = nomAgenceBancaire;
    }

    public String getTelephoneAgenceBancaire() {
        return telephoneAgenceBancaire;
    }

    public void setTelephoneAgenceBancaire(String telephoneAgenceBancaire) {
        this.telephoneAgenceBancaire = telephoneAgenceBancaire;
    }

    public String getEmailAgenceBancaire() {
        return emailAgenceBancaire;
    }

    public void setEmailAgenceBancaire(String emailAgenceBancaire) {
        this.emailAgenceBancaire = emailAgenceBancaire;
    }

    public String getSiegeAgenceBancaire() {
        return siegeAgenceBancaire;
    }

    public void setSiegeAgenceBancaire(String siegeAgenceBancaire) {
        this.siegeAgenceBancaire = siegeAgenceBancaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AgenceBancaire that = (AgenceBancaire) o;

        return getLoginAgenceBancaire() != null ? getLoginAgenceBancaire().equals(that.getLoginAgenceBancaire()) : that.getLoginAgenceBancaire() == null;
    }

    @Override
    public int hashCode() {
        return getLoginAgenceBancaire() != null ? getLoginAgenceBancaire().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "AgenceBancaire { " +
                "loginAgenceBancaire = '" + loginAgenceBancaire + '\'' +
                ", nomAgenceBancaire = '" + nomAgenceBancaire + '\'' +
                ", telephoneAgenceBancaire = '" + telephoneAgenceBancaire + '\'' +
                ", emailAgenceBancaire = '" + emailAgenceBancaire + '\'' +
                ", siegeAgenceBancaire = '" + siegeAgenceBancaire + '\'' +
                " }";
    }
}
