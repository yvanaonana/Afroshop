package com.tsafack.tkswif.afroshop.entities.entitiesBD;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by ESPACE-TEK on 04/02/2018.
 */

public class Client implements Parcelable{

    private String idClient;
    private String nomClient;
    private Date dateNaissanceClient;
    private String telephoneClient;
    private String emailClient;
    private String paysClient;
    private String regionClient;
    private String villeClient;
    private String localitéClient;
    private Date dateInscriptionClient;

    public Client() {
    }

    public Client(String idClient, String nomClient, Date dateNaissanceClient, String telephoneClient,
                  String emailClient, String paysClient, String regionClient, String villeClient,
                  String localitéClient, Date dateInscriptionClient) {
        this.idClient = idClient;
        this.nomClient = nomClient;
        this.dateNaissanceClient = dateNaissanceClient;
        this.telephoneClient = telephoneClient;
        this.emailClient = emailClient;
        this.paysClient = paysClient;
        this.regionClient = regionClient;
        this.villeClient = villeClient;
        this.localitéClient = localitéClient;
        this.dateInscriptionClient = dateInscriptionClient;
    }

    protected Client(Parcel in) {
        idClient = in.readString();
        nomClient = in.readString();
        telephoneClient = in.readString();
        emailClient = in.readString();
        paysClient = in.readString();
        regionClient = in.readString();
        villeClient = in.readString();
        localitéClient = in.readString();
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public Date getDateNaissanceClient() {
        return dateNaissanceClient;
    }

    public void setDateNaissanceClient(Date dateNaissanceClient) {
        this.dateNaissanceClient = dateNaissanceClient;
    }

    public String getTelephoneClient() {
        return telephoneClient;
    }

    public void setTelephoneClient(String telephoneClient) {
        this.telephoneClient = telephoneClient;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public String getPaysClient() {
        return paysClient;
    }

    public void setPaysClient(String paysClient) {
        this.paysClient = paysClient;
    }

    public String getRegionClient() {
        return regionClient;
    }

    public void setRegionClient(String regionClient) {
        this.regionClient = regionClient;
    }

    public String getVilleClient() {
        return villeClient;
    }

    public void setVilleClient(String villeClient) {
        this.villeClient = villeClient;
    }

    public String getLocalitéClient() {
        return localitéClient;
    }

    public void setLocalitéClient(String localitéClient) {
        this.localitéClient = localitéClient;
    }

    public Date getDateInscriptionClient() {
        return dateInscriptionClient;
    }

    public void setDateInscriptionClient(Date dateInscriptionClient) {
        this.dateInscriptionClient = dateInscriptionClient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return idClient.equals(client.idClient);

    }

    @Override
    public int hashCode() {
        return idClient.hashCode();
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient='" + idClient + '\'' +
                ", nomClient='" + nomClient + '\'' +
                ", dateNaissanceClient=" + dateNaissanceClient +
                ", telephoneClient='" + telephoneClient + '\'' +
                ", emailClient='" + emailClient + '\'' +
                ", paysClient='" + paysClient + '\'' +
                ", regionClient='" + regionClient + '\'' +
                ", villeClient='" + villeClient + '\'' +
                ", localitéClient='" + localitéClient + '\'' +
                ", dateInscriptionClient=" + dateInscriptionClient +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idClient);
        parcel.writeString(nomClient);
        parcel.writeString(telephoneClient);
        parcel.writeString(emailClient);
        parcel.writeString(paysClient);
        parcel.writeString(regionClient);
        parcel.writeString(villeClient);
        parcel.writeString(localitéClient);
    }
}
